package com.onlinestore

import com.google.api.client.util.Value
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import com.onlinestore.db.dao.AuthInfoDao
import com.onlinestore.db.dao.CategoriesDao
import com.onlinestore.db.dao.ProductsDao
import com.onlinestore.db.dao.UsersDao
import com.onlinestore.models.AuthInfo
import com.onlinestore.models.Category
import com.onlinestore.models.Product
import com.onlinestore.models.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.onlinestore.services.CategoryService
import com.onlinestore.services.LoginService
import com.onlinestore.services.ProductsService
import com.onlinestore.services.RegisterService
import org.springframework.context.annotation.PropertySource

@Configuration
class ServerAppConfig {
    //@Value(value = "\${sqlite.backend.url}")
    var url: String = "jdbc:sqlite:/app/onlinestore_backend_db"

    @Bean
    fun usersDao(): UsersDao =
        UsersDao(
            delegateDAO = createDao(clazz = User::class.java)
        )

    @Bean
    fun authInfoDAO(): AuthInfoDao =
        AuthInfoDao(delegateDAO = createDao(clazz = AuthInfo::class.java))

    @Bean
    fun productsDao(): ProductsDao =
        ProductsDao(
            delegateDao = createDao(clazz = Product::class.java),
            categoriesDao = createDao(clazz = Category::class.java)
        )

    @Bean
    fun categoriesDao(): CategoriesDao =
        CategoriesDao(
            delegateDao = createDao(clazz = Category::class.java)
        )

    @Bean
    fun loginService(
        userDAO: UsersDao,
        authInfoDao: AuthInfoDao
    ): LoginService =
        LoginService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun registerService(
        userDAO: UsersDao,
        authInfoDao: AuthInfoDao
    ): RegisterService =
        RegisterService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun categoryService(
        categoriesDao: CategoriesDao
    ): CategoryService =
        CategoryService(
            categoriesDao = categoriesDao
        )

    @Bean
    fun productsService(
        productsDAO : ProductsDao
    ): ProductsService =
        ProductsService(
            productsDao = productsDAO
        )


    private fun <T, I> createDao(clazz: Class<T>): Dao<T, I> {
        val connectionSource = JdbcConnectionSource(url)
        val orm: Dao<T, I> = DaoManager.createDao(
            connectionSource,
            clazz
        )
        TableUtils.createTableIfNotExists(connectionSource, clazz)
        return orm
    }
}
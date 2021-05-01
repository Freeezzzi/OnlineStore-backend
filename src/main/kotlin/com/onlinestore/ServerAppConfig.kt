package com.onlinestore

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.logger.LoggerFactory
import com.onlinestore.db.dao.AuthInfoDao
import com.onlinestore.db.dao.CategoriesDao
import com.onlinestore.db.dao.ProductsDao
import com.onlinestore.db.dao.UsersDao
import com.onlinestore.models.AuthInfo
import com.onlinestore.models.Category
import com.onlinestore.models.Product
import com.onlinestore.models.User
import com.onlinestore.services.CategoryService
import com.onlinestore.services.LoginService
import com.onlinestore.services.ProductsService
import com.onlinestore.services.RegisterService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServerAppConfig {
    //@Value(value = "\${sqlite.backend.url}")
    var url: String = "jdbc:mysql:///appStorage?cloudSqlInstance=onlinestore-346ba:europe-west1:backend-db&" +
            "socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=BackendPassword"
    private val JDBC_URL = "jdbc:mysql:///%s"
    private val DB_NAME = "appStorage"
    private val DB_USER = "root"
    private val DB_PWD = "BackendPassword"
    private val CONNECTION_NAME = "onlinestore-346ba:europe-west1:backend-db"


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
        connectionSource.initialize()
        val orm: Dao<T, I> = DaoManager.createDao(
            connectionSource,
            clazz
        )
        //TableUtils.createTableIfNotExists(connectionSource, clazz)
        return orm
    }
}
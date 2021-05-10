package com.onlinestore

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.logger.LoggerFactory
import com.j256.ormlite.table.TableUtils
import com.onlinestore.db.dao.*
import com.onlinestore.models.*
import com.onlinestore.services.*
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
    fun ordersDao():OrdersDao =
        OrdersDao(
            delegateDAO = createDao(clazz = Order::class.java),
            usersDao = createDao(clazz = User::class.java),
            productsDao = createDao(clazz = Product::class.java),
            categoryDao = createDao(clazz = Category::class.java)
        )

    @Bean
    fun recipesDao():RecipesDao =
        RecipesDao(
            recipesDao = createDao(clazz = Recipe::class.java)
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

    @Bean
    fun ordersService(
        ordersDao: OrdersDao,
        usersDao: UsersDao,
        productsDAO: ProductsDao
    ): OrdersService =
        OrdersService(
            ordersDao = ordersDao,
            usersDao = usersDao,
            productsDao = productsDAO
        )

    @Bean
    fun usersService(
        usersDao: UsersDao
    ): UsersService = UsersService(
        usersDao = usersDao
    )

    @Bean
    fun recipesService(
        recipesDao: RecipesDao
    ): RecipesService = RecipesService(
        recipesDao = recipesDao
    )


    private fun <T, I> createDao(clazz: Class<T>): Dao<T, I> {
        val connectionSource = JdbcConnectionSource(url)
        connectionSource.initialize()
        val orm: Dao<T, I> = DaoManager.createDao(
            connectionSource,
            clazz
        )
        TableUtils.createTableIfNotExists(connectionSource, clazz)
        return orm
    }
}
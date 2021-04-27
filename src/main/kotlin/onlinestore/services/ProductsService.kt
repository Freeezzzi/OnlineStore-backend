package onlinestore.services

import onlinestore.db.dao.ProductsDao
import onlinestore.models.Product
import org.springframework.beans.factory.annotation.Autowired

class ProductsService(
    @Autowired val productsDao: ProductsDao
) {
    fun getByCategory(categoryId:Long) = productsDao.getByCategory(categoryId)

    fun getPopular() = productsDao.getPopular().subList(0,15)

    fun buyProduct(productId:Long)= productsDao.buyProduct(productId)

    fun getOnSale() = productsDao.getOnSale()
}
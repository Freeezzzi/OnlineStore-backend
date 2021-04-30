package com.onlinestore.services

import com.onlinestore.db.dao.ProductsDao
import com.onlinestore.models.Product
import org.springframework.beans.factory.annotation.Autowired

class ProductsService(
    @Autowired val productsDao: ProductsDao
) {
    fun getByCategory(categoryId:Long) = productsDao.getByCategory(categoryId)

    fun getPopular() = productsDao.getPopular().subList(0,15)

    fun buyProduct(productId:Long)= productsDao.buyProduct(productId)

    fun getOnSale() = productsDao.getOnSale()
}
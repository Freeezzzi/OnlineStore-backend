package com.onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import com.onlinestore.models.Category
import com.onlinestore.models.Product
import org.springframework.beans.factory.annotation.Autowired

class ProductsDao(
    private val delegateDao: Dao<Product, Long>,
    @Autowired private val categoriesDao: Dao<Category, Long>
) {
    fun getAll() = delegateDao.queryForAll().map {
        categoriesDao.refresh(it.category)
        it
    }

    fun getByCategory(categoryId: Long) = delegateDao.queryForEq("category_id", categoryId).map {
        categoriesDao.refresh(it.category)
        it
    }

    fun getPopular() = delegateDao.queryBuilder().orderBy("bought", false).query().map {
        categoriesDao.refresh(it.category)
        it
    }

    fun getOnSale() = delegateDao.queryForEq("onSale", true).map {
        categoriesDao.refresh(it.category)
        it
    }

    fun buyProduct(productId: Long, count: Int) : Boolean{
        val value = delegateDao.queryForId(productId)
        if (value.amount < count){
            return false
        }
        value.amount -= count
        value.bought += count
        return delegateDao.update(value) == 1
    }

    fun checkIsCanBeBought(productId: Long, count: Int) : Boolean{
        val value = delegateDao.queryForId(productId)
        if (value.amount < count){
            return false
        }
        return true
    }

    fun findById(productId: Long): Product{
        val product = delegateDao.queryForId(productId)
        categoriesDao.refresh(product.category)
        return product
    }
}
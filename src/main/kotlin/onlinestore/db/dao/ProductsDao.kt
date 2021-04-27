package onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import onlinestore.models.Category
import onlinestore.models.Product
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

    fun buyProduct(productId: Long) : Boolean{
        val value = delegateDao.queryForId(productId)
        if (value.amount <= 0){
            return false
        }
        value.amount--
        value.bought++
        return delegateDao.update(value) == 1
    }
}
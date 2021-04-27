package onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import onlinestore.models.Category

class CategoriesDao(
    private val delegateDao: Dao<Category, Long>
) {
    fun getAll() = delegateDao.queryForAll()
}
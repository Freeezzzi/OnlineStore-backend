package onlinestore.services

import onlinestore.db.dao.CategoriesDao
import org.springframework.beans.factory.annotation.Autowired

class CategoryService(
    @Autowired val categoriesDao: CategoriesDao,
) {
    fun getAll() = categoriesDao.getAll()
}
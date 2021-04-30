package com.onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import com.onlinestore.models.Category

class CategoriesDao(
    private val delegateDao: Dao<Category, Long>
) {
    fun getAll() = delegateDao.queryForAll()
}
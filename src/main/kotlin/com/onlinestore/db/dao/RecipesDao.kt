package com.onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import com.onlinestore.models.Recipe

class RecipesDao(
    private val recipesDao: Dao<Recipe, Long>
) {
    fun getAll() = recipesDao.queryForAll()

    fun insertNew(recipe: Recipe):Boolean {
        return recipesDao.create(recipe) == 1
    }
}
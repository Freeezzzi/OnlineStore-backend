package com.onlinestore.services

import com.onlinestore.db.dao.RecipesDao
import com.onlinestore.models.Recipe
import org.springframework.beans.factory.annotation.Autowired

class RecipesService(
    @Autowired val recipesDao: RecipesDao
) {
    fun getAll():List<Recipe> = recipesDao.getAll()

    fun insertNewData(recipe:Recipe) = recipesDao.insertNew(recipe)
}
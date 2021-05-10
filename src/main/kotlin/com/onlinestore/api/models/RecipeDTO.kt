package com.onlinestore.api.models

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.onlinestore.models.Recipe

data class RecipeDTO(
    val id:Long? = null,
    val name : String = "",
    val difficulty: Int = Recipe.DIFFICULTY_EASY,
    val cookingTime: Int = 1, //1 minute
    val imageUrl: String = "",
    val ingredientNames:List<String> = ArrayList(),
    val ingredientCount:List<String> = ArrayList(),
    val ingredientIds:List<String> = ArrayList(),
    val stepsInfo:List<String> = ArrayList(),
    val stepsImages:List<String> = ArrayList(),
)

fun toRecipeDTO(recipe: Recipe):RecipeDTO =
    RecipeDTO(
        id = recipe.id,
        name = recipe.name,
        difficulty = recipe.difficulty,
        cookingTime = recipe.cookingTime,
        imageUrl = recipe.imageUrl,
        ingredientNames = recipe.ingredientNames,
        ingredientCount = recipe.ingredientCount,
        ingredientIds = recipe.ingredientIds,
        stepsInfo = recipe.stepsInfo,
        stepsImages = recipe.stepsImages
    )
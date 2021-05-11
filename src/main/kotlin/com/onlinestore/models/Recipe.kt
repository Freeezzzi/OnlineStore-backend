package com.onlinestore.models

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "recipes")
data class Recipe(
    @DatabaseField(generatedId = true)
    val id:Long? = null,
    @DatabaseField
    val name : String = "",
    @DatabaseField
    val difficulty: Int = DIFFICULTY_EASY,
    @DatabaseField
    val cookingTime: Int = 1, //1 minute
    @DatabaseField
    val imageUrl: String = "",
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val ingredientNames:ArrayList<String> = ArrayList(),
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val ingredientCount:ArrayList<String> = ArrayList(),
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val ingredientIds:ArrayList<Long> = ArrayList(),
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val stepsInfo:ArrayList<String> = ArrayList(),
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val stepsImages:ArrayList<String> = ArrayList(),
){
    companion object{
        const val DIFFICULTY_EASY = 0
        const val DIFFICULTY_MEDIUM = 1
        const val DIFFUCULTY_HARD = 2
    }
}

package onlinestore.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "categories")
data class Category(
    @DatabaseField(generatedId = true)
    val id: Long? = null,
    @DatabaseField
    val title: String,
    @DatabaseField
    val imageUrl : String = ""
){
    constructor() : this(
        title ="",
        imageUrl = ""
    )
}

package com.onlinestore.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "products")
data class Product(
    @DatabaseField(generatedId = true)
    val id: Long? = null,
    @DatabaseField
    val title: String,
    @DatabaseField(foreign = true)
    val category: Category,
    @DatabaseField
    var price: Long,
    @DatabaseField
    var amount: Long,
    @DatabaseField
    val imageUrl: String = "",
    @DatabaseField
    var bought : Long,
    @DatabaseField
    val country : String = "",
    @DatabaseField
    val brand : String = "",
    @DatabaseField
    var onSale: Boolean = false
){
    constructor() : this(title = "",price = 0,category = Category(),amount = 0, bought = 0)

}

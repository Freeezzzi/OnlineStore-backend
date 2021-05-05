package com.onlinestore.models

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "orders")
data class Order(
    @DatabaseField(generatedId = true)
    val id:Long? = null,
    @DatabaseField
    val user_id:Long = 0,
    @DatabaseField
    val status:Int = -1,
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val productsIds:ArrayList<Long> = ArrayList(),
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    val productsCount : ArrayList<Int> = ArrayList(),
    @DatabaseField
    val address: String = "",
    @DatabaseField
    val orderTime:String = "",
    @DatabaseField
    val deliveryTime:String = ""
)

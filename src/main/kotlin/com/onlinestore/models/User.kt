package com.onlinestore.models

import com.onlinestore.api.models.UserProfileDTO
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
data class User(
    @DatabaseField(generatedId = true)
    val id: Long = 0,
    @DatabaseField
    val pwd: String,
    @DatabaseField
    val name: String,
    @DatabaseField
    val phone : String,
    @DatabaseField
    val email: String,
    @DatabaseField
    var balance:Long
){
    constructor() : this(id = 0, email = "", pwd = "", name = "", phone = "", balance = 0)

}

fun User.toUserProfileDTO() : UserProfileDTO = UserProfileDTO(
    id = id,
    email = email,
    name = name,
    phone = phone,
    pwd = pwd,
    balance = balance,
)
package com.onlinestore.api.models

import com.onlinestore.models.User

data class UserProfileDTO(
    val id: Long,
    val pwd:String,
    val email: String,
    val name: String,
    val phone : String,
    val balance: Long
){
    fun toUser(): User = User(
        id = id,
        pwd = pwd,
        email = email,
        name = name,
        phone = phone,
        balance = balance
    )
}
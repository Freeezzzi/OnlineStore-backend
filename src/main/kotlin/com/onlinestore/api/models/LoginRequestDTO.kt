package com.onlinestore.api.models

data class LoginRequestDTO(
    val username: String, //can be phone or email
    val pwd: String
)
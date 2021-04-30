package com.onlinestore.service.models

import com.onlinestore.models.User

data class LoginResult(
    val success: Boolean,
    val token: String,
    val user: User?
)
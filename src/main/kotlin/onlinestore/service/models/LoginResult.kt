package onlinestore.service.models

import onlinestore.models.User

data class LoginResult(
    val success: Boolean,
    val token: String,
    val user: User?
)
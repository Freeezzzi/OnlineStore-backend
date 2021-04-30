package com.onlinestore.api.models

data class RegisterResponseDTO(
    val userProfile: UserProfileDTO,
    val token: String
)
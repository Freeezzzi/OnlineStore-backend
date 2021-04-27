package onlinestore.api.models

data class RegisterResponseDTO(
    val token: String,
    val userProfile: UserProfileDTO
)
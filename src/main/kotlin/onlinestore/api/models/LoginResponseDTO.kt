package onlinestore.api.models

data class LoginResponseDTO(
    val userProfile: UserProfileDTO,
    val token: String
)
package onlinestore.api.models

data class RegisterRequestDTO(
    val pwd: String,
    val name: String,
    val email : String,
    val phone : String
)
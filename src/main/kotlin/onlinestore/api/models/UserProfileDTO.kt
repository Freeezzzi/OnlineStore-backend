package onlinestore.api.models

data class UserProfileDTO(
    val pwd:String,
    val email: String,
    val name: String,
    val phone : String,
    val balance: Long
)
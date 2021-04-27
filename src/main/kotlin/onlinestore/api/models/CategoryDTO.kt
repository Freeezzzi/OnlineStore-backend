package onlinestore.api.models

import onlinestore.models.Category

data class CategoryDTO(
    val id: Long,
    val title: String,
    val imageUrl : String,
)
fun fromCategory(category: Category) = CategoryDTO(
    id = category.id!!,
    title = category.title,
    imageUrl = category.imageUrl,
)
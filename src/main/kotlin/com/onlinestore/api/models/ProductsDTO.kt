package com.onlinestore.api.models

import com.onlinestore.models.Category
import com.onlinestore.models.Product

data class ProductsDTO(
    val id: Long,
    val title: String,
    val category: CategoryDTO,
    val price: Long,
    val amount: Long,
    val imageUrl: String,
    val bought : Long,
    val country : String,
    val brand: String,
    val onSale: Boolean,
    val weight: String
){
    fun toProduct():Product =
        Product(
            id = id,
            title = title,
            category =  Category(),
            price = price,
            amount = amount,
            imageUrl = imageUrl,
            bought = bought,
            country = country,
            brand = brand,
            onSale = onSale,
            weight = weight
        )
}

fun fromProduct(product: Product) = ProductsDTO(
    id = product.id!!,
    title = product.title,
    category = fromCategory(product.category),
    price = product.price,
    amount = product.amount,
    imageUrl = product.imageUrl,
    bought = product.bought,
    country = product.country,
    brand = product.brand,
    onSale = product.onSale,
    weight = product.weight
)
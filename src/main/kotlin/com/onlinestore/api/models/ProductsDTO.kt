package com.onlinestore.api.models

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
)

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
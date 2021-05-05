package com.onlinestore.api.models


import com.onlinestore.models.Order

data class OrderDTO(
    val id:Long,
    val user_id:Long,
    val status:Int,
    val productsIDs: List<Long>,
    val productsCount : List<Int>,
    val address: String,
    val orderTime:String,
    val deliveryTime:String
)

fun Order.toOrderDTO(): OrderDTO =
    OrderDTO(
        id = id!!,
        user_id = user_id,
        status = status,
        productsIDs = productsIds,
        productsCount = productsCount,
        address = address,
        orderTime = orderTime,
        deliveryTime = deliveryTime
    )
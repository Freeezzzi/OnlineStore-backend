package com.onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import com.onlinestore.api.models.OrderDTO
import com.onlinestore.models.Category
import com.onlinestore.models.Order
import com.onlinestore.models.Product
import com.onlinestore.models.User

class OrdersDao(
    private val delegateDAO: Dao<Order, Long>,
    private val usersDao: Dao<User, Long>,
    private val productsDao: Dao<Product, Long>,
    private val categoryDao:Dao<Category, Long>
) {
    fun saveOrder(orderDTO: OrderDTO){
        val order = Order(
            id = orderDTO.id,
            user_id = orderDTO.user_id,
            status =  orderDTO.status,
            productsIds = orderDTO.productsIDs as ArrayList<Long>,
            productsCount = orderDTO.productsCount as ArrayList<Int>,
            address = orderDTO.address,
            orderTime = orderDTO.orderTime,
            deliveryTime = orderDTO.deliveryTime
        )
        delegateDAO.create(order)
    }

    fun findByUserId(userId:Long) : List<Order> =
        delegateDAO.queryForEq("user_id", userId)
}
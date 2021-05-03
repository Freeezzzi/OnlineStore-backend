package com.onlinestore.services

import com.onlinestore.api.models.OrderDTO
import com.onlinestore.db.dao.OrdersDao
import com.onlinestore.db.dao.ProductsDao
import com.onlinestore.db.dao.UsersDao
import com.onlinestore.models.Order
import org.springframework.beans.factory.annotation.Autowired

class OrdersService(
    @Autowired private val ordersDao: OrdersDao,
    @Autowired private val usersDao: UsersDao,
    @Autowired private val productsDao: ProductsDao
) {
    fun placeOrder(order: OrderDTO):Boolean{
        //Проверка условий
        var sum = 0L
        order.productsIDs.forEachIndexed{ i, productId ->
            if (!productsDao.checkIsCanBeBought(productId,order.productsCount.elementAt(i) )){
                return false
            }
            val product = productsDao.findById(productId)
            sum += product.price * order.productsCount.elementAt(i)
        }
        if (!usersDao.isCanBuy(order.user_id, sum.toInt())){
            return false
        }

        //Изменение таблиц
        usersDao.buyProducts(order.user_id, if(sum.toInt() > 700) sum.toInt() else sum.toInt()+200)
        order.productsIDs.forEachIndexed{ i, productId ->
            productsDao.buyProduct(productId,order.productsCount.elementAt(i) )
        }

        ordersDao.saveOrder(order)
        return true
    }

    fun findForUser(userId:Long) = ordersDao.findByUserId(userId)
}
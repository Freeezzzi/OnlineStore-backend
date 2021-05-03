package com.onlinestore.api.controllers

import com.onlinestore.api.models.OrderDTO
import com.onlinestore.api.models.toOrderDTO
import com.onlinestore.services.LoginService
import com.onlinestore.services.OrdersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/orders")
class OrdersController(
    @Autowired val loginService: LoginService,
    @Autowired val ordersService: OrdersService
) {
    @PostMapping("place")
    fun placeOrder(
        @RequestHeader(UsersController.TOKEN_HEADER, required = false) token: String?,
        @RequestBody orderDTO: OrderDTO
    ) : Boolean {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return ordersService.placeOrder(orderDTO)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("by_id")
    fun findForUser(
        @RequestHeader(UsersController.TOKEN_HEADER, required = false) token: String?,
        @RequestParam("user_id") userId:Long
    ): List<OrderDTO>{
        if (token != null && loginService.getAuthInfo(token) != null) {
            return ordersService.findForUser(userId)
                .map { order -> order.toOrderDTO() }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
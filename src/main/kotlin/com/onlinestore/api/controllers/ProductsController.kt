package com.onlinestore.api.controllers

import com.onlinestore.api.controllers.UsersController.Companion.TOKEN_HEADER
import com.onlinestore.api.models.ProductsDTO
import com.onlinestore.api.models.fromProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import com.onlinestore.services.LoginService
import com.onlinestore.services.ProductsService

@RestController
@RequestMapping("/products")
class ProductsController(
    @Autowired val productsService: ProductsService,
    @Autowired val loginService: LoginService
) {
    @GetMapping("popular")
    fun getPopular(
        @RequestHeader(TOKEN_HEADER) token: String?
    ): List<ProductsDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return productsService.getPopular()
                .map { product -> fromProduct(product = product) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("by-category")
    fun getByCategory(
        @RequestHeader(TOKEN_HEADER) token: String?,
        @RequestParam("categoryId") categoryId: Long
    ) : List<ProductsDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return productsService.getByCategory(categoryId)
                .map { product -> fromProduct(product = product) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("on-sale")
    fun getOnSale(
        @RequestHeader(TOKEN_HEADER) token: String?
    ) : List<ProductsDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return productsService.getOnSale()
                .map { product -> fromProduct(product = product) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("buy")
    fun buyProduct(
        @RequestHeader(TOKEN_HEADER) token: String?,
        @RequestParam("productId") productId : Long
    ){
        if (token != null && loginService.getAuthInfo(token) != null) {
            if(productsService.buyProduct(productId)){
                throw ResponseStatusException(HttpStatus.ACCEPTED)
            }
            else{
                throw ResponseStatusException(HttpStatus.GONE)
            }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("by_ids")
    fun getByIds(
        @RequestHeader(TOKEN_HEADER) token: String?,
        @RequestParam("productIds") productIds : List<Long>
    ): List<ProductsDTO>{
        if (token != null && loginService.getAuthInfo(token) != null) {
            val list = ArrayList<ProductsDTO>()
            productIds.forEach {
                list.add(fromProduct(productsService.findById(it)))
            }
            return list
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
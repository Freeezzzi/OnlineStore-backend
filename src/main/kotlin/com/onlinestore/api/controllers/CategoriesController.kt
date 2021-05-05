package com.onlinestore.api.controllers

import com.onlinestore.api.models.CategoryDTO
import com.onlinestore.api.models.fromCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import com.onlinestore.services.CategoryService
import com.onlinestore.services.LoginService

@RestController
@RequestMapping("/categories")
class CategoriesController(
    @Autowired val categoryService: CategoryService,
    @Autowired val loginService: LoginService
) {
    @GetMapping("all")
    fun getAll(
        @RequestHeader(UsersController.TOKEN_HEADER) token: String?
    ) : List<CategoryDTO>{
        if (token != null && loginService.getAuthInfo(token) != null){
            return categoryService.getAll()
                .map {category -> fromCategory(category) }
        }
        else{
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
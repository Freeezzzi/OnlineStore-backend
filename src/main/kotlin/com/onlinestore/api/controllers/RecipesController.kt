package com.onlinestore.api.controllers

import com.onlinestore.api.models.OrderDTO
import com.onlinestore.api.models.RecipeDTO
import com.onlinestore.api.models.toOrderDTO
import com.onlinestore.api.models.toRecipeDTO
import com.onlinestore.services.LoginService
import com.onlinestore.services.RecipesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/recipes")
class RecipesController(
    @Autowired val recipesService: RecipesService,
    @Autowired val loginService: LoginService
) {
    @GetMapping("all")
    fun findForUser(
        @RequestHeader(UsersController.TOKEN_HEADER) token: String?
    ): List<RecipeDTO>{
        if (token != null && loginService.getAuthInfo(token) != null) {
            return recipesService.getAll()
                .map { recipe -> toRecipeDTO(recipe) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
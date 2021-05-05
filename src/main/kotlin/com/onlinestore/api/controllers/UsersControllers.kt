package com.onlinestore.api.controllers

import com.onlinestore.api.models.UserProfileDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import com.onlinestore.services.LoginService
import com.onlinestore.services.UsersService

@RestController
@RequestMapping("/user")
class UsersController(
    @Autowired val loginService: LoginService,
    @Autowired val usersService: UsersService
) {
    @PostMapping("update-fcm-token")
    fun updateFcmToken(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?,
        @RequestBody fcmToken: String
    ) {
        if (token == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            loginService.updateAuthInfo(token = token, fcmToken = fcmToken, userId = authInfo.userId)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("update")
    fun updateUser(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?,
        @RequestBody userProfileDTO: UserProfileDTO
    ): Boolean{
        if (token != null && loginService.getAuthInfo(token) != null) {
            return usersService.updateUser(userProfileDTO.toUser())
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
    companion object {
        const val TOKEN_HEADER = "Token"
    }
}
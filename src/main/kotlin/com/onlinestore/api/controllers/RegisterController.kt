package com.onlinestore.api.controllers

import com.onlinestore.services.RegisterService
import com.onlinestore.api.models.RegisterRequestDTO
import com.onlinestore.api.models.RegisterResponseDTO
import com.onlinestore.models.toUserProfileDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import com.onlinestore.services.ExistingEntityException

@RestController
@RequestMapping("/register")
class RegisterController(
    @Autowired val registerService: RegisterService
) {

    @PostMapping
    fun register(
        @RequestBody registerRequestDTO: RegisterRequestDTO
    ): RegisterResponseDTO =
        try {
            val registerResult = registerService.register(
                pwd = registerRequestDTO.pwd,
                name = registerRequestDTO.name,
                phone = registerRequestDTO.phone,
                email = registerRequestDTO.email
            )
            RegisterResponseDTO(
                token = registerResult.token,
                userProfile = registerResult.user!!.toUserProfileDTO()
            )
        } catch (e: ExistingEntityException) {
            throw ResponseStatusException(HttpStatus.CONFLICT)
        }
}
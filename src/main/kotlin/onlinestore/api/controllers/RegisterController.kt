package onlinestore.api.controllers

import onlinestore.api.models.RegisterRequestDTO
import onlinestore.api.models.RegisterResponseDTO
import onlinestore.models.toUserProfileDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import onlinestore.services.ExistingEntityException
import onlinestore.services.RegisterService

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
                registerResult.user!!.toUserProfileDTO()
            )
        } catch (e: ExistingEntityException) {
            throw ResponseStatusException(HttpStatus.CONFLICT)
        }
}
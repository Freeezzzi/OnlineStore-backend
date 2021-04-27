package onlinestore.api.controllers

import onlinestore.api.controllers.UsersController.Companion.TOKEN_HEADER
import onlinestore.api.models.ProductsDTO
import onlinestore.api.models.fromProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import onlinestore.services.LoginService
import onlinestore.services.ProductsService

@RestController
@RequestMapping("/products")
class ProductsController(
    @Autowired val productsService: ProductsService,
    @Autowired val loginService: LoginService
) {
    @GetMapping("popular")
    fun getPopular(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?
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
        @RequestHeader(TOKEN_HEADER, required = false) token: String?,
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
        @RequestHeader(TOKEN_HEADER, required = false) token: String?
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
        @RequestHeader(TOKEN_HEADER, required = false) token: String?,
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
}
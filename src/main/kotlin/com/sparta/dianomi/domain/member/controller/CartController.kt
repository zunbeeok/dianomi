package com.sparta.dianomi.domain.member.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.member.dto.CartCreateDto
import com.sparta.dianomi.domain.member.dto.CartDto
import com.sparta.dianomi.domain.member.model.Cart
import com.sparta.dianomi.domain.member.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartController(private val cartService: CartService) {

    @PostMapping
    fun addToCart(@AuthenticationPrincipal user: UserPrincipal, @RequestBody cartCreateDto: CartCreateDto): ResponseEntity<CartDto> {
        val memberId = user.id
        val cart = cartService.addToCart(cartCreateDto.menuId, cartCreateDto.storeId, memberId, cartCreateDto.count)
        return ResponseEntity.status(HttpStatus.CREATED).body(CartDto.from(cart))
    }

    @DeleteMapping("/{cartId}")
    fun removeFromCart(@AuthenticationPrincipal user: UserPrincipal, @PathVariable cartId: Long): ResponseEntity<String> {
        val memberId = user.id
        return try {
            cartService.removeFromCart(memberId, cartId)
            ResponseEntity.ok("성공적으로 제거하였습니다.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("제거에 실패했습니다.")
        }
    }

    @DeleteMapping("/member/{memberId}")
    fun clearCart(@AuthenticationPrincipal user: UserPrincipal, @PathVariable memberId: String): ResponseEntity<String> {
        val memberId = user.id
        return try {
            cartService.clearCart(memberId)
            ResponseEntity.ok("성공적으로 제거하였습니다.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("제거에 실패했습니다.")
        }
    }
    @GetMapping("/{cartId}")
    fun getCart(@AuthenticationPrincipal user: UserPrincipal, @PathVariable cartId: Long): ResponseEntity<out Any> {
        val memberId = user.id
        return try {
            val cart = cartService.getCart(memberId, cartId)
            if (cart.member.id != memberId) {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view this cart.")
            } else {
                ResponseEntity.ok(CartDto.from(cart))
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회할 수 없습니다.")
        }
    }

}


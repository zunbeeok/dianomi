package com.sparta.dianomi.domain.member.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.member.dto.CartCreateDto
import com.sparta.dianomi.domain.member.dto.CartDto
import com.sparta.dianomi.domain.member.service.CartService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartController(private val cartService: CartService) {

    @PostMapping
    @Operation(summary = "카트에 아이템 추가", description = "주어진 메뉴 ID, 스토어 ID, 회원 ID, 수량에 해당하는 아이템을 카트에 추가합니다.")
    fun addToCart(@AuthenticationPrincipal user: UserPrincipal, @RequestBody cartCreateDto: CartCreateDto): ResponseEntity<CartDto> {
        val memberId = user.id
        val cart = cartService.addToCart(cartCreateDto.menuId, cartCreateDto.storeId, memberId, cartCreateDto.count)
        return ResponseEntity.status(HttpStatus.CREATED).body(CartDto.from(cart))
    }

    @DeleteMapping("/{cartId}")
    @Operation(summary = "카트에서 아이템 제거", description = "주어진 회원 ID, 카트 ID에 해당하는 아이템을 카트에서 제거합니다.")
    fun removeFromCart(@AuthenticationPrincipal user: UserPrincipal, @PathVariable cartId: Long): ResponseEntity<String> {
        val memberId = user.id
        return try {
            cartService.removeFromCart(memberId, cartId)
            ResponseEntity.ok("성공적으로 제거하였습니다.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message.toString())
        }
    }

    @DeleteMapping("/member")
    @Operation(summary = "카트 비우기", description = "주어진 회원 ID에 해당하는 회원의 카트를 비웁니다.")
    fun clearCart(@AuthenticationPrincipal user: UserPrincipal): ResponseEntity<String> {
        val memberId = user.id
        return try {
            cartService.clearCart(memberId)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message.toString())
        }
    }
    @GetMapping()
    @Operation(summary = "카트 조회", description = "주어진 회원 ID, 카트 ID에 해당하는 카트를 조회합니다.")
    fun getCarts(@AuthenticationPrincipal user: UserPrincipal): ResponseEntity<out Any> {
        val memberId = user.id
        return try{
            val carts = cartService.getCarts(memberId)
            val cartDto = carts.map { CartDto.from(it) }
            ResponseEntity.ok(cartDto)

        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message.toString())
        }

    }

}

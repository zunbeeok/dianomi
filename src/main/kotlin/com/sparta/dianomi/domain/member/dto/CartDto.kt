package com.sparta.dianomi.domain.member.dto

import com.sparta.dianomi.domain.member.model.Cart

data class CartDto(
    val id: Long?,
    val storeId: Long?,
    val menuId: Long?,
    val memberId: Long?,
    val count: Int?
) {
    companion object {
        fun from(cart: Cart): CartDto {
            return CartDto(
                id = cart.id,
                storeId = cart.store.id,
                menuId = cart.menu.id,
                memberId = cart.member.id,
                count = cart.count
            )
        }
    }
}

package com.sparta.dianomi.domain.member.service

import com.sparta.dianomi.domain.member.model.Cart

interface CartService {
    fun addToCart(menuId: Long, storeId: Long, memberId: Long, count: Int): Cart
    fun getCarts(memberId: Long): List<Cart>
    fun removeFromCart(memberId: Long, cartId: Long)
    fun clearCart(memberId: Long)
}

package com.sparta.dianomi.domain.member.service

import com.sparta.dianomi.domain.member.model.Cart
import com.sparta.dianomi.domain.member.repository.CartRepository
import com.sparta.dianomi.domain.member.repository.MemberRepository
import com.sparta.dianomi.domain.store.repository.MenuRepository
import com.sparta.dianomi.domain.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val menuRepository: MenuRepository,
    private val storeRepository: StoreRepository,
    private val memberRepository: MemberRepository
) : CartService {
    override fun addToCart(menuId: Long, storeId: Long, memberId: Long, count: Int): Cart {
        val menu = menuRepository.findById(menuId).orElseThrow { Exception("Menu not found") }
        val store = storeRepository.findById(storeId).orElseThrow { Exception("Store not found") }
        val member = memberRepository.findById(memberId).orElseThrow { Exception("Member not found") }
        val cart = Cart(menu = menu, store = store, member = member, count = count)
        return cartRepository.save(cart)
    }

    override fun removeFromCart(memberId: Long, cartId: Long) {
        val cart = cartRepository.findById(cartId).orElseThrow { Exception("Cart not found") }
        if (cart.member.id != memberId) {
            throw IllegalAccessException("Not authorized to remove this cart.")
        }
        cartRepository.deleteById(cartId)
    }

    override fun clearCart(memberId: Long) {
        val member = memberRepository.findById(memberId).orElseThrow { Exception("Member not found") }
        val carts = cartRepository.findAllByMember(member)
        cartRepository.deleteAll(carts)
    }

    override fun getCart(memberId: Long, cartId: Long): Cart {
        val cart = cartRepository.findById(cartId).orElseThrow { Exception("Cart not found") }
        if (cart.member.id != memberId) {
            throw IllegalAccessException("Not authorized to view this cart.")
        }
        return cart
    }
}


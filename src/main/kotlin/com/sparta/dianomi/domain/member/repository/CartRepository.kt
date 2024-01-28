package com.sparta.dianomi.domain.member.repository

import com.sparta.dianomi.domain.member.model.Cart
import com.sparta.dianomi.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
    fun findAllByMember(member: Member): List<Cart>
}
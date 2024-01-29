package com.sparta.dianomi.domain.order.repository

import com.sparta.dianomi.domain.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository :JpaRepository<Order,Long>{
    fun findAllByUserId(userId:Long):List<Order>
}
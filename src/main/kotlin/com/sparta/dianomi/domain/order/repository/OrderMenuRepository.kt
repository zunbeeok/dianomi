package com.sparta.dianomi.domain.order.repository

import com.sparta.dianomi.domain.order.model.OrderMenu
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository :JpaRepository<OrderMenu,Long>{
}
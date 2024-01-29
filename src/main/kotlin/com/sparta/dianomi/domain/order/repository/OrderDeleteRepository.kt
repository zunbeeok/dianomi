package com.sparta.dianomi.domain.order.repository

import com.sparta.dianomi.domain.order.model.OrderDelete
import org.springframework.data.jpa.repository.JpaRepository

interface OrderDeleteRepository :JpaRepository<OrderDelete,Long>{
}
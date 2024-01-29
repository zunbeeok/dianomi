package com.sparta.dianomi.domain.order.dto

import com.sparta.dianomi.domain.order.model.Order
import com.sparta.dianomi.domain.order.model.OrderStatus
import com.sparta.dianomi.domain.store.model.Store
import java.time.LocalDateTime

class OrderListResponse(

){

    fun from(orders:List<Order>):List<OrderInfo>{
        return orders.map {

            OrderInfo(
                it.id!!,
                it.createdAt,
                it.totalPrice,
                it.status
            )
        }
    }
}

data class OrderInfo(
    val orderId:Long,
    val createdAt :LocalDateTime,
    val totalPrice : Long,
    val status: OrderStatus
)




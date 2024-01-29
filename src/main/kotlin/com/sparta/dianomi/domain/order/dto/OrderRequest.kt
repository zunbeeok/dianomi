package com.sparta.dianomi.domain.order.dto

import com.sparta.dianomi.domain.order.model.Order

data class OrderRequest (
    val menuList:List<OrderMenuInfo>,
    val requestPrice : Long,
    val couponId:Long?,

){
    fun toEntity(userId:Long):Order{
        return Order(
            userId,
            this.requestPrice,
            this.couponId
        )
    }
}

data class OrderMenuInfo(
    val count:Long,
    val menuId:Long,
)
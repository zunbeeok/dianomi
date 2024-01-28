package com.sparta.dianomi.domain.coupon.dto

data class CouponResponseDto(
    val id:Long,
    val name:String,
    val discount:Double,
    val publisher:String,
    val maxCount:Int,
    val issuedCount:Int,
    val userId:Long,
    val storeId:Long?
)

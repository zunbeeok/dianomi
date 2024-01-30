package com.sparta.dianomi.domain.coupon.dto

data class IssuedCouponResponseDto(
    val id:Long,
    val couponId:Long,
    val name:String,
    val discount:Double,
)

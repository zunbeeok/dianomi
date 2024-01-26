package com.sparta.dianomi.domain.coupon.dto

data class CreateCouponDto(
    val name:String,
    val discount:Int,
    val publisher:String,
    val maxCount:Int
)

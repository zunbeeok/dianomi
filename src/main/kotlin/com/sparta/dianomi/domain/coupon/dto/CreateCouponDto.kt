package com.sparta.dianomi.domain.coupon.dto

data class CreateCouponDto(
    val name:String,
    val discount:Double,
    val maxCount:Int,
)

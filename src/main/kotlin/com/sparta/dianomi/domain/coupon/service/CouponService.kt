package com.sparta.dianomi.domain.coupon.service

import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import jakarta.persistence.Id

interface CouponService {
    fun createCoupon(createCouponDto: CreateCouponDto):CouponResponseDto

    fun getCoupon(couponId: Long):CouponResponseDto

    fun getCouponList():List<CouponResponseDto>
    //admin 이 한번에 검색관리 하는 느낌

    fun deleteCoupon(couponId:Long)
}

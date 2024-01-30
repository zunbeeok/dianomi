package com.sparta.dianomi.domain.coupon.service

import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import com.sparta.dianomi.domain.coupon.dto.IssuedCouponResponseDto
import jakarta.persistence.Id

interface CouponService {
    fun createAdminCoupon(createCouponDto: CreateCouponDto, userId: Long): CouponResponseDto

    fun createStoreCoupon(createCouponDto: CreateCouponDto, userId: Long,storeId: Long): CouponResponseDto

    fun getCoupon(couponId: Long):CouponResponseDto

    fun getCouponList():List<CouponResponseDto>

    fun deleteCoupon (couponId:Long,userId: Long,storeId:Long?)

    fun createIssuedCoupon(couponId: Long , userId:Long): IssuedCouponResponseDto
}

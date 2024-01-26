package com.sparta.dianomi.domain.coupon.repository

import com.sparta.dianomi.domain.coupon.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository:JpaRepository<Coupon,Long> {
}
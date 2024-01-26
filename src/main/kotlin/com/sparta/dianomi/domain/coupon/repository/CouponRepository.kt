package com.sparta.dianomi.domain.coupon.repository

import com.sparta.dianomi.domain.coupon.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository:JpaRepository<Coupon,Long> {
}

//여기에 쿠폰의 기본정보들. id / 할인율 등등등... 이 저장되는
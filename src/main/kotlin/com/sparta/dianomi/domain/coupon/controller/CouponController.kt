package com.sparta.dianomi.domain.coupon.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import com.sparta.dianomi.domain.coupon.service.CouponService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/coupons")
@RestController
class CouponController(
    private val couponService: CouponService
) {
    @PostMapping
    @PreAuthorize("hasRole('STORE') or hasRole('ADMIN') ")
    fun createCoupon(
        @RequestBody createCouponDto: CreateCouponDto,
    ):ResponseEntity<CouponResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(couponService.createCoupon(createCouponDto))
    }// 사업자와 어드민만 발행할수있다

    @GetMapping("/{couponId}")
    @PreAuthorize("hasRole('STORE') or hasRole('ADMIN') ")
    fun getCoupon(
        @PathVariable couponId:Long
    ):ResponseEntity<CouponResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCoupon(couponId))
    }
    @GetMapping
    @PreAuthorize(" hasRole('ADMIN') ")
    fun getCouponList():ResponseEntity<List<CouponResponseDto>>{
        //admin 만 가능
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCouponList())
    }

    @DeleteMapping("/{couponId}")
    @PreAuthorize("hasRole('STORE') or hasRole('ADMIN') ")
    fun deleteCoupon(
        @PathVariable couponId:Long
    ):ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }




}//end
package com.sparta.dianomi.domain.coupon.controller

import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import com.sparta.dianomi.domain.coupon.service.CouponService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping
@RestController
class IssuedController(
    private val couponService: CouponService
) {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    fun createIssuedCoupon(
        @RequestBody createCouponDto: CreateCouponDto,
        @AuthenticationPrincipal user: UserPrincipal,
    ): ResponseEntity<CouponResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(couponService.createCoupon(createCouponDto,user.id))
    }

}
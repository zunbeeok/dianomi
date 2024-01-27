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

@RequestMapping("/coupons")
@RestController
class CouponController(
    private val couponService: CouponService
) {
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun createAdminCoupon(
        @RequestBody createCouponDto: CreateCouponDto,
        @AuthenticationPrincipal user: UserPrincipal,
    ): ResponseEntity<CouponResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(couponService.createAdminCoupon(createCouponDto, user.id))
    }

    @PostMapping("/store/{storeId}")
    @PreAuthorize("hasRole('STORE')")
    fun createStoreCoupon(
        @RequestBody createCouponDto: CreateCouponDto,
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable storeId: Long
    ): ResponseEntity<CouponResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(couponService.createStoreCoupon(createCouponDto, user.id,storeId))
    }


    @GetMapping("/{couponId}")
    fun getCoupon(
        @PathVariable couponId:Long,
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<CouponResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCoupon(couponId))
    }
    @GetMapping
    fun getCouponList(@AuthenticationPrincipal user: UserPrincipal):ResponseEntity<List<CouponResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCouponList())
    }


    @DeleteMapping("/{couponId}/{storeId}")
    @PreAuthorize("hasRole('STORE') or hasRole('ADMIN') ")
    fun deleteCoupon( @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable couponId:Long, @PathVariable storeId:Long?
    ):ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
//storeId 받아서 삭제


}//end
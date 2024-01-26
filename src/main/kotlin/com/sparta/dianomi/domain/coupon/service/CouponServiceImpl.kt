package com.sparta.dianomi.domain.coupon.service

import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import com.sparta.dianomi.domain.coupon.model.Coupon
import com.sparta.dianomi.domain.coupon.model.Publisher
import com.sparta.dianomi.domain.coupon.repository.CouponRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CouponServiceImpl(
    private val couponRepository: CouponRepository
):CouponService{
    override fun createCoupon(createCouponDto: CreateCouponDto): CouponResponseDto {
        val publisherVal = when(createCouponDto.publisher){
            "ADMIN" -> Publisher.ADMIN
            "STORE" -> Publisher.STORE
            else -> throw IllegalArgumentException("Invalid Publisher Value")
        }//열거형 타입 맞춰주고 dto값을 열거형으로 매핑
        return couponRepository.save(
                Coupon(
                    name = createCouponDto.name,
                    discount = createCouponDto.discount,
                    publisher = publisherVal,
                    maxCount = createCouponDto.maxCount
            )
        ).toResponse()
    }

    override fun getCoupon(couponId: Long): CouponResponseDto {
        val findCoupon = couponRepository.findByIdOrNull(couponId)?: throw ModelNotFoundException("coupon",couponId)
        return findCoupon.toResponse()
    }

    override fun getCouponList(): List<CouponResponseDto> {
        return couponRepository.findAll().map { it.toResponse() }
    }

    override fun deleteCoupon(couponId: Long) {
        val findCoupon = couponRepository.findByIdOrNull(couponId)?: throw ModelNotFoundException("coupon",couponId)
        couponRepository.delete(findCoupon)
    }

    companion object{
        //계산 하는 거,할인율에 따른 금액 나오는거 메서드
        // 여기서 계산할지 아니면 userCouponService로 가서 적어야하는지?
    }
}
package com.sparta.dianomi.domain.coupon.service

import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import com.sparta.dianomi.domain.coupon.dto.CreateCouponDto
import com.sparta.dianomi.domain.coupon.dto.IssuedCouponResponseDto
import com.sparta.dianomi.domain.coupon.model.Coupon
import com.sparta.dianomi.domain.coupon.model.Publisher
import com.sparta.dianomi.domain.coupon.repository.CouponRepository
import com.sparta.dianomi.domain.coupon.repository.IssuedRepository
import com.sparta.dianomi.domain.member.model.Member
import com.sparta.dianomi.domain.member.model.MemberRole
import com.sparta.dianomi.domain.member.repository.MemberRepository
import com.sparta.dianomi.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CouponServiceImpl(
    private val couponRepository: CouponRepository,
    private val issuedRepository: IssuedRepository,
    private val memberRepository: MemberRepository,
    private val storeRepository: StoreRepository
):CouponService{


    override fun createCoupon(createCouponDto: CreateCouponDto , userId:Long): CouponResponseDto {
        val member: Member = memberRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("Model",userId)
        val publisher = when (member.role.toString()) {
            "ADMIN" -> Publisher.ADMIN
            "STORE" -> Publisher.STORE
            else -> throw IllegalArgumentException("Invalid role")
        }
        val couponSave =
            couponRepository.save(
                Coupon(
                    name = createCouponDto.name,
                    discount = createCouponDto.discount,
                    publisher = publisher,
                    maxCount = createCouponDto.maxCount,
                    issued = false,
                    userId = member.id!!,
                )
            )
        return couponSave.toResponse()
    }
     //현재까지는 잘 작동합니다.
     //TODO: 생성시 역할이 STORE일 경우 STORE 아이디도 DB에 기록이 되고, ADMIN일 경우 storeId 테이블이 null값으로 오게 하려고 했습니다.


    override fun createIssuedCoupon(couponId: Long): IssuedCouponResponseDto {
        TODO("일단 보류")
    }

    override fun getCoupon(couponId: Long): CouponResponseDto {
        val findCoupon = couponRepository.findByIdOrNull(couponId)?: throw ModelNotFoundException("coupon",couponId)
        return findCoupon.toResponse()
    }

    override fun getCouponList(): List<CouponResponseDto> {
        return couponRepository.findAll().map { it.toResponse() }
    }

    override fun deleteCoupon(couponId: Long, userId: Long, storeId: Long?) {
        val findCoupon = couponRepository.findByIdOrNull(couponId) ?: throw ModelNotFoundException("coupon", couponId)
        val member: Member = memberRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("Model", userId)
        val findStore = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("store", storeId)

        if (member.role.toString() == "ADMIN") {
            couponRepository.delete(findCoupon)
        } else if (member.role.toString() == "STORE") {
            if (findStore.userId == member.id) {
                //스토어 아이디로 조회한 Store의 user_id가 val member(인자값으로 받은userId)랑 같다면 지워도 된다
                couponRepository.delete(findCoupon)
            } else {
                throw Exception("가게의 주인이 아니여서 권한이 없습니다")
            }
            //create 부분에 storeId 부분을 만들지 못해서 검증을 못했습니다!
        }
    }
}

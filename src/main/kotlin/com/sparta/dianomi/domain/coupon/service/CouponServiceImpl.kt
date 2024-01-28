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
import com.sparta.dianomi.domain.member.repository.MemberRepository
import com.sparta.dianomi.domain.store.model.Store
import com.sparta.dianomi.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponServiceImpl(
    private val couponRepository: CouponRepository,
    private val issuedRepository: IssuedRepository,
    private val memberRepository: MemberRepository,
    private val storeRepository: StoreRepository
):CouponService{


    override fun createAdminCoupon(createCouponDto: CreateCouponDto, userId: Long): CouponResponseDto {
        val couponSave =
            couponRepository.save(
                Coupon(
                    name = createCouponDto.name,
                    discount = createCouponDto.discount,
                    publisher = Publisher.ADMIN,
                    maxCount = createCouponDto.maxCount,
                    userId = userId,
                    issued = false,
                    storeId = null
                )//ADMIN이 발행시에는 storeId는 null임
            )
        return couponSave.toResponse()
    }

    override fun createStoreCoupon(createCouponDto: CreateCouponDto, userId: Long,storeId: Long): CouponResponseDto {
        val userStores: List<Store> = storeRepository.findStoreIdsByUserId(userId)
        val selectStore: Store? = userStores.find { it.id == storeId }
        if (selectStore == null) {
            throw IllegalArgumentException("storeId가 존재 하지 않거나,userId에 맞는 storeId가 아닙니다")
        }

        val couponSave = couponRepository.save(
            Coupon(
                name = createCouponDto.name,
                discount = createCouponDto.discount,
                publisher = Publisher.STORE,
                maxCount = createCouponDto.maxCount,
                userId = userId,
                issued = false,
                storeId = selectStore.id,
            )//store가 발행시에는 storeid를 넣어서 발행
        )

        return couponSave.toResponse()
    }


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

    @Transactional
    override fun deleteCoupon(couponId: Long, userId: Long, storeId: Long?) {
        val findCoupon = couponRepository.findByIdOrNull(couponId) ?: throw ModelNotFoundException("coupon", couponId)
        val member: Member = memberRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("Model", userId)

        //ADMIN의 역할을 가졋으면 뭐든 삭제 가능
        if (member.role.toString() == "ADMIN") {
            couponRepository.delete(findCoupon)
            //storeId를 가지고 있을 경우. 자기 가게인지 권한 검증
        } else if (storeId != null && findCoupon.publisher == Publisher.STORE) {
            val findStore = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("store", storeId)
            if (findStore.userId != member.id) {
                throw IllegalArgumentException("가게의 주인이 아니여서 권한이 없습니다")
            }

            couponRepository.delete(findCoupon)
        } else {
            throw IllegalArgumentException("해당 쿠폰을 삭제할 권한이 없습니다")
        }
    }
    }


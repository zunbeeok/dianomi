package com.sparta.dianomi.domain.coupon.repository

import com.sparta.dianomi.domain.coupon.model.Issued
import org.springframework.data.jpa.repository.JpaRepository

interface IssuedRepository:JpaRepository<Issued,Long> {

    fun findByCouponAndUserId(couponId : Long,userId:Long):Issued?
}

//사용한 쿠폰들이 오는 무덤
//비밀번호로 따지면 최근 사용 쿠폰들 -> false 가 true로 바뀐 쿠폰들이 여기서 잠을 잠

// 1. 쿠폰 레파지토리에 완성쿠폰들이 이씀.
// 3. 사용상태가 true가 되버린애들이 와서 저장되는곳이 issued
// 4. 이 이유는 환불같은거를 햇을시 issued에서 잠자고있는 true 친구들을 false로 바꿔주면서 다시 꺼낼수 있어야함.
// 5. 쿠폰 레파지토리는 단순한 발급을 위한 주체 ->실질적인 member가 사용하는 곳은 issued레파지토리를 사용

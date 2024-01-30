package com.sparta.dianomi.domain.coupon.model

import com.sparta.dianomi.domain.coupon.dto.CouponResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "coupon")
class Coupon(
    @Column(name = "name")
    var name: String,

    @Column(name = "discount")
    var discount: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "publisher")
    var publisher: Publisher,

    @Column(name = "max_count")
    var maxCount: Int,

    @Column(name = "user_id")
    val userId: Long,

    //true와 false로 사용유무 구분을 위함
    @Column(name = "issued")
    var issued: Boolean,

    @Column(name = "store_id")
    var storeId : Long? = null

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null

    @Column(name = "issued_count")
    var issuedCount:Int = 0

    fun toResponse():CouponResponseDto{
        return CouponResponseDto(
            id = this.id!!,
            name = this.name,
            discount = this.discount,
            publisher =this.publisher.name,
            maxCount =this.maxCount,
            issuedCount =this.issuedCount,
            userId = this.userId,
            storeId = this.storeId
        )
    }

    // max치를 만들어서 감소증가 접근제한하는 함수
    //

}
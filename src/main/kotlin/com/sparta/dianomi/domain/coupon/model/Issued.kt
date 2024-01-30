package com.sparta.dianomi.domain.coupon.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.*


@Entity
@Table(name = "coupon_issued")
class Issued(

    @Column(name = "coupon_id")
    val coupon: Long,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "coupon_discount")
    val couponDiscount: Double,

    @Column(name = "coupon_issued_count")
    val couponIssuedCount : Int,

    @Column(name = "is_used")
    var isUsed: Boolean


):BaseTimeEntity()
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun updateIssued() {
        this.isUsed = true
    }
}


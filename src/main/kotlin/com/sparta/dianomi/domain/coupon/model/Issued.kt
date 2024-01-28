package com.sparta.dianomi.domain.coupon.model

import jakarta.persistence.*


@Entity
@Table(name = "coupon_issued")
class Issued(

    @Column(name = "coupon_id")
    val coupon: Long,

    @Column(name = "coupon_discount")
    val couponDiscount: Int,

    @Column(name = "coupon_issued_count")
    val couponIssuedCount : Int



)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "coupon_issued")
    var couponIssued: Boolean = false

    fun updateIssued() {
        this.couponIssued = true
    }
}


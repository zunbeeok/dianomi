package com.sparta.dianomi.domain.order.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import com.sparta.dianomi.domain.coupon.model.Coupon
import jakarta.persistence.*
import org.springframework.data.annotation.Immutable

@Entity
@Table(name ="dianomi_order")
class Order (
    @Column(name = "user_id")
    val userId:Long,

    @Column(name = "total_price")
    val totalPrice:Long,

    @Column(name = "coupon_id", nullable = true)
    val couponId:Long?,
):BaseTimeEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null;

    @Column(name = "status", nullable = false)
    var status: OrderStatus = OrderStatus.PAYMENT;

    @Column(name = "is_deleted", nullable = true)
    var isDeleted:Boolean =false;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    val orderMenus:MutableList<OrderMenu> = ArrayList<OrderMenu>();

}
package com.sparta.dianomi.domain.order.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name="order_delete")
class OrderDelete(
    @Id
    @Column(name = "order_id", nullable =  false)
    val id:Long,

    @Column(name ="comment", nullable = false)
    val comment:String,

    @Column(name="user_id", nullable = false)
    val userId:Long,
):BaseTimeEntity(){}



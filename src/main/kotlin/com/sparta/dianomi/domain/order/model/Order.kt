package com.sparta.dianomi.domain.order.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.springframework.data.annotation.Immutable

@Entity
class Order (
    var status: OrderStatus,

    val count : Long =1,
):BaseTimeEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null;

    @OneToMany(mappedBy = "order")
    val orderMenus:List<OrderMenu> = ArrayList<OrderMenu>();
}
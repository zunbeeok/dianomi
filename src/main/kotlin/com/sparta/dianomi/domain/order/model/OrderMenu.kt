package com.sparta.dianomi.domain.order.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "order_menu")
class OrderMenu(

    @ManyToOne
    @JoinColumn(name = "id")
    val order:Order,


    val menuId: Long,

    val count : Long
) :BaseTimeEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null;
}
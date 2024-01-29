package com.sparta.dianomi.domain.order.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import com.sparta.dianomi.domain.store.model.Menu
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "order_menu")
class OrderMenu(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order:Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
     var menu:Menu,

    val count : Long,

) :BaseTimeEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null;


}
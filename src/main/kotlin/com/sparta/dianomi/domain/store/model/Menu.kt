package com.sparta.dianomi.domain.store.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.sparta.dianomi.common.model.BaseTimeEntity
import com.sparta.dianomi.domain.member.model.Cart
import jakarta.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")

@Table(name = "menu")
class Menu(
    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Column(name = "store_id", nullable = false)
    var storeId: Long,

    @OneToMany(mappedBy = "menu", targetEntity = Cart::class)
    var carts: List<Cart> = arrayListOf()


) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null



}
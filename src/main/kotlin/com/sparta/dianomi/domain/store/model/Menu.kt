package com.sparta.dianomi.domain.store.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.sparta.dianomi.common.model.BaseTimeEntity
import com.sparta.dianomi.domain.member.model.Cart
import jakarta.persistence.*

@Entity
@Table(name = "menu")
class Menu(
    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @ManyToOne
    @JoinColumn(name = "store_id")
    var store: Store,

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
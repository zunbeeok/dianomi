package com.sparta.dianomi.domain.member.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.sparta.dianomi.domain.store.model.Menu
import com.sparta.dianomi.domain.store.model.Store
import jakarta.persistence.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")

@Entity
@Table(name = "cart")
class Cart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member,

    @ManyToOne
    @JoinColumn(name = "menu_id")
    var menu: Menu,

    @ManyToOne
    @JoinColumn(name = "store_id")
    var store: Store,

    @Column(name = "count")
    var count: Int
)

package com.sparta.dianomi.domain.store.model

import com.sparta.dianomi.common.model.BaseTimeEntity
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

    @Column(name = "store_id", nullable = false)
    var storeId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


//    init {
//        this.store = store
//        store.menus.add(this)
//    }
}
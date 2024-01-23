package com.sparta.dianomi.domain.store.menu.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Menu(
    var name: String,
    var description: String,
    var price: Int
) : BaseTimeEntity() { // BaseTimeEntity 상속하는게 이거래!!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
package com.sparta.dianomi.domain.store.dto

import com.sparta.dianomi.domain.store.model.Menu

data class MenuDto(
    val id: Long?,
    val name: String,
    val description: String,
    val price: Int

) {

    companion object {
        fun from(menu: Menu): MenuDto {
            return MenuDto(
                id = menu.id,
                name= menu.name,
                description= menu.description,
                price= menu.price
            )
        }
    }
}
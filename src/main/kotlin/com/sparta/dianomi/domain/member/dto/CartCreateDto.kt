package com.sparta.dianomi.domain.member.dto

data class CartCreateDto(
    val storeId: Long,
    val menuId: Long,
    val count: Int
)
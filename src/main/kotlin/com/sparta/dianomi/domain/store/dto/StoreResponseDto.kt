package com.sparta.dianomi.domain.store.dto

data class StoreResponseDto(
    val id:Long,
    val name:String,
    val address:String,
    val description:String,
    val businessNum:String,
    val orderCount:Int,
    val reviewCount:Int
)

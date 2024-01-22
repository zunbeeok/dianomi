package com.sparta.dianomi.domain.store.dto

data class StoreResponseDto(
    val storeId:Long,
    val name:String,
    val address:String,
    val businessNum:String,
    val orderCount:Int,
    val reviewCount:Int
)

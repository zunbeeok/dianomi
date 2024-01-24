package com.sparta.dianomi.domain.store.dto

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address

data class CreateStoreDto(
    val name:String,
    val address:String,
    val businessNum:String,
    val description:String,
    val userId:Long
)

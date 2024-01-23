package com.sparta.dianomi.domain.member.dto

data class MemberResponse(
    val id : Long,
    val memberName: String,
    val nickName: String,
    val address : String,
    val phoneNumber : String,
    val role : String,
)

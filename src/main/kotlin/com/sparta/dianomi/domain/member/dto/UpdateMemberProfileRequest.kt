package com.sparta.dianomi.domain.member.dto

data class UpdateMemberProfileRequest(
    val nickName : String,
    val address : String,
    val phoneNumber: String,
)

package com.sparta.dianomi.domain.member.dto

data class LoginRequest(
   val memberName: String,
   val password: String,
   val role: String
)



package com.sparta.dianomi.domain.member.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(
    val name: String,
    val address: String,
    val phoneNumber: String,
    @field:Size(min = 4 , max = 10)
    @field:Pattern(regexp = "^[a-z0-9]*\$",
        message = "memberName은 소문자와 숫자로만 이루어져야 합니다")
    val memberName: String,
    @field:Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하여야 합니다")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[a-zA-Z\\d@$!%*?&]*\$",
        message = "비밀번호는 소문자,대문자,숫자,특수문자를 모두 포함해야 합니다"
    )
    val password: String,
    val nickName: String,
    val role: String
)

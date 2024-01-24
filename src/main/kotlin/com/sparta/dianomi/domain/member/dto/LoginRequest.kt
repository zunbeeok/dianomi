package com.sparta.dianomi.domain.member.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
   @Schema(description = "memberName은 4자이상 10자 이하, 소문자와 숫자로만 이루어져야 합니다", example = "1s2n")
   val memberName: String,
   @Schema(description = "비밀번호는 8자 이상 15자 이하, 소문자,대문자,숫자,특수문자를 모두 포함해야 합니다", example = "!2aZ3gfabf3")
   val password: String
)


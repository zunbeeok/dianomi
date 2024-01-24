package com.sparta.dianomi.domain.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @Schema(example = "name")
    val name: String,
    @Schema(example = "address")
    val address: String,
    @Schema(example = "01012345678")
    val phoneNumber: String,
    @Schema(description = "memberName은 4자이상 10자 이하, 소문자와 숫자로만 이루어져야 합니다", example = "1s2n")
    @field:Size(min = 4 , max = 10)
    @field:Pattern(regexp = "^[a-z0-9]*\$",
        message = "memberName은 소문자와 숫자로만 이루어져야 합니다")
    val memberName: String,
    @Schema(description = "비밀번호는 8자 이상 15자 이하, 소문자,대문자,숫자,특수문자를 모두 포함해야 합니다", example = "!2aZ3gfabf3")
    @field:Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하여야 합니다")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[a-zA-Z\\d@$!%*?&]*\$",
        message = "비밀번호는 소문자,대문자,숫자,특수문자를 모두 포함해야 합니다"
    )
    val password: String,
    @Schema(example = "nickname")
    val nickName: String,
    @Schema(example = "USER")
    val role: String
)


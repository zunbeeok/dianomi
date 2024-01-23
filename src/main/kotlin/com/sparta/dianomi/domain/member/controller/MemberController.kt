package com.sparta.dianomi.domain.member.controller


import com.sparta.dianomi.authority.security.UserPrincipal
import com.sparta.dianomi.domain.member.dto.*
import com.sparta.dianomi.domain.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/member/sign_up")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest) : ResponseEntity<MemberResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(signUpRequest))
    }

    @PostMapping("/member/login")
    fun login(@RequestBody loginRequest : LoginRequest) : ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.login(loginRequest))
    }

    @PutMapping("/member/info")
    fun updateMemberProfile(
        @RequestBody updateMemberProfileRequest: UpdateMemberProfileRequest,
        @AuthenticationPrincipal user: UserPrincipal,
    ): ResponseEntity<String> {
        val memberId= user.id
        return ResponseEntity.status(HttpStatus.OK)
            .body(memberService.updateMemberProfile(updateMemberProfileRequest,memberId))
    }

    @PutMapping("/member/password")
    fun updatePassword(@Valid @RequestBody updatePasswordRequest : UpdatePasswordRequest,
                       @AuthenticationPrincipal user: UserPrincipal,) : ResponseEntity<String> {
        val memberId= user.id
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updatePassword(updatePasswordRequest,memberId))
    }

    @DeleteMapping("/member")
    fun deleteMember(@AuthenticationPrincipal user: UserPrincipal,) : ResponseEntity<Unit> {
        val memberId= user.id
        memberService.deleteMember(memberId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
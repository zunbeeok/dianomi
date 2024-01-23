package com.sparta.dianomi.domain.member.service

import com.sparta.dianomi.domain.member.dto.*


interface MemberService {

    fun signUp(request : SignUpRequest): MemberResponse

    fun login(request: LoginRequest) : LoginResponse

    fun updateMemberProfile(request: UpdateMemberProfileRequest, memberId : Long) :String

    fun updatePassword(request: UpdatePasswordRequest, memberId : Long) :String

    fun deleteMember(memberId: Long)
}
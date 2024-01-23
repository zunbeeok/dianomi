package com.sparta.dianomi.domain.member.service

import com.sparta.dianomi.authority.jwt.JwtPlugin
import com.sparta.dianomi.common.exception.InvalidCredentialException
import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.member.dto.*
import com.sparta.dianomi.domain.member.model.Member
import com.sparta.dianomi.domain.member.model.MemberRole
import com.sparta.dianomi.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : MemberService {

    override fun signUp(request: SignUpRequest): MemberResponse {
        if (memberRepository.existsByMemberName(request.memberName)) {
            throw IllegalStateException("MemberName is already in use")
        }
        val role = when (request.role) {
            "ADMIN" -> MemberRole.ADMIN
            "USER" -> MemberRole.USER
            "STORE" -> MemberRole.STORE
            else -> throw IllegalArgumentException("Invalid role")
        }
        return memberRepository.save(
            Member(
                memberName = request.memberName,
                password = passwordEncoder.encode(request.password),
                address = request.address,
                nickName = request.nickName,
                phoneNumber = request.phoneNumber,
                role = role
            )
        ).let { MemberResponse(it.id!!,it.memberName,it.nickName,it.address,it.phoneNumber,it.role.toString()) }
    }

    override fun login(request: LoginRequest): LoginResponse {
        val memberFind =
            memberRepository.findByMemberName(request.memberName) ?: throw ModelNotFoundException("Member", null)
        if (memberFind.role.name != request.role || !passwordEncoder.matches(request.password, memberFind.password)) {
            throw InvalidCredentialException()
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = memberFind.id.toString(),
                memberName = memberFind.memberName,
                role = memberFind.role.name
            )
        )

    }

    @Transactional
    override fun updateMemberProfile(request: UpdateMemberProfileRequest, memberId: Long) :String{
        val memberFind = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", null)

        if(request.phoneNumber != memberFind.phoneNumber && memberRepository.existsByPhoneNumberAndIdNot(request.phoneNumber,memberId)) {
            throw IllegalStateException("Already using phone number")
            }
        if(request.nickName != memberFind.nickName && memberRepository.existsByNickNameAndIdNot(request.nickName,memberId)) {
            throw IllegalStateException("Already using nickname")
        }

        memberFind.nickName = request.nickName
        memberFind.address = request.address
        memberFind.phoneNumber = request.phoneNumber

        memberRepository.save(memberFind)
        return "수정되었습니다"
    }

    @Transactional
    override fun updatePassword(request: UpdatePasswordRequest, memberId: Long):String {
        val memberFind = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", null)

        if (!passwordEncoder.matches(request.currentPassword, memberFind.password))
            throw InvalidCredentialException("Current password is not correct")

        if (passwordEncoder.matches(request.newPassword, memberFind.password)) {
            throw InvalidCredentialException("New password must be different from the current password")
        }
        memberFind.password = passwordEncoder.encode(request.newPassword)
        return "수정되었습니다"
    }


    @Transactional
    override fun deleteMember(memberId: Long) {
        val memberFind = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", null)
        memberRepository.delete(memberFind)
    }

}
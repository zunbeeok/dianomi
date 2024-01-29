package com.sparta.dianomi.domain.member.service

import com.sparta.dianomi.authority.jwt.JwtPlugin
import com.sparta.dianomi.common.exception.InvalidCredentialException
import com.sparta.dianomi.common.exception.ModelNotFoundException
import com.sparta.dianomi.domain.member.dto.*
import com.sparta.dianomi.domain.member.model.Member
import com.sparta.dianomi.domain.member.model.MemberPasswordRecord
import com.sparta.dianomi.domain.member.model.MemberRole
import com.sparta.dianomi.domain.member.repository.MemberPasswordRecordRepository
import com.sparta.dianomi.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
    private val memberPasswordRecordRepository: MemberPasswordRecordRepository
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
        if (!passwordEncoder.matches(request.password, memberFind.password)) {
            throw InvalidCredentialException("password not correct")
        }
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = memberFind.id.toString(),
                nickName = memberFind.nickName,
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

        //바꿀 비밀번호 두번 입력 받기
        if (request.newPassword != request.newPasswordCheck)
            throw InvalidCredentialException("바꿀 비밀번호 체크가 틀립니다")

        //memberId로 사용했던 비밀번호 가져오기
        var passwordRecord = memberPasswordRecordRepository.findByMember(memberId)

        //1. 비밀번호가 최근 3회에 있나 확인 -> recordRepository에서 찾기
        // 빠른순서 세개를 가져와서 any-> 그중 한개라도 있을경우 throw
        if(passwordRecord.take(3).any { passwordEncoder.matches(request.newPassword, it.password) })
            throw InvalidCredentialException("최근 3회 사용했던 비밀번호는 사용 할 수 없습니다.")

        //2. 비밀번호 record 저장 -> save를 recordRepository에 해주기
        val newPasswordRecord = MemberPasswordRecord(memberFind.id!!, passwordEncoder.encode(request.newPassword))
        memberPasswordRecordRepository.save(newPasswordRecord)

        //3. 비밀번호 record 된게 3개 이상 이면 오래된 것 부터 삭제
        //사이즈가 2를 넘어가게 되면 while로 돌게됨
        while(passwordRecord.size > 2) {
            //가장 오래된 비밀번호를 가져옴 > first()함수로 리스트에서 첫번째를 가져와서 변수에 할당해줌
            val passwordDelete = passwordRecord.first()
            //그 비밀번호를 삭제해버림
            memberPasswordRecordRepository.delete(passwordDelete)
            //filterNot을 사용하여 조건을 만족하지 않는(삭제된) 비밀번호를 제외한 나머지를 유지
            //기존 passwordRecord는 List(변경 불가능) 이기 때문에 변경가능한 리스트 타입인 MutableList로 변환해서 써야함
            passwordRecord = passwordRecord.filterNot { it == passwordDelete }.toMutableList()
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
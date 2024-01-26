package com.sparta.dianomi.domain.member.repository

import com.sparta.dianomi.domain.member.model.Member
import com.sparta.dianomi.domain.member.model.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByMemberName(memberName: String): Member?

    fun existsByMemberName(memberName: String): Boolean

    fun existsByPhoneNumberAndIdNot(phoneNumber: String, id: Long): Boolean

    fun existsByNickNameAndIdNot(nickName: String, id: Long): Boolean
}
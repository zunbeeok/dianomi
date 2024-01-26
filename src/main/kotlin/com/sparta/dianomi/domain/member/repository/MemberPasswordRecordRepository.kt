package com.sparta.dianomi.domain.member.repository

import com.sparta.dianomi.domain.member.model.Member
import com.sparta.dianomi.domain.member.model.MemberPasswordRecord
import org.springframework.data.jpa.repository.JpaRepository

interface MemberPasswordRecordRepository:JpaRepository<MemberPasswordRecord, Long> {
    fun findByMember(member:Member): List<MemberPasswordRecord>
}
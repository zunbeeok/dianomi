package com.sparta.dianomi.domain.member.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.*

@Table(name = "member_record")
@Entity
class MemberPasswordRecord(

    @Column(name = "member_id")
    val member: Long,

    @Column(name = "password" , nullable = false)
    val password: String,

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
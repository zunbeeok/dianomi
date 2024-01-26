package com.sparta.dianomi.domain.member.model

import com.sparta.dianomi.common.model.BaseTimeEntity
import jakarta.persistence.*

@Table(name = "record")
@Entity
class MemberPasswordRecord(

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    @Column(name = "password" , nullable = false)
    val password: String,

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
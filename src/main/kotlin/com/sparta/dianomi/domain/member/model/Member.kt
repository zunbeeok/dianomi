package com.sparta.dianomi.domain.member.model

import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
@Table(name = "member")
class Member(

//    - memberName은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
//- password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.


//    @Size(min = 4 , max = 10)
//    @Pattern(regexp = "^[a-z0-9]*\$")
    @Column(name = "id_name" , nullable = false , unique = true)
    val memberName: String,

//    @Size(min = 8 , max = 15)
//    @Pattern(regexp = "")
    @Column(name = "password" , nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: MemberRole,

    @Column(name = "nick_name" , nullable = false, unique = true)
    var nickName: String,

    @Column(name = "address" , nullable = false)
    var address: String,

    @Column(name = "hp" , nullable = false , unique = true)
    var phoneNumber: String,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


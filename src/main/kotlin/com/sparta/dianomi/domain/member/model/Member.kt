package com.sparta.dianomi.domain.member.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")

@Entity
@Table(name = "member")
class Member(


    @Column(name = "id_name" , nullable = false , unique = true)
    val memberName: String,

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


    @OneToMany(mappedBy = "member", targetEntity = Cart::class)
    var carts: List<Cart> = arrayListOf()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


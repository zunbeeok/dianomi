package com.sparta.dianomi.authority.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val nickName: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long , nickName: String , roles: Set<String>): this(
        id, nickName, roles.map { SimpleGrantedAuthority("ROLE_$it") }
    )
}

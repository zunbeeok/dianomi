package com.sparta.dianomi.authority.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val memberName: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long , memberName: String , roles: Set<String>): this(
        id, memberName, roles.map { SimpleGrantedAuthority("ROLE_$it") }
    )
}

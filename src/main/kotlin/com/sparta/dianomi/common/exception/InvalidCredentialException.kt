package com.sparta.dianomi.common.exception

data class InvalidCredentialException(
    override val message: String =  "The credential is invalid"
): RuntimeException()

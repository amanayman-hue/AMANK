package com.amank.parent.auth

sealed class AuthResult {
    data class Success(
        val uid: String,
        val email: String?
    ) : AuthResult()

    data class Failure(
        val message: String
    ) : AuthResult()
}

interface AuthRepository {

    suspend fun register(
        email: String,
        password: String
    ): AuthResult

    suspend fun signIn(
        email: String,
        password: String
    ): AuthResult
}

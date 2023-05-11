package org.android.go.sopt.domain.repository

interface AuthRepository {
    suspend fun signUp(id: String, password: String, name: String, skill: String): Boolean
    suspend fun signIn(id: String, password: String): Boolean
}
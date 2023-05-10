package org.android.go.sopt.domain.repository

interface AuthRepository {
    fun signUp(id: String, password: String, name: String, skill: String)
    fun signIn(id: String, password: String)
}
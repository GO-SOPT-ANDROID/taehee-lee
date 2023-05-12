package org.android.go.sopt.domain.repository

interface AuthRepository {
    fun clearPref()
    fun getAutoMode(): Boolean
    fun setAutoMode(isAutoMode: Boolean)

    suspend fun signUp(id: String, password: String, name: String, skill: String): Boolean
    suspend fun signIn(id: String, password: String): Boolean
}
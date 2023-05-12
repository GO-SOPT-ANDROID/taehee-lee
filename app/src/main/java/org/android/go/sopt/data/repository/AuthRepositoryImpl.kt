package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.local.AutoLoginDataSource
import org.android.go.sopt.data.datasource.remote.AuthDataSource
import org.android.go.sopt.data.model.RequestSignInDto
import org.android.go.sopt.data.model.RequestSignUpDto
import org.android.go.sopt.domain.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val autoLoginDataSource: AutoLoginDataSource,
) : AuthRepository {
    override fun clearPref() = autoLoginDataSource.clearPref()
    override fun getAutoMode(): Boolean = autoLoginDataSource.isAutoLogin

    override fun setAutoMode(isAutoMode: Boolean) {
        autoLoginDataSource.isAutoLogin = isAutoMode
    }

    override suspend fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String
    ): Boolean = runCatching {
        authDataSource.signUp(RequestSignUpDto(id, password, name, skill))
    }.fold({
        Timber.d(it.message)
        true
    }, {
        Timber.e(it.message)
        false
    })

    override suspend fun signIn(id: String, password: String): Boolean = runCatching {
        authDataSource.signIn(
            RequestSignInDto(id, password)
        )
    }.fold({
        Timber.d(it.message)
        true
    }, {
        Timber.e(it.message)
        false
    })

}
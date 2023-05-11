package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.model.RequestSignInDto
import org.android.go.sopt.data.model.RequestSignUpDto
import org.android.go.sopt.data.model.ResponseSignInDto
import org.android.go.sopt.data.model.ResponseSignUpDto
import org.android.go.sopt.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun signUp(signUpRequest: RequestSignUpDto): ResponseSignUpDto =
        authService.signUp(signUpRequest)

    suspend fun signIn(signInRequest: RequestSignInDto): ResponseSignInDto =
        authService.signIn(signInRequest)
}
package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.RequestSignInDto
import org.android.go.sopt.data.model.RequestSignUpDto
import org.android.go.sopt.data.model.ResponseSignInDto
import org.android.go.sopt.data.model.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("sign-up")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): ResponseSignUpDto

    @POST("sign-in")
    fun signIn(
        @Body request: RequestSignInDto,
    ): ResponseSignInDto
}
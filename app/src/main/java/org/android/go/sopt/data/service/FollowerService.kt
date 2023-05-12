package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.ResponseFollowerDto
import retrofit2.http.GET

interface FollowerService {
    @GET("/api/users?page=2")
    suspend fun fetchFollowerList(): ResponseFollowerDto
}
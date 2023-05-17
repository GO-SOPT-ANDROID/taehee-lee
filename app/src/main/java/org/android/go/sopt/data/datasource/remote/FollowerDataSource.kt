package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.model.ResponseFollowerDto
import org.android.go.sopt.data.service.FollowerService
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollowerService
) {
    suspend fun fetchFollowerList(): ResponseFollowerDto = followerService.fetchFollowerList()
}
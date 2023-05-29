package org.android.go.sopt.data.local

import org.android.go.sopt.domain.model.Follower

sealed class FollowerState {
    object Loading : FollowerState()
    data class Success(val followerList: List<Follower>) : FollowerState()
    data class Error(val errorMessage: String) : FollowerState()
}


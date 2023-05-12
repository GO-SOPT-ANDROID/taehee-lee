package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.FollowerDataSource
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.domain.repository.FollowerRepository
import timber.log.Timber
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
) : FollowerRepository {

    override suspend fun fetchFollowerList(): List<Follower>? = kotlin.runCatching {
        followerDataSource.fetchFollowerList()
    }.fold(
        {
            Timber.d("팔로워 목록 조회 성공")
            it.toFollower()
        }, {
            Timber.e(it.message)
            null
        }
    )

}
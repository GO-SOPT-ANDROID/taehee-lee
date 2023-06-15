package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.local.FollowerState
import org.android.go.sopt.domain.repository.FollowerRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val followerRepository: FollowerRepository
) : ViewModel() {

    private val _followerStateFlow = MutableStateFlow<FollowerState>(FollowerState.Loading)
    val followerStateFlow: StateFlow<FollowerState> = _followerStateFlow

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        viewModelScope.launch {
            try {
                val followerList = followerRepository.fetchFollowerList()
                if (followerList != null) {
                    _followerStateFlow.value = FollowerState.Success(followerList)
                } else {
                    _followerStateFlow.value = FollowerState.Error("팔로워 리스트를 조회하는데 실패했습니다.")
                }
            } catch (e: Exception) {
                _followerStateFlow.value = FollowerState.Error("Error: ${e.message}")
            }
        }
    }

}
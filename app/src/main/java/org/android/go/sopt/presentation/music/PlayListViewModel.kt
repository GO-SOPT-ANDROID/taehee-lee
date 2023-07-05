package org.android.go.sopt.presentation.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.remote.ServicePool.musicService
import org.android.go.sopt.domain.model.Music
import timber.log.Timber


class PlayListViewModel : ViewModel() {

    private val _playList = MutableStateFlow<List<Music>>(listOf())
    val playList get() = _playList.asStateFlow()

    init {
        fetchPlayList()
    }

    private fun fetchPlayList() {
        viewModelScope.launch {
            kotlin.runCatching {
                musicService.fetchMusicList()
            }.fold(
                {
                    Timber.d("플레이리스트 조회 성공")
                    _playList.value = it.toMusicList()
                }, {
                    Timber.e(it.message)
                }
            )
        }
    }

}
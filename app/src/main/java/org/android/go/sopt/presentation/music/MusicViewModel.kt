package org.android.go.sopt.presentation.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.android.go.sopt.data.datasource.remote.ServicePool.musicService
import org.android.go.sopt.domain.model.Music
import org.android.go.sopt.util.ContentUriRequestBody
import timber.log.Timber

class MusicViewModel : ViewModel() {

    val musicTitle = MutableStateFlow("")
    val singer = MutableStateFlow("")

    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    private val _playList = MutableStateFlow<List<Music>>(listOf())
    val playList get() = _playList.asStateFlow()

    fun fetchPlayList() {
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

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun uploadMusic(title:String, singer:String) {
        if (image.value == null) {
            Timber.e("아직 사진이 등록되지 않았습니다.")
        } else {
            viewModelScope.launch {
                val titleBody = title.toRequestBody("text/plain".toMediaType())
                val singerBody = singer.toRequestBody("text/plain".toMediaType())
                kotlin.runCatching {
                    musicService.uploadMusic(image.value!!.toFormData(), titleBody, singerBody)
                }.fold(
                    {
                        Timber.d("음악 추가 성공")
                        _playList.value.apply {
                            _playList.value = this + it.toMusicFormat()
                        }
                    }, {
                        Timber.e(it.message)
                    }
                )

            }
        }

    }

}
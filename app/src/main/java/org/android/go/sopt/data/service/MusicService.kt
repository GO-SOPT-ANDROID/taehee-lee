package org.android.go.sopt.data.service

import okhttp3.MultipartBody
import org.android.go.sopt.data.model.ResponsePlayListDto
import org.android.go.sopt.data.model.ResponseUploadMusicDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MusicService {

    @GET("/uniuni99/music")
    suspend fun fetchMusicList(): ResponsePlayListDto



}
package org.android.go.sopt.data.service

import org.android.go.sopt.BuildConfig.KAKAO_REST_API_KEY
import org.android.go.sopt.data.model.KakaoSearchResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KaKaoService {
    @GET("/v2/search/web")
    fun getSearchWeb(
        @Query("query") keyword: String
    ): Call<KakaoSearchResponseDto>
}
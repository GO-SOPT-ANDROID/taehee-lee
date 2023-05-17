package org.android.go.sopt.data.service

import org.android.go.sopt.BuildConfig.KAKAO_REST_API_KEY
import org.android.go.sopt.domain.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KaKaoSearchService {
    @GET("/v2/search/web")
    suspend fun getSearchWeb(
        @Header("Authorization") key: String = KAKAO_REST_API_KEY,
        @Query("query") keyword: String
    ): Response<SearchResponse>
}
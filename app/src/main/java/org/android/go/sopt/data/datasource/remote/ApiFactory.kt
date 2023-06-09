package org.android.go.sopt.data.datasource.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig.KAKAO_BASE_URL
import org.android.go.sopt.BuildConfig.SIGN_UP_BASE_URL
import org.android.go.sopt.data.repository.TokenInterceptor
import org.android.go.sopt.data.service.ImageService
import org.android.go.sopt.data.service.KaKaoService
import org.android.go.sopt.data.service.MusicService
import retrofit2.Retrofit

object ApiFactory {

    private val kakaoClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
    }

    val retrofitForKakao: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .client(kakaoClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val retrofitForImagePost: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SIGN_UP_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> createKakaoService(): T = retrofitForKakao.create<T>(T::class.java)
    inline fun <reified T> createImageService(): T = retrofitForImagePost.create<T>(T::class.java)

}

object ServicePool {
    val kakaoSearchService = ApiFactory.createKakaoService<KaKaoService>()
    val imageService = ApiFactory.createImageService<ImageService>()
    val musicService = ApiFactory.createImageService<MusicService>()
}

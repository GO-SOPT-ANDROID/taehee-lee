package org.android.go.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.service.AuthService
import org.android.go.sopt.data.type.BaseUrlType
import org.android.go.sopt.di.RetrofitModule.Retrofit2
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@Retrofit2(BaseUrlType.SOPT) retrofit: Retrofit): AuthService =
        retrofit.create()
}
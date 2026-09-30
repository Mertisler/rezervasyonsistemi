package com.loc.rezervasyonsistemi.core.di

import com.loc.rezervasyonsistemi.data.api.EventApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Zaman aşımı, Header eklentileri veya Interceptor'lar buraya dahil edilir
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.ornek-etkinlik.com/v1/") // Projenin temel uç noktası
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEventApi(retrofit: Retrofit): EventApi {
        // Mevcut retrofit nesnesini kullanarak EventApi implementasyonunu oluşturur
        return retrofit.create(EventApi::class.java)
    }
}
package com.string.me.up.todohiltassignment.di

import com.string.me.up.todohiltassignment.BuildConfig
import com.string.me.up.todohiltassignment.helper.Helper
import com.string.me.up.todohiltassignment.todo.TodoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkClient(): OkHttpClient = createOkHttpClient()

    private fun createOkHttpClient(
    ): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        return builder
            .connectTimeout(Helper.TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Helper.TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Helper.TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiFactory(): TodoApi = RetrofitApiFactory(
        provideBaseUrl(),
        provideOkClient()
    ).buildApi(TodoApi::class.java)
}
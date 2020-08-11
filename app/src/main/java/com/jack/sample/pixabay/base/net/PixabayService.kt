package com.jack.sample.pixabay.base.net

import com.jack.sample.pixabay.BuildConfig
import com.jack.sample.pixabay.home.data.entity.PixabayImageList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PixabayService {
    @GET("/api")
    suspend fun getImages(
        @QueryMap params: Map<String, String>
    ): Response<PixabayImageList>

    companion object {
        private const val BASE_URL = "https://pixabay.com"

        @Volatile
        private var INSTANCE: PixabayService? = null

        fun getInstance(): PixabayService =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: create().also {
                    INSTANCE = it
                }
            }

        private fun create(): PixabayService {
            val client = OkHttpClient.Builder().run {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    this.addInterceptor(loggingInterceptor)
                }
                this.build()
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(PixabayService::class.java)
        }
    }
}
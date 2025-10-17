package com.example.medgemma.network

import com.example.medgemma.model.HealthDataPayload
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("your/endpoint")
    suspend fun sendHealthData(@Body data: HealthDataPayload): Response<Unit>

    companion object {
        private const val BASE_URL = ""

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
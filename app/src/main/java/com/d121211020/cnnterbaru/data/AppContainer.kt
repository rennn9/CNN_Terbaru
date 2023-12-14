package com.d121211020.cnnterbaru.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.d121211020.cnnterbaru.data.repository.CNNRepository
import com.d121211020.cnnterbaru.data.source.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val cnnrepository: CNNRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://api-berita-indonesia.vercel.app/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val cnnrepository: CNNRepository
        get() = CNNRepository(retrofitService)
}

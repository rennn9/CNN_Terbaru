package com.d121211020.cnnterbaru.data

import com.d121211020.cnnterbaru.data.repository.CNNRepository
import com.d121211020.cnnterbaru.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val cnnRepository: CNNRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://api-berita-indonesia.vercel.app/cnn/terbaru/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val cnnRepository: CNNRepository
        get() = CNNRepository(retrofitService)

}

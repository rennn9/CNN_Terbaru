package com.d121211020.cnnterbaru.data.source.remote

import com.d121211020.cnnterbaru.data.model.Data
import retrofit2.http.GET

interface ApiService {

    @GET("cnn/terbaru")
    suspend fun getData(): Data

}

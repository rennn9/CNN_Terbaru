package com.d121211020.cnnterbaru.data.repository

import com.d121211020.cnnterbaru.data.model.Data
import com.d121211020.cnnterbaru.data.source.remote.ApiService

class DataRepository(private val apiService: ApiService) {

    suspend fun getData(): Data {
        return apiService.getData()
    }

}

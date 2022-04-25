package com.example.clima.data.repository

import com.example.clima.data.api.RetrofitInstance
import com.example.clima.ui.model.WeatherResponse
import retrofit2.Response

class Repository {

    suspend fun getClima(city: String): Response<WeatherResponse> {
        return RetrofitInstance.api.getClima(city)
    }

}
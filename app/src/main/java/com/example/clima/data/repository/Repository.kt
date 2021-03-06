package com.example.clima.data.repository

import com.example.clima.data.api.RetrofitInstance
import com.example.clima.model.WeatherResponse
import retrofit2.Response

class Repository {

    suspend fun getWeatherData(city: String): Response<WeatherResponse> {
        return RetrofitInstance.api.getWeatherData(city)
    }

}
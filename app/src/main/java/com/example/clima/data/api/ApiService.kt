package com.example.clima.data.api

import com.example.clima.ui.model.WeatherResponse
import com.example.clima.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") key: String = API_KEY,
        @Query("units") unidadeDeMedida: String = "metric",
        @Query("lang") language: String = "pt_br"
    ): Response<WeatherResponse>
}
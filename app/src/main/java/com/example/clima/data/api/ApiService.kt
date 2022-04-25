package com.example.clima.data.api

import com.example.clima.ui.model.WeatherResponse
import com.example.clima.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getClima(
        @Query("q") city: String,
        @Query("appid") key: String = API_KEY
    ): Response<WeatherResponse>
}
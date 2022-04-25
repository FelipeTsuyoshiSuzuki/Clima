package com.example.clima.ui.model

class WeatherResponse(
    val cood: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
) {

    override fun toString(): String {
        return super.toString()
    }

}
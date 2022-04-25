package com.example.clima.ui.model

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
) {
}
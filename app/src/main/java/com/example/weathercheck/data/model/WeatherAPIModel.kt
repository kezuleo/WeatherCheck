package com.example.weathercheck.data.model

data class WeatherAPIModel(
    val base: String,
    val cod: Int,
    val dt: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
)
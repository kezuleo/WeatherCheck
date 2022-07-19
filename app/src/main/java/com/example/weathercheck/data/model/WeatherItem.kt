package com.example.weathercheck.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("main")
    @Expose
    val mainParams: MainWeather
)
package com.example.weathercheck.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("main")
    @Expose
    val mainParams: Main,

    @SerializedName("weather")
    @Expose
    val weatherIcon: Weather,

    @SerializedName("api_model")
    @Expose
    val apiModel: WeatherAPIModel
)
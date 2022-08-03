package com.example.weathercheck.domain

open class DomainWeatherItem(

    //main class params
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,

    //weather class params
    val description: String,
    val icon: String,
    val id: Int,
    val main: String,

    //common weatherapimodel class params
    val base: String,
    val cod: Int,
    val dt: Int,
    val name: String,
    val timezone: Int,
    val visibility: Int
)
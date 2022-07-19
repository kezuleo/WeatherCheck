package com.example.weathercheck.data.net

import com.example.weathercheck.data.model.WeatherItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/data/2.5/weather?lat=35&lon=139&appid=f0fa4d8ceaecc97ab4c7e5bd867c993a")
    fun fetchData(): Call<WeatherItem>

//    companion object {
//        private const val QUERY_PARAM_API_KEY = "api_key"
//        private const val QUERY_PARAM_Q_LOCATION = "city_location"
//        private const val QUERY_PARAM_AQI = "aqi_param"
//    }
}
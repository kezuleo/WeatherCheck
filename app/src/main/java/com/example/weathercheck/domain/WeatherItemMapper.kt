package com.example.weathercheck.domain

import com.example.weathercheck.data.model.WeatherItem

class WeatherItemMapper {

    fun transfer(weatherItem: WeatherItem): DomainWeatherItem {
        return DomainWeatherItem(
            feels_like = weatherItem.mainParams.feels_like,
            grnd_level = weatherItem.mainParams.grnd_level,
            humidity = weatherItem.mainParams.humidity,
            pressure = weatherItem.mainParams.pressure,
            sea_level = weatherItem.mainParams.sea_level,
            temp = weatherItem.mainParams.temp,
            temp_max = weatherItem.mainParams.temp_max,
            temp_min = weatherItem.mainParams.temp_min
        )
    }

}
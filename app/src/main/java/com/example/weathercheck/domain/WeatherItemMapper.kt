package com.example.weathercheck.domain

import com.example.weathercheck.data.model.WeatherAPIModel

class WeatherItemMapper {

    fun transfer(weatherAPIModel: WeatherAPIModel): DomainWeatherItem {
        return DomainWeatherItem(
            feels_like = weatherAPIModel.main.feels_like,
            humidity = weatherAPIModel.main.humidity,
            pressure = weatherAPIModel.main.pressure,
            temp = weatherAPIModel.main.temp,
            temp_max = weatherAPIModel.main.temp_max,
            temp_min = weatherAPIModel.main.temp_min,
            description = weatherAPIModel.weather.get(0).description,
            icon = weatherAPIModel.weather.get(0).icon,
            id = weatherAPIModel.weather.get(0).id,
            main = weatherAPIModel.weather.get(0).main,
            base = weatherAPIModel.base,
            cod = weatherAPIModel.cod,
            dt =  weatherAPIModel.dt,
            name = weatherAPIModel.name,
            timezone = weatherAPIModel.timezone,
            visibility = weatherAPIModel.visibility
        )
    }

}
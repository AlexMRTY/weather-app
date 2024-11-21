package com.example.new_weather_app.mapper

import com.example.new_weather_app.model.WeatherType
import com.example.new_weather_app.dto.HourlyWeatherDTO
import com.example.new_weather_app.dto.WeatherDataDTO
import com.example.new_weather_app.model.Weather
import com.example.new_weather_app.model.WeatherData
import java.time.LocalDateTime

fun WeatherDataDTO.toWeatherData(): Map<Int, List<WeatherData>> {
    val weatherDataList = mutableListOf<WeatherData>()
    val weatherByDay = mutableMapOf<Int, MutableList<WeatherData>>()

    for (i in time.indices) {
        val weatherData = WeatherData(
            time = LocalDateTime.parse(time[i]),
            temperatureCelsius = temperatures[i],
            windSpeed = windSpeeds[i],
            humidity = humidities[i],
            weatherType = WeatherType.fromWMO(weatherCodes[i])
        )

        weatherDataList.add(weatherData)
    }

    weatherDataList.forEach {
        val day = it.time.dayOfMonth
        if (weatherByDay[day] == null) {
            weatherByDay[day] = mutableListOf()
        }
        weatherByDay[day]?.add(it)
    }

    return weatherByDay
}

fun HourlyWeatherDTO.toWeather(): Weather {
    val hourlyWeatherDataMap = hourlyWeatherData.toWeatherData()
    val now = LocalDateTime.now()
    val currentWeatherData = hourlyWeatherDataMap[LocalDateTime.now().dayOfMonth]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return Weather(
        weatherByDay = hourlyWeatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
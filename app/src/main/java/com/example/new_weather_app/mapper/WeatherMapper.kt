package com.example.new_weather_app.mapper

import com.example.new_weather_app.dto.CurrentWeatherDataDTO
import com.example.new_weather_app.dto.DailyWeatherDataDTO
import com.example.new_weather_app.model.WeatherType
import com.example.new_weather_app.dto.WeatherResponseDTO
import com.example.new_weather_app.dto.HourlyWeatherDataDTO
import com.example.new_weather_app.model.CurrentWeather
import com.example.new_weather_app.model.DailyWeather
import com.example.new_weather_app.model.Weather
import com.example.new_weather_app.model.HourlyWeather
import java.time.LocalDate
import java.time.LocalDateTime

fun HourlyWeatherDataDTO.toHourlyWeather(): Map<Int, List<HourlyWeather>> {
    val hourlyWeatherDataList = mutableListOf<HourlyWeather>()
    val weatherByDay = mutableMapOf<Int, MutableList<HourlyWeather>>()

    for (i in time.indices) {
        val data = HourlyWeather(
            time = LocalDateTime.parse(time[i]),
            temperatureCelsius = temperatures[i],
            windSpeed = windSpeeds[i],
            humidity = humidities[i],
            weatherType = WeatherType.fromWMO(weatherCodes[i])
        )

        hourlyWeatherDataList.add(data)
    }

    hourlyWeatherDataList.forEach {
        val day = it.time.dayOfMonth
        if (weatherByDay[day] == null) {
            weatherByDay[day] = mutableListOf()
        }
        weatherByDay[day]?.add(it)
    }

    return weatherByDay
}

fun DailyWeatherDataDTO.toDailyWeather(): Map<Int, DailyWeather> {
    val dailyWeatherMap = mutableMapOf<Int, DailyWeather>()

    for (i in time.indices) {
        val data = DailyWeather(
            time = LocalDate.parse(time[i]),
            maxTemperatureCelsius = maxTemperatures[i],
            minTemperatureCelsius = minTemperatures[i],
            weatherType = WeatherType.fromWMO(weatherCodes[i])
        )

        if (dailyWeatherMap[data.time.dayOfMonth] == null) {
            dailyWeatherMap[data.time.dayOfMonth] = data
        }
    }

    return dailyWeatherMap
}

fun CurrentWeatherDataDTO.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        time = LocalDateTime.parse(time),
        temperatureCelsius = temperature,
        humidity = humidity,
        apparentTemperatureCelsius = apparentTemperature,
        isDay = isDay == 1,
        precipitation = precipitation,
        weatherType = WeatherType.fromWMO(weatherCode),
        windSpeed = windSpeed,
        windDirection = windDirection,
        windGusts = windGusts
    )
}

fun WeatherResponseDTO.toWeather(): Weather {
    val hourlyWeatherDataMap = hourlyWeatherData.toHourlyWeather()
    val dailyWeatherDataMap = dailyWeatherData.toDailyWeather()
    val currentWeatherData = currentWeatherData.toCurrentWeather()
    return Weather(
        weatherHourlyByDay = hourlyWeatherDataMap,
        weatherDaily = dailyWeatherDataMap,
        currentWeather = currentWeatherData
    )
}
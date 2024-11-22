package com.example.new_weather_app.model

data class Weather(
    val weatherHourlyByDay: Map<Int, List<HourlyWeather>>,
    val weatherDaily: Map<Int, DailyWeather>,
    val currentWeather: CurrentWeather
)


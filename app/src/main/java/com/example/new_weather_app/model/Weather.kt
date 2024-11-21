package com.example.new_weather_app.model

data class Weather(
    val weatherByDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
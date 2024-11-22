package com.example.new_weather_app.ui

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class HourlyWeather(
    val temperature: Double,
    val weatherCode: Int,
    val time: String
)

@Composable
fun WeatherCardLazyRow(hourlyWeatherList: List<HourlyWeather>) {
    if (hourlyWeatherList.isEmpty()) {
        return
    }

    LazyRow(
        modifier = Modifier.padding(16.dp)
    ) {
        items(hourlyWeatherList) { hourlyWeather ->
            WeatherCardRow(
                temperature = hourlyWeather.temperature,
                weatherCode = hourlyWeather.weatherCode,
                time = hourlyWeather.time,
                onWeatherSelected = { /* handle selection */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardLazyRowPreview() {
    val sampleData = List(24) { index ->
        HourlyWeather(
            temperature = 20.0 + index,
            weatherCode = index % 100,
            time = String.format("%02d:00", index)
        )
    }
    WeatherCardLazyRow(hourlyWeatherList = sampleData)
}
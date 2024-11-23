package com.example.new_weather_app.ui

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.new_weather_app.model.HourlyWeather
import java.time.LocalDate


@Composable
fun HourlyWeatherRow(hourlyWeatherList: Map<Int, List<HourlyWeather>>) {
    val currentDayHourlyWeather = hourlyWeatherList[LocalDate.now().dayOfMonth] ?: emptyList()
    LazyRow(
        modifier = Modifier.padding(16.dp)
    ) {
        items(currentDayHourlyWeather) { hourlyWeather ->
            HourCard(
                temperature = hourlyWeather.temperatureCelsius,
                weatherType = hourlyWeather.weatherType,
                time = hourlyWeather.time,
            )
        }
    }
}


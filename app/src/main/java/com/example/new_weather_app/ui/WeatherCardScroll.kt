package com.example.new_weather_app.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

data class DailyWeather(
    val temperature: Double,
    val weatherCode: Int,
    val time: String
)

@Composable
fun WeatherCardScroll(dailyWeatherList: List<DailyWeather>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(dailyWeatherList) { dailyWeather ->
            WeatherCardRow(
                temperature = dailyWeather.temperature,
                weatherCode = dailyWeather.weatherCode,
                time = dailyWeather.time,
                onWeatherSelected = { /* handle selection */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardScrollPreview() {
    val sampleData = List(7) { index ->
        DailyWeather(
            temperature = 20.0 + index,
            weatherCode = index % 100,
            time = "Day ${index + 1}"
        )
    }
    WeatherCardScroll(dailyWeatherList = sampleData)
}
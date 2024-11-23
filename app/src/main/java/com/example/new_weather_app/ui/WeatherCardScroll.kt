package com.example.new_weather_app.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import com.example.new_weather_app.model.DailyWeather
import com.example.new_weather_app.model.WeatherType
import java.time.LocalDate


@Composable
fun DailyWeather(dailyWeatherList: Map<Int, DailyWeather>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(dailyWeatherList.values.toList()) { dailyWeather ->
            DayCard(
                maxTempreture = dailyWeather.maxTemperatureCelsius,
                minTempreture = dailyWeather.minTemperatureCelsius,
                time = dailyWeather.time,
                weatherType = dailyWeather.weatherType
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardScrollPreview() {
    val sampleData = mapOf(
        0 to DailyWeather(
            time = LocalDate.now(),
            maxTemperatureCelsius = 30.0,
            minTemperatureCelsius = 20.0,
            weatherType = WeatherType.fromWMO(0)
        ),
        1 to DailyWeather(
            time = LocalDate.now().plusDays(1),
            maxTemperatureCelsius = 25.0,
            minTemperatureCelsius = 15.0,
            weatherType = WeatherType.fromWMO(0)
        ),
        2 to DailyWeather(
            time = LocalDate.now().plusDays(2),
            maxTemperatureCelsius = 20.0,
            minTemperatureCelsius = 10.0,
            weatherType = WeatherType.fromWMO(0)
        ),
        3 to DailyWeather(
            time = LocalDate.now().plusDays(3),
            maxTemperatureCelsius = 15.0,
            minTemperatureCelsius = 5.0,
            weatherType = WeatherType.fromWMO(0)
        ),
    )
    DailyWeather(dailyWeatherList = sampleData)
}
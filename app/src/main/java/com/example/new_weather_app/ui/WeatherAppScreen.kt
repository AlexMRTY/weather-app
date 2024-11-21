package com.example.new_weather_app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.new_weather_app.vm.WeatherState


@Composable
fun WeatherAppScreen(
    weatherState: WeatherState?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        WeatherCard(
            temperature = 25.0,
            weatherCode = 0, // MainlyClear
            humidity = 60,
            windSpeed = 5.0,
            time = "14:30"
        )
        WeatherCardLazyRow(
            hourlyWeatherList = List(24) { index ->
                HourlyWeather(
                    temperature = 20.0 + index,
                    weatherCode = index % 100,
                    time = String.format("%02d:00", index)
                )
            }
        )
        Text(
            text = "Next 7 days",
            style = MaterialTheme.typography.headlineSmall
        )
        WeatherCardScroll(
            dailyWeatherList = List(7) { index ->
                DailyWeather(
                    temperature = 20.0 + index,
                    weatherCode = index % 100,
                    time = "Day ${index + 1}"
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAppScreenPreview() {
    WeatherAppScreen(
        weatherState = WeatherState()
    )
}


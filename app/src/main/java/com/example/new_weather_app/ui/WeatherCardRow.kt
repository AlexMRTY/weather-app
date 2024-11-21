package com.example.new_weather_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_weather_app.model.WeatherType

@Composable
fun WeatherCardRow(
    temperature: Double,
    weatherCode: Int,
    time: String,
    onWeatherSelected: (HourlyWeather) -> Unit
) {
    val weatherType = WeatherType.fromWMO(weatherCode)

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${temperature}°C",
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = weatherType.iconRes),
                    contentDescription = weatherType.weatherDesc,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = time,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        onWeatherSelected(HourlyWeather(temperature, weatherCode, time))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardRowPreview() {
    WeatherCardRow(
        temperature = 25.0,
        weatherCode = 0, // MainlyClear
        time = "14:30",
        onWeatherSelected = {}
    )
}
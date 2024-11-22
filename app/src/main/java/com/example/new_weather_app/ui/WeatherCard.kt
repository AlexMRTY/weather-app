package com.example.new_weather_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.new_weather_app.model.WeatherType
import java.time.LocalDateTime


@Composable
fun WeatherCard(
    temperature: Double,
    weatherType: WeatherType,
    humidity: Double,
    windSpeed: Double,
    time: LocalDateTime
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${temperature}°C",
                    fontSize = 24.sp
                )
                Image(
                    painter = painterResource(id = weatherType.iconRes),
                    contentDescription = weatherType.weatherDesc,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = weatherType.weatherDesc,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Humidity: $humidity%",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Wind Speed: ${windSpeed}m/s",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Time: $time",
                fontSize = 16.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WeatherCardPreview() {
//    WeatherCard(
//        temperature = 25.0,
//        weatherCode = 0, // MainlyClear
//        humidity = 60,
//        windSpeed = 5.0,
//        time = "14:30"
//    )
//}
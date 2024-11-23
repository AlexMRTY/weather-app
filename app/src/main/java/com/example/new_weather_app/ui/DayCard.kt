package com.example.new_weather_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.new_weather_app.model.WeatherType
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun DayCard(
    maxTempreture: Double,
    minTempreture: Double,
    time: LocalDate,
    weatherType: WeatherType
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 20.dp),
        ) {
            Text(
                text = time.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(Locale.ROOT),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${minTempreture.toInt()}° | ${maxTempreture.toInt()}°",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Image(
                    painter = painterResource(id = weatherType.iconRes),
                    contentDescription = weatherType.weatherDesc,
                    modifier = Modifier.size(30.dp)
                )

            }
        }
    }
}

@Preview
@Composable
fun DayCardPreview() {
    DayCard(
        maxTempreture = 30.0,
        minTempreture = 20.0,
        time = LocalDate.now(),
        weatherType = WeatherType.fromWMO(0)
    )
}
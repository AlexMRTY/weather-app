package com.example.new_weather_app.ui.theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoordinateInput(
    onSearch: (Float, Float) -> Unit
) {
    var lat by remember { mutableStateOf("59.3") }
    var long by remember { mutableStateOf("18.0") }
    var latError by remember { mutableStateOf(false) }
    var longError by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 30.dp)
            .wrapContentSize()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = lat,
            onValueChange = {
                lat = it
                latError = it.toFloatOrNull() == null
            },
            label = { Text("Lat") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = latError
        )
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = long,
            onValueChange = {
                long = it
                longError = it.toFloatOrNull() == null
            },
            label = { Text("Long") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = longError
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                if (!latError && !longError) {
                    onSearch(lat.toFloat(), long.toFloat())
                }
            },
            modifier = Modifier
                .wrapContentSize()
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "🔍",
                fontSize = 20.sp
            )
        }
    }
}
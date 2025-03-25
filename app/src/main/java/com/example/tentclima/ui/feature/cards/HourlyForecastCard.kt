package com.example.tentclima.ui.feature.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tentclima.data.remote.response.HourlyDataEntry
import com.example.tentclima.ui.feature.toFormattedTime
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HourlyForecastCard(hourlyData: List<HourlyDataEntry>) {
    LazyRow{
        items(hourlyData){ hourly ->
            HourlyForecastItem(hourly = hourly)
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun HourlyForecastItem(hourly: HourlyDataEntry) {
    Column(
        modifier = Modifier.padding(top = 8.dp, end = 8.dp)
    ){
        Text(
            modifier = Modifier.width(55.dp),
            text = hourly.dt.toFormattedTime(),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        val context = LocalContext.current
        val iconResId = context.resources.getIdentifier(
            "weather_${hourly.weather.first().icon}",
            "drawable",
            context.packageName
        )

        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(55.dp)
        )

        Text(
            modifier = Modifier.width(55.dp),
            text = "${hourly.mainData.temp.roundToInt()}°C",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

/*
@Preview
@Composable
private fun HourlyForecastCardPreview() {
    TentclimaTheme {
        HourlyForecastCard()
    }
}
*/
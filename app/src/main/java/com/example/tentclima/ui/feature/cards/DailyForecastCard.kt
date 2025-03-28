package com.example.tentclima.ui.feature.cards

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tentclima.R
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.FeelsLike
import com.example.tentclima.data.remote.response.Temp
import com.example.tentclima.data.remote.response.Weather
import com.example.tentclima.ui.theme.TentclimaTheme
import com.example.tentclima.utils.windDirection
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun DailyForecastCard(dailyData: List<DailyData>) {
    val weekdays = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom")
    val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val todayIndex = today.dayOfWeek.value - 1 // 0 para segunda, 1 para terça, etc.
    val startingIndex = (todayIndex + 1) % 7 // Índice do primeiro dia (amanhã)
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        itemsIndexed(dailyData){ index, daily ->
            val dayName = weekdays[(startingIndex + index) % 7]
            DailyForecastItem(dailyData = daily, dayName = dayName)
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun DailyForecastItem(dailyData: DailyData, dayName: String) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Text(modifier = Modifier.padding(8.dp),
                text = dayName,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            // Data
            Text(modifier = Modifier.padding(8.dp),
                text = LocalDate.ofEpochDay(dailyData.dt / 86400)
                    .format(DateTimeFormatter.ofPattern("dd/MM")),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

        // Mapeamento de ícones de clima
        val iconResId = when (dailyData.weather.first().icon) {
            "01d" -> R.drawable.weather_01d
            "01n" -> R.drawable.weather_01n
            "02d" -> R.drawable.weather_02d
            "02n" -> R.drawable.weather_02n
            "03d" -> R.drawable.weather_03d
            "03n" -> R.drawable.weather_03n
            "04d" -> R.drawable.weather_04d
            "04n" -> R.drawable.weather_04n
            "09d" -> R.drawable.weather_09d
            "09n" -> R.drawable.weather_09n
            "10d" -> R.drawable.weather_10d
            "10n" -> R.drawable.weather_10n
            "11d" -> R.drawable.weather_11d
            "11n" -> R.drawable.weather_11n
            "13d" -> R.drawable.weather_13d
            "13n" -> R.drawable.weather_13n
            "50d" -> R.drawable.weather_50d
            "50n" -> R.drawable.weather_50n
            else -> R.drawable.weather_02d
        }

        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(65.dp).padding(start = 8.dp)
        )

        Column(
            modifier = Modifier.width(230.dp).padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Text(
                    modifier = Modifier.width(55.dp),
                    text = "${dailyData.temp.min.roundToInt()} °C",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier.width(5.dp),
                    text = "-",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier.width(55.dp),
                    text = "${dailyData.temp.max.roundToInt()} °C",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }

            Row(
                modifier = Modifier.padding(start = 8.dp,top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Umidade
                Icon(
                    painter = painterResource(id = R.drawable.icone_umidade),
                    contentDescription = "Umidade",
                    modifier = Modifier.size(22.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${dailyData.humidity}%",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Chuva
                Icon(
                    painter = painterResource(id = R.drawable.icone_chuva),
                    contentDescription = "Chuva",
                    modifier = Modifier.size(32.dp).padding(start = 8.dp, end = 2.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${(dailyData.pop*100).roundToInt()}%",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Vento
                Icon(
                    painter = painterResource(id = R.drawable.icone_vento),
                    contentDescription = "Vento",
                    modifier = Modifier.size(32.dp).padding(start = 8.dp, end = 2.dp),
                    tint = Color.White
                )

                Text(
                    text = "${(dailyData.speed*3.6).roundToInt()} km/h ${windDirection(dailyData.deg)}",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
private fun DailyForecastCardPreview() {
    TentclimaTheme {
        DailyForecastCard(
            dailyData = fakeDailyData
        )
    }
}

val fakeDailyData = List(7) { index ->
    DailyData(
        dt = System.currentTimeMillis() / 1000 + (index * 86400), // Timestamp futuro
        sunrise = 0,
        sunset = 0,
        temp = Temp(
            day = 25.0 + index * 2.0, // Temperaturas variadas para visualização
            min = 20.0,
            max = 30.0,
            night = 22.0,
            eve = 24.0,
            morn = 18.0
        ),
        feelsLike = FeelsLike(
            day = 25.0,
            night = 20.0,
            eve = 23.0,
            morn = 19.0
        ),
        pressure = 1013,
        humidity = 60,
        weather = listOf(
            Weather(
                id = 800,
                main = "Clear",
                description = "Clear sky",
                icon = "01d" // Ícone válido para o preview
            )
        ),
        speed = 5.0,
        deg = 0,
        gust = 7.0,
        clouds = 20,
        pop = 0.1,
        rain = 0.0
    )
}

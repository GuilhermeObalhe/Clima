package com.example.tentclima.ui.feature.cards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tentclima.model.WeatherInfo
import com.example.tentclima.ui.feature.toFormattedTime
import com.example.tentclima.utils.airQualityWord
import com.example.tentclima.utils.windDirection
import kotlin.math.roundToInt

@Composable
fun InformationsSection(weatherInfo: WeatherInfo) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        color = Color.Gray.copy(alpha = 0.2f),
        shape = RoundedCornerShape(16.dp),
    ){
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            item{
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Sensação térmica: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.feelsLike} °C")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Umidade: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.humidity}%")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Pressão: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.pressure} mbar")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Vento: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${(weatherInfo.wind.speed*3.6).roundToInt()} km/h ${windDirection(weatherInfo.wind.deg)}")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Nuvens: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.clouds}%")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Visibilidade: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.visibility / 1000} km")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Nascer do sol: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append(weatherInfo.sunrise.toFormattedTime())
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Pôr do sol: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append(weatherInfo.sunset.toFormattedTime())
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Probabilidade de chuva: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${(weatherInfo.dailyData.first().pop*100).toInt()}%")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Precipitação: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${if (weatherInfo.dailyData.first().rain == null) 0 else weatherInfo.dailyData.first().rain} mm")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Qualidade do ar: ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append("${weatherInfo.airQualityData.list.first().main.aqi} - ${airQualityWord(weatherInfo.airQualityData.list.first().main.aqi)}")
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
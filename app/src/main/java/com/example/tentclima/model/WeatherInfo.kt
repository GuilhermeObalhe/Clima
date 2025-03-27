package com.example.tentclima.model

import com.example.tentclima.data.remote.response.AirQualityResponse
import com.example.tentclima.data.remote.response.DailyData
import com.example.tentclima.data.remote.response.HourlyDataEntry
import com.example.tentclima.data.remote.response.Wind

data class WeatherInfo(
    val locationName: String,
    val conditionIcon: String,
    val condition: String,
    val temperature: Int,
    val dayOfWeek: String,
    val isDay: Boolean,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: Int,
    val tempMin: Int,
    val tempMax: Int,
    val seaLevel: Int? = null,
    val wind: Wind,
    val visibility: Int,
    val sunrise: Long,
    val sunset: Long,
    val clouds: Int? = null,

    // Previsão horária
    val hourlyData: List<HourlyDataEntry>,
    // Previsão diária
    val dailyData: List<DailyData>,
    // Dados da qualidade do ar
    val airQualityData: AirQualityResponse
)



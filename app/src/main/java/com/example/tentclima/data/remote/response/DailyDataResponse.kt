package com.example.tentclima.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDataResponse(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<DailyData>
)

@Serializable
data class DailyData(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    @SerialName("feels_like") val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
    val rain: Double? = null
)

@Serializable
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

@Serializable
data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

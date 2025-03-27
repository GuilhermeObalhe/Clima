package com.example.tentclima.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityResponse(
    val coord: Coord,
    val list: List<AirQualityData>
)

@Serializable
data class AirQualityData(
    val dt: Long,
    val main: AirQualityIndex,
    val components: AirComponents
)

@Serializable
data class AirQualityIndex(
    val aqi: Int
)

@Serializable
data class AirComponents(
    val co: Double,
    val no: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    @SerialName("pm2_5") val pm25: Double,
    val pm10: Double,
    val nh3: Double
)


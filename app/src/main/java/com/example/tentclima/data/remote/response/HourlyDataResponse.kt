package com.example.tentclima.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class HourlyDataResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<HourlyDataEntry>,
    val city: City
)

@Serializable
data class HourlyDataEntry(
    val dt: Long,
    @SerialName("main") val mainData: MainData,
    val weather: List<Weather>,
    @SerialName("clouds") val cloudsData: Clouds,
    @SerialName("wind") val windData: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: RainData? = null,
    val sys: SysData,
    @SerialName("dt_txt") val dtTxt: String
)

@Serializable
data class MainData(
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double, // 添加 SerialName
    @SerialName("temp_min") val tempMin: Double,     // 添加 SerialName
    @SerialName("temp_max") val tempMax: Double,     // 添加 SerialName
    val pressure: Int,
    @SerialName("sea_level") val seaLevel: Int,
    @SerialName("grnd_level") val grndLevel: Int,
    val humidity: Int,
    @SerialName("temp_kf") val tempKf: Double        // 添加 SerialName
)


@Serializable
data class RainData(
    @SerialName("1h") val oneHour: Double
)

@Serializable
data class SysData(
    val pod: String
)

@Serializable
data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long? = null,
    val sunset: Long? = null
)
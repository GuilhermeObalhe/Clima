package com.example.tentclima.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CitySearchResult(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String? = null,
    @SerialName("local_names")
    val localNames: Map<String, String>? = null
)
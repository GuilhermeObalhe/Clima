package com.example.tentclima.data

import com.example.tentclima.data.remote.RemoteDataSource
import com.example.tentclima.data.remote.response.CitySearchResult
import com.example.tentclima.data.remote.response.DailyDataResponse
import com.example.tentclima.data.remote.response.HourlyDataResponse
import com.example.tentclima.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import javax.inject.Inject

class KtorRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    override suspend fun getWeatherDataResponse(lat: Double, lng: Double): WeatherDataResponse {
        return httpClient
            .get("$BASE_URL/weather?lat=$lat&lon=$lng&appid=6d80b1ffd0c1b175173600e21afdd4d3&units=metric&lang=pt_br")
            .body()
    }

    // Previsão horária
    private val hourlyURL = "https://pro.openweathermap.org/data/2.5"
    override suspend fun getHourlyWeatherDataResponse(lat: Double, lng: Double): HourlyDataResponse {
        return httpClient
            .get("$hourlyURL/forecast/hourly?lat=$lat&lon=$lng&appid=6d80b1ffd0c1b175173600e21afdd4d3&units=metric&lang=pt_br")
            .body()
    }

    // Previsão diária
    override suspend fun getDailyWeatherDataResponse(lat: Double, lng: Double): DailyDataResponse {
        return httpClient
            .get("$BASE_URL/forecast/daily?lat=$lat&lon=$lng&cnt=9&appid=6d80b1ffd0c1b175173600e21afdd4d3&units=metric&lang=pt_br")
            .body()
    }

    // Busca lista de cidades por nome
    override suspend fun searchCities(query: String): List<CitySearchResult> {
        return httpClient.get("https://api.openweathermap.org/geo/1.0/direct") {
            parameter("q", query)
            parameter("limit", 5)
            parameter("appid", "6d80b1ffd0c1b175173600e21afdd4d3")
        }.run {
            if (status.isSuccess()) { // Verifica se a requisição foi bem-sucedida
                body<List<CitySearchResult>>() // Desserializa apenas se OK
            } else {
                emptyList()
            }
        }
    }
}
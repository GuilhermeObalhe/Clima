package com.example.tentclima.ui.feature

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tentclima.data.citydatabase.CityEntity
import com.example.tentclima.data.citydatabase.CityRepository
import com.example.tentclima.data.remote.response.CitySearchResult
import com.example.tentclima.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _weatherInfoState = MutableStateFlow(WeatherInfoState())
    val weatherInfoState: StateFlow<WeatherInfoState> = _weatherInfoState.asStateFlow()

    // Tentando implementar a busca de cidades em tempo real
    private val _searchResults = mutableStateListOf<CitySearchResult>()
    val searchResults: List<CitySearchResult> = _searchResults

    // Recuperar as cidades do banco de dados
    private val _cities = MutableStateFlow<List<CityEntity>>(emptyList())
    val citiesFlow = _cities.asStateFlow()

    private var _latitude: Double = 0.0
    private var _longitude: Double = 0.0

    internal fun getWeatherInfo(latitude: Double, longitude: Double) {
        _latitude = latitude
        _longitude = longitude
        viewModelScope.launch {
            val weatherInfo = weatherRepository.getWeatherData(latitude, longitude)
            _weatherInfoState.update {
                it.copy(weatherInfo = weatherInfo)
            }
        }
    }

    // Método para buscar as cidades
    fun searchCities(query: String) {
        viewModelScope.launch {
            try {
                val results = weatherRepository.searchCities(query)
                _searchResults.clear()
                _searchResults.addAll(results)
            } catch (e: Exception) {
                // Trate erros (ex: exiba uma mensagem de erro)
                _searchResults.clear()
            }
        }
    }

    // Recuperar cidades
    init {
        loadCities() // Carrega as cidades ao iniciar o ViewModel
    }

    private fun loadCities() {
        viewModelScope.launch {
            _cities.emit(cityRepository.getAllCities())
        }
    }

    suspend fun saveCity(city: CityEntity) {
        cityRepository.insertCity(city)
        loadCities() // Atualiza a lista após salvar
    }

    fun deleteCity(cityId: Int) {
        viewModelScope.launch {
            cityRepository.deleteCity(cityId)
            loadCities()
        }
    }
}

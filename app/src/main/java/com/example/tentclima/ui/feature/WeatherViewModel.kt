package com.example.tentclima.ui.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _weatherInfoState = MutableStateFlow(WeatherInfoState())
    val weatherInfoState: StateFlow<WeatherInfoState> = _weatherInfoState.asStateFlow()

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
}

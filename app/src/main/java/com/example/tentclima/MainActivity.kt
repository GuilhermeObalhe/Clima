package com.example.tentclima

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.tentclima.locationservice.LocationService
import com.example.tentclima.ui.feature.WeatherRoute
import com.example.tentclima.ui.feature.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var locationService: LocationService
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                getLocation()
            } else {
                Log.d("MainActivity", "Permissão de localização negada")
            }
        }
        locationService = LocationService(this)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        setContent {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            WeatherRoute()
        }
    }

    private fun getLocation() {
        locationService.getLastLocation(
            onSuccess = { latitude, longitude ->
                Log.d("MainActivity", "Latitude: $latitude, Longitude: $longitude")
                viewModel.getWeatherInfo(latitude, longitude)
            },
            onFailure = { exception ->
                Log.d("MainActivity", "Erro ao obter localização: $exception")
            }
        )
    }
}
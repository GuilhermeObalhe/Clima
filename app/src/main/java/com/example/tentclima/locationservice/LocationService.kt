package com.example.tentclima.locationservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationService(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLastLocation(onSuccess: (latitude: Double, longitude: Double) -> Unit, onFailure: (Exception) -> Unit) {
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            onFailure(Exception("Permissão de localização negada"))
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    onSuccess(latitude, longitude)
                    Log.d("LocationService", "Latitude: $latitude, Longitude: $longitude")
                } else {
                    onFailure(Exception("Localização não encontrada"))
                }
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}
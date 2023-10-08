package com.example.location.domain

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.location.data.model.Coordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    private val locationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)

    override fun doWork(): Result {
        try {
            // Verificar y solicitar permisos de ubicación si es necesario
            if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("LocationWorker", "falla: ${Result.failure()}")
                return Result.failure()
            }

            // Obtener la ubicación actual
            locationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Coordinates.latitude = location.latitude
                    Coordinates.longitude = location.longitude
                    Log.d("LocationWorker", "Latitud: ${location.latitude}, Longitud: ${location.longitude}")
                }
            }
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }
}
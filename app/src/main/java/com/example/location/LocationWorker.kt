package com.example.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val locationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(appContext)

    override fun doWork(): Result {
        try {
            // Verificar y solicitar permisos de ubicación si es necesario
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Si no tiene permisos, devuelve un fallo
                return Result.failure()
            }

            // Obtener la ubicación actual
            locationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Aquí puedes enviar los datos de ubicación a tu servidor o donde desees
                    // Por ejemplo, puedes usar Retrofit para realizar una solicitud HTTP POST
                    // con los datos de ubicación.

                    Log.d("LocationWorker", "Latitud: $latitude, Longitud: $longitude")
                }
            }
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }
}
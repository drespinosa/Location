package com.example.location.domain

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.location.core.RetrofitHelper
import com.example.location.data.model.LocationDTO
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val location = getLocationWithTimeout()
            if (location != null) {
                val locationDTO = LocationDTO(location.latitude, location.longitude)
                sendLocationToServer(locationDTO)
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun getLocationWithTimeout(): Location? =
        withContext(Dispatchers.IO) {
            if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return@withContext null
            }
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
            val locationTask = fusedLocationClient.lastLocation
            val location = Tasks.await(locationTask)
            location
        }


    private suspend fun sendLocationToServer(locationDTO: LocationDTO) {
        val apiService = RetrofitHelper.getApiService()
        val response = apiService.sendLocation(locationDTO)
        if (response.isSuccessful) response.body() else Log.d("com.example.location.domain.LocationWorker", "Fail: $response")
    }
}
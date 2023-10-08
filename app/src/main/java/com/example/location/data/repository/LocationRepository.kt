package com.example.location.data.repository

import android.util.Log
import com.example.location.core.RetrofitHelper
import com.example.location.data.model.LocationDTO

class LocationRepository() {

    private val api = RetrofitHelper.retrofitClient

    suspend fun saveLocation(latitude: Double, longitude: Double) {
        val response = api.postLocation(LocationDTO(latitude, longitude))
        Log.i("LocationRepository", "Response: $response")
    }

}
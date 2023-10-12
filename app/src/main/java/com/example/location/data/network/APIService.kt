package com.example.location.data.network

import com.example.location.data.model.Constants.AUTH_TOKEN
import com.example.location.data.model.LocationDTO
import com.example.location.data.model.LocationResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @Headers("Authorization: Bearer $AUTH_TOKEN")
    @POST("gps")
    suspend fun sendLocation(@Body location: LocationDTO): Response<LocationResponseDTO>

    @GET("gps")
    suspend fun getAllLocations(): List<LocationResponseDTO>

}
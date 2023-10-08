package com.example.location.data.network

import com.example.location.data.model.Constants.AUTH_TOKEN
import com.example.location.data.model.LocationDTO
import com.example.location.data.model.ResponseLocationDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @Headers("Authorization: Bearer $AUTH_TOKEN")
    @POST("gps")
    suspend fun postLocation(@Body location: LocationDTO): Response<ResponseLocationDTO>

}
package com.example.location.core

import com.example.location.data.network.APIService
import com.example.location.data.model.Constants.URL_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val retrofitClient: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}

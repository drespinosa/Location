package com.example.location.data.model

import com.google.gson.annotations.SerializedName

data class ResponseLocationDTO(

    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("created_by")
    val createdBy: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("last_modified_date")
    val lastModifiedDate: String

)

package com.example.location.data.model

import com.google.gson.annotations.SerializedName

data class LocationResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("created_by") val createdBy: Double,
    @SerializedName("created_date") val createdDate: Double,
    @SerializedName("last_modified_date") val lastModifiedDate: Double,
)

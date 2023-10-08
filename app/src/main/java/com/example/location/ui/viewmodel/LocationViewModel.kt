package com.example.location.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.location.data.repository.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: LocationRepository) : ViewModel() {

    fun saveCoordinates(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            repository.saveLocation(latitude, longitude)
        }

    }
}
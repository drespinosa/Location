package com.example.location.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.location.data.repository.LocationRepository

class ViewModelFactory(private val repository: LocationRepository) : ViewModelProvider.Factory {

    constructor() : this(LocationRepository())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LocationRepository::class.java).newInstance(repository)
    }

}
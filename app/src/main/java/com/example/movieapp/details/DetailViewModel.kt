package com.example.movieapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.Result

class DetailViewModel(movieProparty: Result, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Result>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<Result>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = movieProparty
    }

}
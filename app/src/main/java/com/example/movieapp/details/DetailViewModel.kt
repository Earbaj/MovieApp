package com.example.movieapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.Result

class DetailViewModel(movieProparty: Result, app: Application) : AndroidViewModel(app) {

    private val _selectedObjects = MutableLiveData<Result>()
    var selectedObjects: LiveData<Result> = _selectedObjects

    init {
        _selectedObjects.value = movieProparty
    }

}
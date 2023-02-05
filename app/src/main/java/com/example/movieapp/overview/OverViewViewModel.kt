package com.example.movieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.network.MarsApi
import kotlinx.coroutines.launch
import com.example.movieapp.data.Result

class OverViewViewModel : ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _obj = MutableLiveData<List<Result>>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    val obj: LiveData<List<Result>> = _obj

    init {
        getMarsePhotos()
    }

    private fun getMarsePhotos(){
        viewModelScope.launch {
            try{
                val listRes = MarsApi.retrofitService.getPhotos()
                _status.value = "${"https://image.tmdb.org/t/p/w500"+listRes.results[1].backdropPath}"
                _obj.value = listRes.results
            }catch (ex: Exception){
                println(ex.toString())
            }
        }
    }


}
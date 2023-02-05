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
    private val _navigateToSelectedProperty = MutableLiveData<Result?>()

    // The external immutable LiveData for the request status
    var status: LiveData<String> = _status
    var obj: LiveData<List<Result>> = _obj
    var navigateToSelectedProperty: LiveData<Result?> = _navigateToSelectedProperty

    init {
        getMarsePhotos()
    }

    private fun getMarsePhotos(){
        viewModelScope.launch {
            try{
                val listRes = MarsApi.retrofitService.getPhotos()
                //_status.value = "${"https://image.tmdb.org/t/p/w500"+listRes.results[1].backdropPath}"
                _obj.value = listRes.results
            }catch (ex: Exception){
                println(ex.toString())
            }
        }
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedProperty] [MutableLiveData]
     * @param marsProperty The [MarsProperty] that was clicked on.
     */
    fun displayPropertyDetails(marsProperty: Result) {
        _navigateToSelectedProperty.value = marsProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}
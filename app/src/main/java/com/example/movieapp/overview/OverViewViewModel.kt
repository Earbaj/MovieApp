package com.example.movieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.network.MarsApi
import kotlinx.coroutines.launch
import com.example.movieapp.data.Result
//The [ViewModel] that is attached to the [OverviewFragment].
class OverViewViewModel : ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _obj = MutableLiveData<List<Result>>()
    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<Result?>()

    // The external immutable LiveData for the request status
    var status: LiveData<String> = _status
    var obj: LiveData<List<Result>> = _obj
    // The external immutable LiveData for the navigation property
    var navigateToSelectedProperty: LiveData<Result?> = _navigateToSelectedProperty

    //Call getMovieProparties() on init so we can display status immediately.
    init {
        getMovieProparties()
    }

    private fun getMovieProparties(){
        viewModelScope.launch {
            try{
                val listRes = MarsApi.retrofitService.getPhotos()
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
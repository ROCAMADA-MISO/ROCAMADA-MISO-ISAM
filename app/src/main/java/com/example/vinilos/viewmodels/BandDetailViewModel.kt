package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Albums
import com.example.vinilos.models.Band
import com.example.vinilos.repositories.BandDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BandDetailViewModel(application: Application, bandId: Int) :  AndroidViewModel(application) {

    private val bandDetailRepository = BandDetailRepository(application)
    private val _band = MutableLiveData<Band>()

    private val _albums = MutableLiveData<List<Albums>>()

    val albums: LiveData<List<Albums>>
        get() = _albums

    val band: LiveData<Band>
        get() = _band

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = bandId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = bandDetailRepository.refreshData(id)
                    _band.postValue(data)
                    _albums.postValue(data.albums)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val bandId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BandDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BandDetailViewModel(app, bandId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}


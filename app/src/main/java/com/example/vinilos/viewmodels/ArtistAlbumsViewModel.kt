package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Albums
import com.example.vinilos.repositories.AlbumsRepository

class ArtistAlbumsViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumsRepository(application)

    private val _albums = MutableLiveData<List<Albums>>()

    val albums: LiveData<List<Albums>>
        get() = _albums


    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }
    private fun refreshDataFromNetwork() {
        albumsRepository.refreshData({
            _albums.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistAlbumsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistAlbumsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
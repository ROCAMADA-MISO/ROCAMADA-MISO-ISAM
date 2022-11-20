package com.example.vinilos.viewmodels

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.vinilos.models.Albums
import com.example.vinilos.network.NetworkServiceAdapter
import com.example.vinilos.repositories.AlbumsRepository


class CreateAlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumRepository = AlbumsRepository(application)

    private val _albums = MutableLiveData<List<Albums>>()

    val albums: LiveData<List<Albums>>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createAlbum(name:String, cover: String, releaseDate:String, description:String, genre:String, recordLabel:String ){
        val resp =  albumRepository.createAlbum(name,cover,releaseDate,description, genre, recordLabel)
    }


    private fun refreshDataFromNetwork() {
        albumRepository.refreshData( {
            _albums.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateAlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
package com.example.vinilos.viewmodels

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Organization
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.models.Prize
import com.example.vinilos.repositories.PrizesRepository

class CreatePrizeViewModel(application: Application) :  AndroidViewModel(application) {

    private val prizesRepository = PrizesRepository(application)

    private val _prizes = MutableLiveData<List<Prize>>()

    val prizes: LiveData<List<Prize>>
        get() = _prizes

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromNetwork()
    }

    fun createPrize(name:String,organization: String, description:String){
        prizesRepository.createPrize(name,organization,description)
    }

    private fun refreshDataFromNetwork() {
        prizesRepository.refreshData( {
            _prizes.postValue(it)
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
            if (modelClass.isAssignableFrom(CreatePrizeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreatePrizeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

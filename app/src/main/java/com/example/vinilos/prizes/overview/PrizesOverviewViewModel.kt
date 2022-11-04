package com.example.vinilos.prizes.overview

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilos.prizes.network.Prize
import com.example.vinilos.prizes.network.PrizesApi
import kotlinx.coroutines.launch

enum class PrizeApiStatus { LOADING, ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [PrizesOverviewFragment].
 */
class PrizesOverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<PrizeApiStatus>()
    private val _createdPrize = MutableLiveData<Prize>()

    init {

    }

    /**
     * Create prize information from the Prizes API Retrofit service and. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     * @param prize the that is sent as part of the web server request
     */
    fun createPrize(newPrize: Prize) {
        viewModelScope.launch {
            _status.value = PrizeApiStatus.LOADING
            try {
                _createdPrize.value = PrizesApi.prizeService.createPrize(newPrize)
                _status.value = PrizeApiStatus.DONE
            } catch (e: Exception) {
                Log.e("EXCEPTION", e.toString());
                _status.value = PrizeApiStatus.ERROR
            }
        }
    }

}

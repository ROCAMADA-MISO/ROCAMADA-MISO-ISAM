package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter

class BandDetailRepository (val application: Application){
    suspend fun refreshData(bandId: Int):Band {
        return NetworkServiceAdapter.getInstance(application).getBand(bandId)
    }
}
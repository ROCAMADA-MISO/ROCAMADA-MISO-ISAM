package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Musician
import com.example.vinilos.network.NetworkServiceAdapter

class MusicianDetailRepository (val application: Application){
    suspend fun refreshData(musicianId: Int):Musician {
        return NetworkServiceAdapter.getInstance(application).getMusician(musicianId)
    }
}
package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Albums
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumsRepository (val application: Application) {
    fun refreshData(callback: (List<Albums>) -> Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it) 
            },
            onError
        )
        
    }
}
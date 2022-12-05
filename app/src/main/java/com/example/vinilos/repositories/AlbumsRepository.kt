package com.example.vinilos.repositories

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.android.volley.VolleyError
import com.example.vinilos.R
import com.example.vinilos.models.Albums
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumsRepository (val application: Application) {

    fun refreshData(callback: (List<Albums>) -> Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it) 
            },
            onError
        )
        
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createAlbum(name:String, cover: String, releaseDate:String, description:String, genre:String, recordLabel:String) {

        val newAlbum= JSONObject()
        newAlbum.put("name",name)
        newAlbum.put("cover",cover)
        newAlbum.put("releaseDate",releaseDate)
        newAlbum.put("description",description)
        newAlbum.put("genre",genre)
        newAlbum.put("recordLabel",recordLabel)


        return NetworkServiceAdapter.getInstance(application).postAlbum(newAlbum,
            fun (jsonObject: JSONObject){
                Log.v("TAG", "ONCOMPLETE");
                return;
            }, fun (error: VolleyError){
                Log.v("TAG", "ONERROR");
                return;
            }
        )
    }

    fun associateAlbumToBand(bandId:String, albumId: String) {
        return NetworkServiceAdapter.getInstance(application).associateBandAlbum(bandId,albumId,
            fun (jsonObject: JSONObject){
                Log.v("TAG", "ONCOMPLETE");
                return;
        }, fun (error: VolleyError){
            Log.v("TAG", "ONERROR");
            return;
        })
    }



}
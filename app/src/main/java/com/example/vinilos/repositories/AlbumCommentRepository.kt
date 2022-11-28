package com.example.vinilos.repositories

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Albums
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumCommentRepository  (val application: Application){

    suspend fun refreshData(albumId: Int): Album {
        return NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createCommentAlbum(name:String, cover: String, releaseDate:String, description:String, genre:String, recordLabel:String) {

        val n:Int = 1
        val newAlbum= JSONObject()
        newAlbum.put("description",description)
        newAlbum.put("rating",genre)
        newAlbum.put("recordLabel",recordLabel)


        return NetworkServiceAdapter.getInstance(application).postCommentAlbum(newAlbum, n,
            fun (jsonObject: JSONObject){
                Log.v("TAG", "ONCOMPLETE");
                return;
            }, fun (error: VolleyError){
                Log.v("TAG", "ONERROR");
                return;
            }
        )
    }
}
package com.example.vinilos.repositories

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumCommentRepository  (val application: Application){

    suspend fun refreshData(albumId: Int): Album {
        return NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createCommentAlbum( description:String, rating:Int, collector:Collector) {


        val newComment= JSONObject()
        newComment.put("description",description)
        newComment.put("rating",rating)
        newComment.put("collector",collector)



        return NetworkServiceAdapter.getInstance(application).postCommentAlbum(newComment,collector.id,
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
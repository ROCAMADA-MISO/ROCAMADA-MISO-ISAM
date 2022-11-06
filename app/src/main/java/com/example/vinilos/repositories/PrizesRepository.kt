package com.example.vinilos.repositories

import android.app.Application
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Organization
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.volley.Response
import com.android.volley.VolleyError
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.vinilos.models.Prize
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONArray
import org.json.JSONObject

class PrizesRepository (val application: Application){
    fun refreshData(callback: (List<Prize>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getPrizes({
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun createPrize(name:String,organization: String, description: String) {

        val newPrize= JSONObject()
        newPrize.put("name",name)
        newPrize.put("organization",organization)
        newPrize.put("description",description)


        return NetworkServiceAdapter.getInstance(application).postPrize(newPrize,
        fun (jsonObject: JSONObject){
            Log.v("TAG", "OOONCOMPLETE");
            return;
        }, fun (error: VolleyError){
                Log.v("TAG", "ONERROR");
            return;
        }
            )
    }
}
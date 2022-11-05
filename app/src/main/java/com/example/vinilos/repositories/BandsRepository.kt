package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Band
import com.example.vinilos.network.NetworkServiceAdapter

class BandsRepository (val application: Application){
    fun refreshData(callback: (List<Band>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getBands({
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}
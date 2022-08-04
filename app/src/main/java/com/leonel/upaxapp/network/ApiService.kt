package com.leonel.upaxapp.network

import android.util.Log
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.negocio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val api:ApiClient){

    suspend fun getnegocios(request: requestnegocio): List<negocio> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllnegocios(request)
            Log.i("json",response.body().toString())
            response.body()?.comercio?.direcciones ?: emptyList()
        }
    }

    suspend fun getcomercio(request: requestnegocio): Comercio? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllcomercio(request)
            Log.i("json",response.body().toString())
            response.body()?.comercio ?:null
        }
    }
}
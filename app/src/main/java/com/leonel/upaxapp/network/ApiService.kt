package com.leonel.upaxapp.network

import com.leonel.upaxapp.model.negocio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val api:ApiClient){

    suspend fun getnegocios(request: requestnegocio): List<negocio> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllnegocios(request)
            response.body()?.results ?: emptyList()
        }
    }
}
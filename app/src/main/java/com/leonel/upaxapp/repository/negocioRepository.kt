package com.leonel.upaxapp.repository

import com.leonel.upaxapp.database.dao.empleadoDao
import com.leonel.upaxapp.database.dao.negocioDao
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.add
import com.leonel.upaxapp.model.empleado
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.ApiService
import com.leonel.upaxapp.network.requestnegocio
import dagger.Module
import javax.inject.Inject

class negocioRepository @Inject constructor(private val api: ApiService, private val negociodaorep: negocioDao)
{

    suspend fun getAllNegociosFromApi(request: requestnegocio): List<negocio> {
        try {
            val response: List<negocio> = api.getnegocios(request)
            return response.map { it.add() }
        } catch (e: Exception) {
            val mutableEmptyList: MutableList<negocio> = mutableListOf()
            return mutableEmptyList
        }
    }

   suspend fun getAllNegociosFromDatabase():List<negocio>{
        val response: List<negocioEntity> = negociodaorep.getAllnegocios()
        return response.map { it.add() }
    }
    //**********************
    suspend fun insertnegocio(negocios: List<negocioEntity>){
        try {
            negociodaorep.insertAllnegocios(negocios)
        } catch (e: Exception) {
        }
    }

    suspend fun clearnegocios(){
        try {
            negociodaorep.deleteAllnegocios()
        } catch (e: Exception) {
        }
    }
}
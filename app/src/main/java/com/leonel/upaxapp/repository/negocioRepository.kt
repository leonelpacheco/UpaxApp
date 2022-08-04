package com.leonel.upaxapp.repository

import com.leonel.upaxapp.database.dao.comercioDao
import com.leonel.upaxapp.database.dao.negocioDao
import com.leonel.upaxapp.database.entities.comercioEntity
import com.leonel.upaxapp.database.entities.negocioEntity
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.Comerciosave
import com.leonel.upaxapp.model.add
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.ApiService
import com.leonel.upaxapp.network.requestnegocio
import javax.inject.Inject

class negocioRepository @Inject constructor(private val api: ApiService, private val negociodaorep: negocioDao, private val comerciodao: comercioDao)
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

    //***************obtener Comercio*********
    suspend fun getAllComercioFromApi(request: requestnegocio): Comercio? {

        try {
            val response: Comercio = api.getcomercio(request)!!
            return response
        } catch (e: Exception) {
            return null
        }


    }
    suspend fun insertcomercio(comercio: comercioEntity){
        try {
            comerciodao.insertAllcomercios(comercio)
        } catch (e: Exception) {
        }
    }

    suspend fun clearcomercio() {
        try {
            comerciodao.deleteAllcomercios()
        } catch (e: Exception) {
        }
    }

    suspend fun getAllComerciosFromDatabase(): List<Comerciosave> {
        val response: List<comercioEntity> = comerciodao.getAllcomercios()
        return response.map { it.add() }
    }
    //**************************
}
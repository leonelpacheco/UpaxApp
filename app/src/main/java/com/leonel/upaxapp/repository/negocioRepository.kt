package com.leonel.upaxapp.repository

import com.leonel.upaxapp.database.dao.empleadoDao
import com.leonel.upaxapp.database.dao.negocioDao
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity
import com.leonel.upaxapp.model.add
import com.leonel.upaxapp.model.empleado
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.ApiService
import com.leonel.upaxapp.network.requestnegocio
import javax.inject.Inject

class negocioRepository @Inject constructor(private val api: ApiService)
{

    suspend fun getAllNegociosFromApi(request: requestnegocio): List<negocio> {
        val response: List<negocio> = api.getnegocios(request)
        return response.map { it.add() }
    }

/*    suspend fun getAllNegociosFromDatabase():List<negocio>{
        val response: List<negocioEntity> = negocioDao.getAllnegocios()
        return response.map { it.add() }
    }*/
    //**********************
/*    suspend fun insertnegocio(negocio: negocioEntity){
        negocioDao.insertAll(negocio)
    }

    suspend fun clearnegocios(){
        negocioDao.deleteAllnegocios()
    }*/
}
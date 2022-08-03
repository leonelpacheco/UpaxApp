package com.leonel.upaxapp.repository

import com.leonel.upaxapp.database.dao.empleadoDao
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.model.add
import com.leonel.upaxapp.model.empleado
import javax.inject.Inject

class empleadoRepository @Inject constructor(private val empleadoDao: empleadoDao) {

    suspend fun getAllEmpleadosFromDatabase():List<empleado>{
        val response: List<empleadoEntity> = empleadoDao.getAllempleados()
        return response.map { it.add() }
    }
    //**********************
    suspend fun insertempleado(empleado:empleadoEntity){
        empleadoDao.insertAll(empleado)
    }

    suspend fun clearempleados(){
        empleadoDao.deleteAllempleados()
    }
}
package com.leonel.upaxapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity

@Dao
interface empleadoDao {
    @Query("SELECT * FROM empleado_table ORDER BY id DESC")
    suspend fun getAllempleados():List<empleadoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(empleado:empleadoEntity)

    @Query("DELETE FROM empleado_table")
    suspend fun deleteAllempleados()

    //*******************

    @Query("SELECT * FROM negocio_table ORDER BY id DESC")
    suspend fun getAllnegocios():List<negocioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllnegocios(negocio: negocioEntity)

    @Query("DELETE FROM negocio_table")
    suspend fun deleteAllnegocios()
}
package com.leonel.upaxapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity
import com.leonel.upaxapp.model.negocio

@Dao
interface negocioDao {
    @Query("SELECT * FROM negocio_table ORDER BY id DESC")
    suspend fun getAllnegocios():List<negocioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(negocio: negocioEntity)

    @Query("DELETE FROM negocio_table")
    suspend fun deleteAllnegocios()
}
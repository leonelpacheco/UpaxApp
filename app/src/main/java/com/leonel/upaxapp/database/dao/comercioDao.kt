package com.leonel.upaxapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leonel.upaxapp.database.entities.comercioEntity
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.Comerciosave

@Dao
interface comercioDao {
    @Query("SELECT * FROM comercio_table ORDER BY id DESC")
    suspend fun getAllcomercios():List<comercioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllcomercios(comercio: comercioEntity)

    @Query("DELETE FROM comercio_table")
    suspend fun deleteAllcomercios()
}
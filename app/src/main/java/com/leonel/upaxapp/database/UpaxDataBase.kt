package com.leonel.upaxapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leonel.upaxapp.database.dao.empleadoDao
import com.leonel.upaxapp.database.dao.negocioDao
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity

@Database(entities = [empleadoEntity::class, negocioEntity::class], version = 3)
abstract class UpaxDataBase: RoomDatabase() {

    abstract fun getEmpleadoDao(): empleadoDao
    abstract fun getNegocioDao(): negocioDao

}
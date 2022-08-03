package com.leonel.upaxapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonel.upaxapp.model.empleado


@Entity(tableName = "empleado_table")
data class empleadoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "direccion") val direccion: String,
    @ColumnInfo(name = "imagen") val imagen: String
)
fun empleado.toDataBase() = empleadoEntity(nombre = nombre,telefono = telefono,email = email,direccion = direccion,imagen = imagen)
package com.leonel.upaxapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonel.upaxapp.model.negocio

@Entity(tableName = "negocio_table")
data class negocioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "calle") val calle: String,
    @ColumnInfo(name = "numero_exterior") val numero_exterior: String,
    @ColumnInfo(name = "numero_interior") val numero_interior: String,
    @ColumnInfo(name = "codigo_postal") val codigo_postal: String,
    @ColumnInfo(name = "nombrePais") val nombrePais: String,
    @ColumnInfo(name = "nombreEstado") val nombreEstado: String,
    @ColumnInfo(name = "nombreMunicipio") val nombreMunicipio: String,
    @ColumnInfo(name = "nombreColonia") val nombreColonia: String,
    @ColumnInfo(name = "latitud") val latitud: String,
    @ColumnInfo(name = "longitud") val longitud: String
)
fun negocio.toDataBase() = negocioEntity(calle = calle,numero_exterior = numero_exterior,numero_interior = numero_interior,codigo_postal = codigo_postal
    ,nombrePais = nombrePais,nombreEstado=nombreEstado ,nombreMunicipio=nombreMunicipio,nombreColonia=nombreColonia, latitud=latitud,  longitud=longitud)

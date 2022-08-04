package com.leonel.upaxapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.Comerciosave
import com.leonel.upaxapp.model.negocio

@Entity(tableName = "comercio_table")
data class comercioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "fiIdZeus") val fiIdZeus: String?,
    @ColumnInfo(name = "fcIdComercio") val fcIdComercio: String?,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "telefono") val telefono: String?,
    @ColumnInfo(name = "tipoComercio") val tipoComercio: String?,
    @ColumnInfo(name = "idGiro") val idGiro: String?,
    @ColumnInfo(name = "idCategoria") val idCategoria: String?,
    @ColumnInfo(name = "idSubcategoria") val idSubcategoria: String?,
    @ColumnInfo(name = "idDisponibilidad") val idDisponibilidad: String?,
    @ColumnInfo(name = "idEstatusLevantamiento") val idEstatusLevantamiento: String?,
    @ColumnInfo(name = "urlImagen") val urlImagen: String?
)
fun Comerciosave.toDataBase() = comercioEntity(fiIdZeus = fiIdZeus,fcIdComercio = fcIdComercio,nombre = nombre,
    descripcion = descripcion,email = email,telefono=telefono ,tipoComercio=tipoComercio,
    idGiro=idGiro,idCategoria=idCategoria,  idSubcategoria=idSubcategoria,
    idDisponibilidad=idDisponibilidad, idEstatusLevantamiento=idEstatusLevantamiento,
    urlImagen=urlImagen)

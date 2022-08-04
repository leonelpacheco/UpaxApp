package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.database.entities.comercioEntity

data class Comerciosave (

    @SerializedName("fiIdZeus") val fiIdZeus: String?,
    @SerializedName("fcIdComercio") val fcIdComercio: String?,
    @SerializedName("nombre") val nombre:  String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("telefono") val telefono:  String?,
    @SerializedName("tipoComercio") val tipoComercio: String?,
    @SerializedName("idGiro") val idGiro: String?,
    @SerializedName("idCategoria") val idCategoria: String?,
    @SerializedName("idSubcategoria") val idSubcategoria: String?,
    @SerializedName("idDisponibilidad") val idDisponibilidad: String?,
    @SerializedName("idEstatusLevantamiento") val idEstatusLevantamiento: String?,
    @SerializedName("urlImagen") val urlImagen:  String?
)
fun Comerciosave.add()=Comerciosave(fiIdZeus,fcIdComercio,nombre,descripcion,email,telefono,tipoComercio,idGiro,idCategoria,idSubcategoria,
    idDisponibilidad, idEstatusLevantamiento,urlImagen)
fun comercioEntity.add()=Comerciosave(fiIdZeus,fcIdComercio,nombre,descripcion,email,telefono,tipoComercio,idGiro,idCategoria,idSubcategoria,
    idDisponibilidad, idEstatusLevantamiento,urlImagen)
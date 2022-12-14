package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.database.entities.comercioEntity
import com.leonel.upaxapp.database.entities.negocioEntity

data class Comercio (

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
    @SerializedName("urlImagen") val urlImagen:  String?,
    @SerializedName("direcciones") var direcciones: List<negocio>?
)
fun Comercio.add()=Comercio(fiIdZeus,fcIdComercio,nombre,descripcion,email,telefono,tipoComercio,idGiro,idCategoria,idSubcategoria,
    idDisponibilidad, idEstatusLevantamiento,urlImagen,direcciones)
/*
fun comercioEntity.add()=Comercio(fiIdZeus,fcIdComercio,nombre,descripcion,email,telefono,tipoComercio,idGiro,idCategoria,idSubcategoria,
    idDisponibilidad, idEstatusLevantamiento,urlImagen,null)*/

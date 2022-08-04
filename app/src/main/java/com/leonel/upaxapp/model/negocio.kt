package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.database.entities.empleadoEntity
import com.leonel.upaxapp.database.entities.negocioEntity

data class negocio (

                    @SerializedName("calle") val calle: String?,
                    @SerializedName("numeroExterior") val numero_exterior: String?,
                    @SerializedName("numeroInterior") val numero_interior: String?,
                    @SerializedName("codigoPostal") val codigo_postal: String?,
                    @SerializedName("nombrePais") val nombrePais: String?,
                    @SerializedName("nombreEstado") val nombreEstado: String?,
                    @SerializedName("nombreMunicipio") val nombreMunicipio: String?,
                    @SerializedName("colonia") val nombreColonia: String?,
                    @SerializedName("latitud") val latitud: String?,
                    @SerializedName("longitud") val longitud: String?,
                    @SerializedName("urlImagen") val urlImagen: String?
)
fun negocio.add()=negocio(calle,numero_exterior,numero_interior,codigo_postal,nombrePais,nombreEstado,nombreMunicipio,nombreColonia,latitud,longitud,urlImagen)
fun negocioEntity.add()=negocio(calle,numero_exterior,numero_interior,codigo_postal,nombrePais,nombreEstado,nombreMunicipio,nombreColonia,latitud,longitud, urlImagen)
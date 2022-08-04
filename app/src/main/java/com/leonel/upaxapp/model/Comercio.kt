package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName

data class Comercio (

    @SerializedName("fcIdComercio") val fcIdComercio: String,
    @SerializedName("nombre") val nombre:  String,
    @SerializedName("telefono") val telefono:  String,
    @SerializedName("urlImagen") val urlImagen:  String,
    @SerializedName("direcciones") val direcciones: List<negocio>
)
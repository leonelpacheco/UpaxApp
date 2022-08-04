package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName

data class HorariosAtencion(
    @SerializedName("codeDia") val codeDia: String,
    @SerializedName("idTipoHora") val idTipoHora: String,
    @SerializedName("tipoHora") val tipoHora:  String,
    @SerializedName("hora") val hora: String,
)

package com.leonel.upaxapp.network

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.model.negocio

data class requestnegocio (
    @SerializedName("idZeusComercio") val idZeusComercio: Int =0
)

package com.leonel.upaxapp.network

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.HorariosAtencion
import com.leonel.upaxapp.model.negocio

data class responsenegocios (

    @SerializedName("comercio") val comercio: Comercio,
    @SerializedName("horariosAtencion") val horariosAtencion: List<HorariosAtencion>

    )
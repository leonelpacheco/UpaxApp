package com.leonel.upaxapp.network

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.model.negocio

data class responsenegocios (
    @SerializedName("results") val results: List<negocio>
        )
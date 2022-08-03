package com.leonel.upaxapp.network

import com.denariuspak.examengnach.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {
    @POST(Constants.URL_BASE)
    suspend fun getAllnegocios(@Body idZeusComercio: requestnegocio): Response<responsenegocios>
}
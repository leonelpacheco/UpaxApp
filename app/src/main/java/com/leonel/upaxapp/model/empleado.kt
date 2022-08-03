package com.leonel.upaxapp.model

import com.google.gson.annotations.SerializedName
import com.leonel.upaxapp.database.entities.empleadoEntity

data class empleado(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("email") val email: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("imagen") val imagen: String
    )
fun empleado.add()=empleado(id,nombre,telefono,email,direccion,imagen)
fun empleadoEntity.add()=empleado(id,nombre,telefono,email,direccion,imagen)



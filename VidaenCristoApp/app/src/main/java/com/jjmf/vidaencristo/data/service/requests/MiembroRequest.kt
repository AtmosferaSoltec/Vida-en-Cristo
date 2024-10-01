package com.jjmf.vidaencristo.data.service.requests

import com.google.gson.annotations.SerializedName

data class MiembroRequest(
    @SerializedName("dni") val dni: String,
    @SerializedName("nombres") val nombres: String,
    @SerializedName("apellidos") val apellidos: String,
    @SerializedName("celular") val celular: String,
    @SerializedName("direc") val direc: String,
    @SerializedName("fecha_nac") val fechaNac: String,
    @SerializedName("id_distrito") val idDistrito: Int
)
package com.jjmf.vidaencristo.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.vidaencristo.domain.model.Familia

data class FamiliaDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("miembros") val miembros: List<MiembroDto?>?
){
    fun toDomain(): Familia {
        return Familia(
            id = id ?: 0,
            nombre = nombre ?: "",
            miembros = miembros?.mapNotNull { it?.toDomain() } ?: emptyList()
        )
    }
}



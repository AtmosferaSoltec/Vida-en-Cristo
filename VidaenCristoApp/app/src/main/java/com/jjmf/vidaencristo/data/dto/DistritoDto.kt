package com.jjmf.vidaencristo.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.vidaencristo.domain.model.Distrito

data class DistritoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?
) {
    fun toDomain(): Distrito {
        return Distrito(
            id = id ?: 0,
            nombre = nombre ?: ""
        )
    }
}

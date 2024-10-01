package com.jjmf.vidaencristo.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.vidaencristo.domain.model.Miembro
import com.jjmf.vidaencristo.util.toDate2
import java.util.Date

data class MiembroDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("dni") val dni: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("apellidos") val apellidos: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("distrito") val distrito: String?,
    @SerializedName("direc") val direc: String?,
    @SerializedName("fecha_nac") val fechaNac: String?
) {
    fun toDomain(): Miembro {
        return Miembro(
            id = id ?: 0,
            dni = dni ?: "",
            nombres = nombres ?: "",
            apellidos = apellidos ?: "",
            celular = celular ?: "",
            distrito = distrito ?: "",
            direc = direc ?: "",
            fechaNac = fechaNac?.toDate2() ?: Date()
        )
    }
}

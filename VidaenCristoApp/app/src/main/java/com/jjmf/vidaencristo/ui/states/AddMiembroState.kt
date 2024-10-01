package com.jjmf.vidaencristo.ui.states

import com.jjmf.vidaencristo.domain.model.Distrito

data class AddMiembroState(
    val dni : String = "",
    val nombres: String = "",
    val apellidos: String = "",
    val celular: String = "",
    val distrito: Distrito? = null,
    val direc: String = "",
    val fechaNac: String = ""
)

package com.jjmf.vidaencristo.ui.events

import com.jjmf.vidaencristo.domain.model.Distrito

sealed class AddMiembroEvent {
    data class SetDni(val value: String): AddMiembroEvent()
    data class SetNombres(val value: String): AddMiembroEvent()
    data class SetApellidos(val value: String): AddMiembroEvent()
    data class SetCelular(val value: String): AddMiembroEvent()
    data class SetDistrito(val value: Distrito) : AddMiembroEvent()
    data class SetDirec(val value: String) : AddMiembroEvent()
    data class SetFechaNac(val value: String) : AddMiembroEvent()
}
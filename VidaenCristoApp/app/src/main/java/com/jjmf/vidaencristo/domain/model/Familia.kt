package com.jjmf.vidaencristo.domain.model

data class Familia(
    val id: Int,
    val nombre: String,
    val miembros: List<Miembro>
)

package com.jjmf.vidaencristo.domain.model

import java.util.Date

data class Miembro(
    val id:Int,
    val dni:String,
    val nombres: String,
    val apellidos: String,
    val celular: String,
    val distrito: String,
    val direc: String,
    val fechaNac: Date
){
    fun fullName(): String {
        val firstName = nombres.split(" ").getOrNull(0) ?: ""
        val lastName = apellidos.split(" ").getOrNull(0) ?: ""
        return "$firstName $lastName"
    }
}

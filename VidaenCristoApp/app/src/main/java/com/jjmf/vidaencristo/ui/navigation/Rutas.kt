package com.jjmf.vidaencristo.ui.navigation

sealed class Rutas(val route: String) {

    data object Login: Rutas("login")
    data object Menu: Rutas("menu")
    data object Miembros: Rutas("miembros")
    data object AddMiembro: Rutas("addMiembro")
    data object VerMiembro: Rutas("verMiembro?{id}"){
        fun sendId(id: Int) = "verMiembro?$id"
    }
    data object Familias: Rutas("familias")

}
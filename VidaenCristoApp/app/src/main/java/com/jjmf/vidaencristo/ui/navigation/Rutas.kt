package com.jjmf.vidaencristo.ui.navigation

sealed class Rutas(val route: String) {

    data object Login: Rutas("login")
}
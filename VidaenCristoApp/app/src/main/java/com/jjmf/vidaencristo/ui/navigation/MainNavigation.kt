package com.jjmf.vidaencristo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.vidaencristo.ui.features.login.LoginScreen

@Composable
fun MainNavigation(modifier: Modifier) {
    val nav = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = nav,
        startDestination = Rutas.Login.route
    ) {
        composable(
            route = Rutas.Login.route
        ){
            LoginScreen()
        }
    }
}
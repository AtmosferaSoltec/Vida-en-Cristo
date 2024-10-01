package com.jjmf.vidaencristo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jjmf.vidaencristo.ui.screens.AddMiembroScreen
import com.jjmf.vidaencristo.ui.screens.FamiliasScreen
import com.jjmf.vidaencristo.ui.screens.VerMiembroScreen
import com.jjmf.vidaencristo.ui.screens.LoginScreen
import com.jjmf.vidaencristo.ui.screens.MenuScreen
import com.jjmf.vidaencristo.ui.screens.MiembrosScreen

@Composable
fun MainNavigation(modifier: Modifier) {
    val nav = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = nav,
        startDestination = Rutas.Menu.route
    ) {
        composable(
            route = Rutas.Login.route
        ) {
            LoginScreen()
        }

        composable(
            route = Rutas.Menu.route
        ) {
            MenuScreen(
                toMiembros = {
                    nav.navigate(Rutas.Miembros.route)
                },
                toFamilias = {
                    nav.navigate(Rutas.Familias.route)
                }
            )
        }

        composable(
            route = Rutas.Miembros.route
        ){
            MiembrosScreen(
                toAdd = {
                    nav.navigate(Rutas.AddMiembro.route)
                },
                toDetalle = { idMiembro ->
                    nav.navigate(Rutas.VerMiembro.sendId(idMiembro))
                }
            )
        }

        composable(
            route = Rutas.AddMiembro.route
        ){
            AddMiembroScreen(
                back = {
                    nav.popBackStack()
                }
            )
        }

        composable(
            route = Rutas.VerMiembro.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val idMiembro = it.arguments?.getInt("id") ?: return@composable
            VerMiembroScreen(idMiembro = idMiembro)
        }

        /** Familias **/
        composable(
            route = Rutas.Familias.route
        ) {
            FamiliasScreen()
        }
    }
}
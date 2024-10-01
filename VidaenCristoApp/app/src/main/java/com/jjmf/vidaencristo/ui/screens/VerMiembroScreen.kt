package com.jjmf.vidaencristo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.vidaencristo.ui.components.CajaText
import com.jjmf.vidaencristo.ui.viewmodels.MiembroViewModel
import com.jjmf.vidaencristo.util.format

@Composable
fun VerMiembroScreen(
    idMiembro: Int,
    viewModel: MiembroViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.get(idMiembro)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Detalle Miembro",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        CajaText(
            value = viewModel.miembro?.dni ?: "",
            newValue = {},
            label = "DNI"
        )

        CajaText(
            value = viewModel.miembro?.nombres ?: "",
            newValue = {},
            label = "Nombres"
        )

        CajaText(
            value = viewModel.miembro?.apellidos ?: "",
            newValue = {},
            label = "Apellidos"
        )

        CajaText(
            value = viewModel.miembro?.celular ?: "",
            newValue = {},
            label = "Telefono"
        )

        CajaText(
            value = viewModel.miembro?.fechaNac?.format("dd/MM/yyyy") ?: "Sin valor",
            newValue = {},
            label = "Fecha Nacimiento"
        )

        CajaText(
            value = viewModel.miembro?.distrito ?: "",
            newValue = {},
            label = "Distrito"
        )

        CajaText(
            value = viewModel.miembro?.direc ?: "",
            newValue = {},
            label = "Direccion"
        )

    }

}
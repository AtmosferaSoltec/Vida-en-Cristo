package com.jjmf.vidaencristo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.vidaencristo.ui.components.CajaFecha
import com.jjmf.vidaencristo.ui.components.CajaSelect
import com.jjmf.vidaencristo.ui.components.CajaText
import com.jjmf.vidaencristo.ui.events.AddMiembroEvent
import com.jjmf.vidaencristo.ui.viewmodels.DistritoViewModel
import com.jjmf.vidaencristo.ui.viewmodels.MiembroViewModel

@Composable
fun AddMiembroScreen(
    back: () -> Unit,
    viewModel: MiembroViewModel = hiltViewModel(),
    distritoViewModel: DistritoViewModel = hiltViewModel()
) {

    val state = viewModel.addMiembroState

    LaunchedEffect(key1 = Unit) {
        distritoViewModel.getAll()
    }

    if (viewModel.back) {
        LaunchedEffect(Unit) {
            viewModel.back = false
            back()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Agregar Miembro",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        CajaText(
            value = state.dni,
            newValue = {
                viewModel.setEvent(AddMiembroEvent.SetDni(it))
            },
            label = "DNI",
            keyboardType = KeyboardType.Number,
            max = 8
        )
        CajaText(
            value = state.nombres,
            newValue = {
                viewModel.setEvent(AddMiembroEvent.SetNombres(it))
            },
            label = "Nombres"
        )
        CajaText(
            value = state.apellidos,
            newValue = {
                viewModel.setEvent(AddMiembroEvent.SetApellidos(it))
            },
            label = "Apellidos"
        )
        CajaText(
            value = state.celular,
            newValue = {
                viewModel.setEvent(AddMiembroEvent.SetCelular(it))
            },
            label = "Celular",
            keyboardType = KeyboardType.Number
        )
        CajaSelect(
            value = state.distrito,
            newValue = { distrito ->
                viewModel.setEvent(AddMiembroEvent.SetDistrito(distrito))
            },
            label = "Distrito",
            list = distritoViewModel.list,
            displayMapper = {
                it.nombre
            }
        )
        CajaText(
            value = state.direc,
            newValue = {
                viewModel.setEvent(AddMiembroEvent.SetDirec(it))
            },
            label = "Dirección"
        )

        CajaFecha(
            modifier = Modifier.fillMaxWidth(),
            value = state.fechaNac,
            newValue = { newFecha ->
                viewModel.setEvent(AddMiembroEvent.SetFechaNac(newFecha))
            },
            label = "Fecha de Nacimiento"
        )

        Button(
            onClick = {
                viewModel.add()
            }
        ) {
            Text("Guardar")
        }
    }

}

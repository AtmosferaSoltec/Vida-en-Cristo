package com.jjmf.vidaencristo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.vidaencristo.ui.components.CajaText
import com.jjmf.vidaencristo.ui.viewmodels.DistritoViewModel
import com.jjmf.vidaencristo.ui.viewmodels.MiembroViewModel
import com.jjmf.vidaencristo.util.format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMiembroScreen(
    viewModel: MiembroViewModel = hiltViewModel(),
    distritoViewModel: DistritoViewModel = hiltViewModel()
) {
    val distrito = remember { mutableStateOf("") }
    val isVisible = remember { mutableStateOf(false) }

    val alertFecha = remember { mutableStateOf(false) }
    val fecha = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

    LaunchedEffect(key1 = Unit) {
        distritoViewModel.getAll()
    }

    if (alertFecha.value) {
        DatePickerDialog(
            onDismissRequest = {
                alertFecha.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        val selectedDateMillis = fecha.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val calendar = Calendar.getInstance().apply {
                                timeInMillis = selectedDateMillis
                            }
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val formattedDate = dateFormat.format(calendar.time)
                            println("Fecha seleccionada: $formattedDate")
                        }
                        alertFecha.value = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
        ) {
            DatePicker(
                state = fecha,
                title = null,
                showModeToggle = false,
                headline = null
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Agregar Miembro",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(text = Date(fecha.displayedMonthMillis).format("dd/MM/yyyy") ?: "Sin Fecha")
        CajaText(
            value = "",
            newValue = {},
            label = "DNI",
            keyboardType = KeyboardType.Number
        )
        CajaText(
            value = "",
            newValue = {},
            label = "Nombres"
        )
        CajaText(
            value = "",
            newValue = {},
            label = "Apellidos"
        )
        CajaText(
            value = "",
            newValue = {},
            label = "Celular",
            keyboardType = KeyboardType.Number
        )
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = distrito.value,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Distrito")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isVisible.value = true
                        }
                    ) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = isVisible.value,
                onDismissRequest = {
                    isVisible.value = false
                }
            ) {
                distritoViewModel.list.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(it.nombre)
                        },
                        onClick = {
                            distrito.value = it.nombre
                            isVisible.value = false
                        }
                    )
                }
            }
        }
        CajaText(
            value = "",
            newValue = {},
            label = "Dirección"
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Fecha de Nacimiento")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        alertFecha.value = true
                    }
                ) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null)
                }
            }
        )
    }

}
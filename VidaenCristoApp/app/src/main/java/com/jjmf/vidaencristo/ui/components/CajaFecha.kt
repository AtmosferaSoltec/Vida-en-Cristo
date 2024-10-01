package com.jjmf.vidaencristo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CajaFecha(
    modifier: Modifier = Modifier,
    value: String,
    newValue: (String) -> Unit,
    label : String
) {

    val alertFecha = remember { mutableStateOf(false) }
    val fechaState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

    if (alertFecha.value) {
        DatePickerDialog(
            onDismissRequest = {
                alertFecha.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        val selectedDateMillis = fechaState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val calendar = Calendar.getInstance().apply {
                                timeInMillis = selectedDateMillis
                            }
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val formattedDate = dateFormat.format(calendar.time)
                            newValue(formattedDate)
                        }
                        alertFecha.value = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
        ) {
            DatePicker(
                state = fechaState,
                title = null,
                showModeToggle = false,
                headline = null
            )
        }
    }


    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = label)
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
package com.jjmf.vidaencristo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun <T> CajaSelect(
    modifier: Modifier = Modifier,
    value: T?,
    newValue: (T) -> Unit,
    label: String,
    list: List<T>,
    displayMapper: (T) -> String
) {
    val expanded = remember { mutableStateOf(false) }
    val displayText = value?.let { displayMapper(it) }
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = displayText ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        expanded.value = true
                    }
                ) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }
            }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            list.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        newValue(item)
                        expanded.value = false
                    },
                    text = {
                        Text(text = displayMapper(item))
                    }
                )
            }
        }
    }
}
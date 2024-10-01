package com.jjmf.vidaencristo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CajaText(
    modifier: Modifier = Modifier,
    value: String,
    newValue: (String) -> Unit,
    label: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = newValue,
        label = if (label != null) {
            { Text(label) }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
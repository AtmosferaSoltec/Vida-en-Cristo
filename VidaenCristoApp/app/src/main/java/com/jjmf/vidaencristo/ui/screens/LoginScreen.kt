package com.jjmf.vidaencristo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

        Button(
            onClick = {
            }
        ) {
            Text(text = "Contador")
        }
    }
}
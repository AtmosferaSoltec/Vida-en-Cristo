package com.jjmf.vidaencristo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.vidaencristo.ui.viewmodels.MiembroViewModel
import com.jjmf.vidaencristo.util.format

enum class Meses(val value: String) {
    Enero("01"),
    Febrero("02"),
    Marzo("03"),
    Abril("04"),
    Mayo("05"),
    Junio("06"),
    Julio("07"),
    Agosto("08"),
    Septiembre("09"),
    Octubre("10"),
    Noviembre("11"),
    Diciembre("12")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiembrosScreen(
    toAdd: () -> Unit,
    toDetalle: (Int) -> Unit,
    viewModel: MiembroViewModel = hiltViewModel()
) {


    LaunchedEffect(Unit) {
        viewModel.getMiembros()
    }

    val mesSelected = remember { mutableStateOf<Meses?>(null) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Miembros",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = toAdd
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when {
                    viewModel.isLoading -> CircularProgressIndicator()
                    viewModel.list.isEmpty() -> Text("No hay miembros")
                    else -> {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                items(Meses.entries) {
                                    ElevatedFilterChip(
                                        selected = true,
                                        onClick = {
                                            mesSelected.value = it
                                        },
                                        label = {
                                            Text(it.name)
                                        }
                                    )
                                }
                            }
                            Row {
                                Text(
                                    modifier = Modifier.padding(start = 12.dp),
                                    text = "Total: ${viewModel.list.size}"
                                )
                            }
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(viewModel.list.filter {
                                    if (mesSelected.value == null) true else it.fechaNac.format(
                                        "MM"
                                    ) == mesSelected.value!!.value
                                }.sortedBy { it.nombres }) { miembro ->
                                    Column {
                                        ListItem(
                                            leadingContent = {
                                                Box(
                                                    modifier = Modifier
                                                        .size(42.dp)
                                                        .clip(CircleShape)
                                                        .background(MaterialTheme.colorScheme.primary),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = miembro.nombres.first().toString(),
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            },
                                            headlineContent = {
                                                Text(miembro.fullName())
                                            },
                                            supportingContent = {
                                                Text(
                                                    miembro.fechaNac.format("dd/MM/yyyy")
                                                        ?: "Sin fecha"
                                                )
                                            },
                                            trailingContent = {
                                                IconButton(
                                                    onClick = {
                                                        toDetalle(miembro.id)
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.Default.Edit,
                                                        contentDescription = null
                                                    )
                                                }
                                            }
                                        )
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
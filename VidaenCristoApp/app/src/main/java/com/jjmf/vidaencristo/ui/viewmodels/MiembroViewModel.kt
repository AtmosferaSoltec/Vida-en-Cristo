package com.jjmf.vidaencristo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.vidaencristo.data.service.requests.MiembroRequest
import com.jjmf.vidaencristo.domain.model.Miembro
import com.jjmf.vidaencristo.domain.repository.MiembroRepository
import com.jjmf.vidaencristo.ui.events.AddMiembroEvent
import com.jjmf.vidaencristo.ui.states.AddMiembroState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiembroViewModel @Inject constructor(
    private val repo: MiembroRepository
) : ViewModel() {

    var addMiembroState by mutableStateOf(AddMiembroState())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var list by mutableStateOf<List<Miembro>>(emptyList())
        private set

    var miembro by mutableStateOf<Miembro?>(null)
        private set

    fun getMiembros() {
        viewModelScope.launch {
            try {
                isLoading = true
                delay(500)
                list = repo.getAll()
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }

    fun get(id: Int) {
        viewModelScope.launch {
            try {
                miembro = repo.getById(id)
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }

    var back by mutableStateOf(false)

    fun add() {
        viewModelScope.launch {
            try {

                if (addMiembroState.distrito?.id != null) {
                    return@launch
                }

                val request = MiembroRequest(
                    dni = addMiembroState.dni,
                    nombres = addMiembroState.nombres,
                    apellidos = addMiembroState.apellidos,
                    celular = addMiembroState.celular,
                    direc = addMiembroState.direc,
                    fechaNac = addMiembroState.fechaNac,
                    idDistrito = addMiembroState.distrito!!.id
                )
                repo.insert(request)
                back = true
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }

    fun setEvent(e: AddMiembroEvent) {
        addMiembroState = when (e) {
            is AddMiembroEvent.SetDni -> addMiembroState.copy(dni = e.value)
            is AddMiembroEvent.SetNombres -> addMiembroState.copy(nombres = e.value)
            is AddMiembroEvent.SetApellidos -> addMiembroState.copy(apellidos = e.value)
            is AddMiembroEvent.SetCelular -> addMiembroState.copy(celular = e.value)
            is AddMiembroEvent.SetDistrito -> addMiembroState.copy(distrito = e.value)
            is AddMiembroEvent.SetDirec -> addMiembroState.copy(direc = e.value)
            is AddMiembroEvent.SetFechaNac -> addMiembroState.copy(fechaNac = e.value)
        }
    }
}
package com.jjmf.vidaencristo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.vidaencristo.domain.model.Miembro
import com.jjmf.vidaencristo.domain.repository.MiembroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiembroViewModel @Inject constructor(
    private val repo: MiembroRepository
) : ViewModel() {


    var isLoading by mutableStateOf(false)
    var list by mutableStateOf<List<Miembro>>(emptyList())

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

    var miembro by mutableStateOf<Miembro?>(null)

    fun get(id: Int) {
        viewModelScope.launch {
            try {
                miembro = repo.getById(id)
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }
}
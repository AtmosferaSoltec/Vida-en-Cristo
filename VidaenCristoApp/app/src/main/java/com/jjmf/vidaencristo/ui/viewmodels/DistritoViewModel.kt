package com.jjmf.vidaencristo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.vidaencristo.domain.model.Distrito
import com.jjmf.vidaencristo.domain.repository.DistritoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DistritoViewModel @Inject constructor(
    private val repo: DistritoRepository
) : ViewModel() {

    var list by mutableStateOf<List<Distrito>>(emptyList())

    fun getAll() {
        viewModelScope.launch {
            try {
                list = repo.getAll()
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }

}
package com.jjmf.vidaencristo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.vidaencristo.domain.model.Familia
import com.jjmf.vidaencristo.domain.repository.FamiliaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamiliaViewModel @Inject constructor(
    private val repo: FamiliaRepository
) : ViewModel() {

    var list by mutableStateOf<List<Familia>>(emptyList())

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
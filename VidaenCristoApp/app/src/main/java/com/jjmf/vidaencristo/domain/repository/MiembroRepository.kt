package com.jjmf.vidaencristo.domain.repository

import com.jjmf.vidaencristo.domain.model.Miembro

interface MiembroRepository {
    suspend fun getAll(): List<Miembro>
    suspend fun getById(id: Int): Miembro?
}
package com.jjmf.vidaencristo.domain.repository

import com.jjmf.vidaencristo.domain.model.Familia

interface FamiliaRepository {
    suspend fun getAll(): List<Familia>
}
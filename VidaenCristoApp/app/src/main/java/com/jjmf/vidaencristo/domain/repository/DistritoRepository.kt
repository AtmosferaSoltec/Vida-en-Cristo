package com.jjmf.vidaencristo.domain.repository

import com.jjmf.vidaencristo.domain.model.Distrito

interface DistritoRepository {
    suspend fun getAll(): List<Distrito>
}
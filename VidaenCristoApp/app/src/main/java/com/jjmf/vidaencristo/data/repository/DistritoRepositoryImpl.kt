package com.jjmf.vidaencristo.data.repository

import com.jjmf.vidaencristo.data.service.DistritoService
import com.jjmf.vidaencristo.domain.model.Distrito
import com.jjmf.vidaencristo.domain.repository.DistritoRepository
import javax.inject.Inject

class DistritoRepositoryImpl @Inject constructor(
    private val api: DistritoService
) : DistritoRepository {
    override suspend fun getAll(): List<Distrito> {
        try {
            val call = api.getAll()
            if (call.isSuccessful) {
                return call.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                throw Exception("Error al obtener los distritos")
            }
        } catch (e: Exception) {
            throw e
        }
    }

}
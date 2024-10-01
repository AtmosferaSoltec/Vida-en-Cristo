package com.jjmf.vidaencristo.data.repository

import com.jjmf.vidaencristo.data.service.MiembroService
import com.jjmf.vidaencristo.domain.model.Miembro
import com.jjmf.vidaencristo.domain.repository.MiembroRepository
import javax.inject.Inject

class MiembroRepositoryImpl @Inject constructor(
    private val api: MiembroService
) : MiembroRepository {
    override suspend fun getAll(): List<Miembro> {
        try {
            val call = api.getAll()
            if (call.isSuccessful) {
                return call.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                throw Exception("Error al obtener los miembros")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getById(id: Int): Miembro? {
        try {
            val call = api.getById(id)
            if (call.isSuccessful) {
                return call.body()?.toDomain()
            } else {
                throw Exception("Error al obtener el miembro")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
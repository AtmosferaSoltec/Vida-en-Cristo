package com.jjmf.vidaencristo.data.repository

import com.jjmf.vidaencristo.data.service.FamiliaService
import com.jjmf.vidaencristo.domain.model.Familia
import com.jjmf.vidaencristo.domain.repository.FamiliaRepository
import javax.inject.Inject

class FamiliaRepositoryImpl @Inject constructor(
    private val api: FamiliaService
) : FamiliaRepository {
    override suspend fun getAll(): List<Familia> {
        try {
            val call = api.getAll()
            if (call.isSuccessful) {
                return call.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                throw Exception("Error al obtener las familias")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
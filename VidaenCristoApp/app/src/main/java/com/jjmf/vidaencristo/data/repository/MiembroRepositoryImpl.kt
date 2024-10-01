package com.jjmf.vidaencristo.data.repository

import android.util.Log
import com.google.gson.Gson
import com.jjmf.vidaencristo.data.service.MiembroService
import com.jjmf.vidaencristo.data.service.requests.MiembroRequest
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

    override suspend fun insert(request: MiembroRequest) {
        try {
            val call = api.insert(request)
            if (call.isSuccessful) {
                return
            } else {
                val errorBody = call.errorBody()?.string()
                if (errorBody != null) {
                    val errorResponse = try {
                        Gson().fromJson(errorBody, ErrorResponse::class.java)
                    } catch (e: Exception) {
                        null
                    }
                    if (errorResponse != null) {
                        throw Exception(errorResponse.message)
                    } else {
                        throw Exception("Error al insertar el miembro")
                    }
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

data class ErrorResponse(
    val message: String,
    val error: String,
    val statusCode: Int
)
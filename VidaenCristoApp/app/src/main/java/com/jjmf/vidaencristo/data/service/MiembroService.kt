package com.jjmf.vidaencristo.data.service

import com.jjmf.vidaencristo.data.dto.MiembroDto
import com.jjmf.vidaencristo.data.service.requests.MiembroRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MiembroService {

    @GET("miembro")
    suspend fun getAll(): Response<List<MiembroDto>>

    @GET("miembro/{id}")
    suspend fun getById(@Path("id") id: Int): Response<MiembroDto>

    @POST("miembro")
    suspend fun insert(@Body request: MiembroRequest): Response<Any>

}
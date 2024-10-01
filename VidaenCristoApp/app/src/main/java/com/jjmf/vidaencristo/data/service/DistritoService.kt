package com.jjmf.vidaencristo.data.service

import com.jjmf.vidaencristo.data.dto.DistritoDto
import retrofit2.Response
import retrofit2.http.GET

interface DistritoService {

    @GET("distrito")
    suspend fun getAll(): Response<List<DistritoDto>>
}
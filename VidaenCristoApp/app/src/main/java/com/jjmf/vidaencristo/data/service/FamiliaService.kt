package com.jjmf.vidaencristo.data.service

import com.jjmf.vidaencristo.data.dto.FamiliaDto
import retrofit2.Response
import retrofit2.http.GET

interface FamiliaService {

    @GET("familia")
    suspend fun getAll(): Response<List<FamiliaDto>>

}
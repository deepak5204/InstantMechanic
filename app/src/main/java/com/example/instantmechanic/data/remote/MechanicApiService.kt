package com.example.instantmechanic.data.remote

import com.example.instantmechanic.domain.model.Mechanic
import retrofit2.http.GET

interface MechanicApiService {
    @GET("mechanic")
    suspend fun getMechanics(): List<Mechanic>
}
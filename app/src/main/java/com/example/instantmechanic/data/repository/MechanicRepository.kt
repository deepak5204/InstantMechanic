package com.example.instantmechanic.data.repository

import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.data.remote.MechanicApiService
import com.example.instantmechanic.domain.model.Mechanic
import kotlinx.coroutines.delay
import javax.inject.Inject

class MechanicRepository @Inject constructor(
    private val apiService: MechanicApiService
) {


    suspend fun getMechanics(): List<Mechanic> {

        delay(1000)
        return DummyMechanicsData.mechanics

        // here remote api

//        api.getMechanics()


    }
}
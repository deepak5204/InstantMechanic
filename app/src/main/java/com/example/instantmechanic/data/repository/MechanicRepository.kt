package com.example.instantmechanic.data.repository

import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.data.remote.MechanicApiService
import com.example.instantmechanic.domain.model.Mechanic

class MechanicRepository() {


     fun getMechanics(): List<Mechanic> {
        return DummyMechanicsData.mechanics

        // here remote api

//        api.getMechanics()


    }
}
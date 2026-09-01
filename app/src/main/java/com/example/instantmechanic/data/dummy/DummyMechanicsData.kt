package com.example.instantmechanic.data.dummy

import com.example.instantmechanic.domain.model.Mechanic

object DummyMechanicsData {

    val mechanics = listOf(
        Mechanic(
            id = 1,
            garageName = "Raj Auto Care",
            rating = 4.5,
            distance = "1.2 km",
            location = "Connaught Place",
            services = listOf(
                "Oil Change",
                "Brake Repair",
                "Engine Repair"
            ),
            isOpen = true,
            address = "12 Main Road, Connaught Place",
            workingHours = "9:00 AM - 8:00 PM",
            phoneNumber = "9876543210"
        ),

        Mechanic(
            id = 2,
            garageName = "Sharma Motors",
            rating = 4.2,
            distance = "2.5 km",
            location = "Karol Bagh",
            services = listOf(
                "Tyre Repair",
                "Battery Service",
                "General Service"
            ),
            isOpen = false,
            address = "45 Market Road, Karol Bagh",
            workingHours = "10:00 AM - 7:00 PM",
            phoneNumber = "9876543211"
        )
    )
}
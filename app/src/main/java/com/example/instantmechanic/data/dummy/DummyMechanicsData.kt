package com.example.instantmechanic.data.dummy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.OilBarrel
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.domain.model.MechanicService

object DummyMechanicsData {

    val mechanics = listOf(
        Mechanic(
            id = 1,
            garageName = "Raj Auto Care",
            rating = 4.5,
            distance = "1.2 km",
            location = "Connaught Place",
            services = listOf(
                MechanicService("Tyre Repair", Icons.Default.BuildCircle),
                MechanicService("Battery Service", Icons.Default.ElectricBolt),
                MechanicService("Oil Change", Icons.Default.OilBarrel),
                MechanicService("General Service", Icons.Default.Build)
            ),
            isOpen = true,
            address = "12 Main Road, Connaught Place",
            workingHours = "Mon-Fri: 8:00 AM - 6:00 PM\nSat: 9:00 AM - 3:00 PM   Sun: Closed",
            phoneNumber = "9876543210"
        ),

        Mechanic(
            id = 2,
            garageName = "Sharma Motors",
            rating = 4.2,
            distance = "2.5 km",
            location = "Karol Bagh",
            services = listOf(
                MechanicService("Tyre Repair", Icons.Default.BuildCircle),
                MechanicService("Battery Service", Icons.Default.ElectricBolt),
                MechanicService("Oil Change", Icons.Default.OilBarrel),
                MechanicService("General Service", Icons.Default.Build)
            ),
            isOpen = false,
            address = "45 Market Road, Karol Bagh",
            workingHours = "Mon-Fri: 8:00 AM - 6:00 PM\nSat: 9:00 AM - 3:00 PM   Sun: Closed",
            phoneNumber = "9876543211"
        )
    )
}
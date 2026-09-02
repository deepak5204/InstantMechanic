package com.example.instantmechanic.data.dummy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.OilBarrel
import androidx.compose.material.icons.filled.Speed
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.domain.model.MechanicService

object DummyMechanicsData {

    val mechanics = listOf(
        Mechanic(
            id = 1,
            garageName = "Raj Auto Care",
            rating = 4.5,
            ratingCount = 128,
            distance = "1.2 km",
            location = "Connaught Place",
            services = listOf(
                MechanicService("Tyre Repair", Icons.Default.BuildCircle),
                MechanicService("Battery Service", Icons.Default.ElectricBolt),
                MechanicService("Oil Change", Icons.Default.OilBarrel),
                MechanicService("General Service", Icons.Default.Build)
            ),
            isOpen = true,
            address = "12 Main Road, Connaught Place, New Delhi",
            workingHours = "Mon-Fri: 8:00 AM - 6:00 PM\nSat: 9:00 AM - 3:00 PM   Sun: Closed",
            phoneNumber = "+91 98765 43210"
        ),
        Mechanic(
            id = 2,
            garageName = "Sharma Motors",
            rating = 4.2,
            ratingCount = 85,
            distance = "2.5 km",
            location = "Karol Bagh",
            services = listOf(
                MechanicService("Tyre Repair", Icons.Default.BuildCircle),
                MechanicService("Battery Service", Icons.Default.ElectricBolt),
                MechanicService("Brake Inspection", Icons.Default.Speed)
            ),
            isOpen = false,
            address = "45 Market Road, Karol Bagh, New Delhi",
            workingHours = "Mon-Fri: 9:00 AM - 7:00 PM\nSat: 10:00 AM - 4:00 PM   Sun: Closed",
            phoneNumber = "+91 98765 43211"
        ),
        Mechanic(
            id = 3,
            garageName = "Express Car Doctors",
            rating = 4.8,
            ratingCount = 240,
            distance = "3.8 km",
            location = "Cyber City",
            services = listOf(
                MechanicService("AC Repair", Icons.Default.AcUnit),
                MechanicService("Oil Change", Icons.Default.OilBarrel),
                MechanicService("General Service", Icons.Default.Build),
                MechanicService("Battery Service", Icons.Default.ElectricBolt)
            ),
            isOpen = true,
            address = "Building 10, DLF Cyber City, Gurugram",
            workingHours = "Mon-Sun: 7:00 AM - 10:00 PM (Open Daily)",
            phoneNumber = "+91 98765 43212"
        ),
        Mechanic(
            id = 4,
            garageName = "Verma Wheel & Brake Care",
            rating = 4.0,
            ratingCount = 52,
            distance = "5.1 km",
            location = "Saket",
            services = listOf(
                MechanicService("Tyre Repair", Icons.Default.BuildCircle),
                MechanicService("Brake Inspection", Icons.Default.Speed)
            ),
            isOpen = true,
            address = "88 Press Enclave Marg, Saket, New Delhi",
            workingHours = "Mon-Sat: 8:30 AM - 6:30 PM\nSun: Closed",
            phoneNumber = "+91 98765 43213"
        ),
        Mechanic(
            id = 5,
            garageName = "Metro Auto Garage",
            rating = 4.6,
            ratingCount = 194,
            distance = "6.4 km",
            location = "Indiranagar",
            services = listOf(
                MechanicService("General Service", Icons.Default.Build),
                MechanicService("AC Repair", Icons.Default.AcUnit),
                MechanicService("Oil Change", Icons.Default.OilBarrel)
            ),
            isOpen = false,
            address = "100 Feet Road, Indiranagar, Bengaluru",
            workingHours = "Mon-Sat: 9:00 AM - 8:00 PM\nSun: 10:00 AM - 2:00 PM",
            phoneNumber = "+91 98765 43214"
        )
    )
}
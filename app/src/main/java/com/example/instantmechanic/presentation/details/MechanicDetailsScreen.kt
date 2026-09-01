package com.example.instantmechanic.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.domain.model.Mechanic

@Composable
fun MechanicDetailsScreen(
    mechanic: Mechanic,
    onRequestServiceClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = mechanic.garageName,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(text = "⭐ ${mechanic.rating}")

        Text(text = "Address: ${mechanic.address}")

        Text(
            text = "Services: ${mechanic.services.joinToString(", ")}"
        )

        Text(
            text = "Working Hours: ${mechanic.workingHours}"
        )

        Text(
            text = "Phone: ${mechanic.phoneNumber}"
        )

        Button(
            onClick = onRequestServiceClick
        ) {
            Text("Request Service")
        }
    }
}

val mechanicdata = DummyMechanicsData.mechanics[0]
@Preview(showBackground = true)
@Composable
private fun MechanicDetailsScreenPreview() {
    MechanicDetailsScreen(
        mechanic = mechanicdata,
        onRequestServiceClick = {

        }
    )
}
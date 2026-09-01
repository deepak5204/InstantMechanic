package com.example.instantmechanic.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.domain.model.Mechanic

@Composable
fun HomeScreen() {

    val mechanics = DummyMechanicsData.mechanics

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(mechanics) { mechanic ->
            MechanicCard(mechanic = mechanic)
        }
    }
}

@Composable
fun MechanicCard(mechanic: Mechanic) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = mechanic.garageName,
                style = MaterialTheme.typography.titleLarge
            )

            Text(text = "⭐ ${mechanic.rating}")

            Text(text = mechanic.distance)

            Text(text = mechanic.location)

            Text(
                text = if (mechanic.isOpen) {
                    "Open"
                } else {
                    "Closed"
                }
            )

            Text(
                text = "Services: ${mechanic.services.joinToString(", ")}"
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()

}
package com.example.instantmechanic.presentation.home


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instantmechanic.data.remote.RetrofitClient
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.presentation.viewModel.MechanicViewModel

@Composable
fun HomeScreen(
    onMechanicClick : (Mechanic) -> Unit,
    viewModel: MechanicViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {

        MechanicUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }

        is MechanicUiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                items(state.mechanics) { mechanic ->
                    MechanicCard(
                        mechanic = mechanic,
                        onClick = {
                            onMechanicClick(mechanic)
                        }
                    )
                }
            }
        }

        is MechanicUiState.Error -> {
            Text(state.message)
        }
    }
}

@Composable
fun MechanicCard(mechanic: Mechanic, onClick: () -> Unit) {

    Card(
        onClick = { onClick()},
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
    HomeScreen(
        onMechanicClick = {}
    )

}
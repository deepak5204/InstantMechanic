package com.example.instantmechanic.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.presentation.viewModel.MechanicViewModel

@Composable
fun HomeScreen(
    onMechanicClick: (Mechanic) -> Unit, viewModel: MechanicViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {

        MechanicUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MechanicUiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.mechanics) { mechanic ->
                    MechanicCard(mechanic = mechanic, onCardClick = {
                        onMechanicClick(mechanic)
                    }, onViewDetailsClick = {

                    })
                }
            }
        }

        is MechanicUiState.Error -> {
            Text(state.message)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MechanicCard(
    mechanic: Mechanic,
    onCardClick: () -> Unit,
    onViewDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFAAACAF), RoundedCornerShape(12.dp))
            .clickable { onCardClick() },
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Row 1: Mechanic/Garage Name & Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mechanic.garageName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold, fontSize = 16.sp
                    ),
                    color = Color(0xFF111827),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Star",
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${mechanic.rating}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium, fontSize = 14.sp
                        ),
                        color = Color(0xFF111827)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Row 2: Proximity & Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${mechanic.distance} • ${mechanic.location}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                    color = Color(0xFF4B5563)
                )

                StatusBadge(isOpen = mechanic.isOpen)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Row 3: Service Tags
            val maxVisibleTags = 3
            val visibleServices = mechanic.services.take(maxVisibleTags)
            val remainingCount = mechanic.services.size - maxVisibleTags

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                visibleServices.forEach { service ->
                    ServiceTag(text = service)
                }
                if (remainingCount > 0) {
                    ServiceTag(text = "+$remainingCount")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row 4: Single Primary CTA
            Button(
                onClick = onViewDetailsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0F172A), contentColor = Color.White
                )
            ) {
                Text(
                    text = "View Details", style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(isOpen: Boolean) {
    val backgroundColor = if (isOpen) Color(0xFFD1FAE5) else Color(0xFFFEE2E2)
    val textColor = if (isOpen) Color(0xFF065F46) else Color(0xFF991B1B)
    val label = if (isOpen) "OPEN NOW" else "CLOSED"

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = label, color = textColor, style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold, fontSize = 11.sp
            )
        )
    }
}

@Composable
private fun ServiceTag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFF3F4F6))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF374151),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium, fontSize = 12.sp
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onMechanicClick = {})

}
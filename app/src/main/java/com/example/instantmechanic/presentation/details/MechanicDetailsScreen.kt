package com.example.instantmechanic.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.domain.model.MechanicService
import com.example.instantmechanic.presentation.components.ServiceCard
import com.example.instantmechanic.ui.theme.PrimaryButtonBlue
import com.example.instantmechanic.ui.theme.StarYellow
import com.example.instantmechanic.ui.theme.StatusClosedBg
import com.example.instantmechanic.ui.theme.StatusOpenBg
import com.example.instantmechanic.ui.theme.StatusOpenGreen

// Clean screen file holding only Screen-level compositions
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MechanicDetailsScreen(
    mechanic: Mechanic, onRequestService: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 8.dp, color = MaterialTheme.colorScheme.surface
            ) {
                Button(
                    onClick = onRequestService,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryButtonBlue)
                ) {
                    Text(
                        text = "Request Service", fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = mechanic.garageName,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = StarYellow,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "${mechanic.rating}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "(${mechanic.ratingCount ?: 0} reviews)",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Location & Contact
            LocationAndContactSection(
                address = mechanic.address,
                phoneNumber = mechanic.phoneNumber
            )

            HorizontalDivider()

            ServicesGridSection(services = mechanic.services)

            HorizontalDivider()

            // Working Hours Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Working Hours",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (mechanic.isOpen) "Open Now" else "Closed",
                        color = if (mechanic.isOpen) StatusOpenGreen else Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (mechanic.isOpen) StatusOpenBg else StatusClosedBg)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = mechanic.workingHours,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    lineHeight = 25.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ServicesGridSection(
    services: List<MechanicService>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Services Offered",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 2 // Sets 2 cards per row for standard phone screens
        ) {
            services.forEach { service ->
                ServiceCard(
                    service = service,
                    modifier = Modifier.weight(1f) // Distributes equal width per item
                )
            }
        }
    }
}


@Composable
fun LocationAndContactSection(
    address: String,
    phoneNumber: String
) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // --- Location Row ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    // Opens Google Maps or default map viewer
                    val mapUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
                    context.startActivity(mapIntent)
                }
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Styled Icon Container
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "Address",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = address,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // --- Call Row ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    // Opens device phone dialer with number pre-filled
                    val dialUri = Uri.parse("tel:$phoneNumber")
                    val dialIntent = Intent(Intent.ACTION_DIAL, dialUri)
                    context.startActivity(dialIntent)
                }
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Styled Icon Container
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = phoneNumber,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

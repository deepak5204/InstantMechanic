import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instantmechanic.domain.model.Mechanic
import com.example.instantmechanic.domain.model.MechanicService


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
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    )
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
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "${mechanic.rating}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "(${mechanic.ratingCount ?: 30} reviews)",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Location & Contact
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = mechanic.address, style = MaterialTheme.typography.bodyLarge
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = mechanic.phoneNumber, style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            HorizontalDivider()

            ServicesGridSection(
                services = mechanic.services
            )

            HorizontalDivider()
            // Working Hours Section (Single multi-line String)
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
                        color = if (mechanic.isOpen) Color(0xFF2E7D32) else Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (mechanic.isOpen) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Displays the exact formatted string layout from your image
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

        // FlowRow auto-calculates row wrapping while keeping uniform spacing
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


// Production Color Definitions for this design
val CustomWrenchIconColor = Color(0xFF5A6273) // Grey/blue wrench
val CustomCardBorderColor = Color(0xFFECEEF2) // Soft border
val CustomCardBackdropColor = Color(0xFFFAFBFE) // Light backdrop card

@Composable
fun ServiceCard(
    service: MechanicService, modifier: Modifier = Modifier
) {
    // Surface provides the background, shape, and click handling
    Surface(
        modifier = modifier
            .fillMaxWidth() // Fill width of its container (crucial for grid)
            .border(width = 1.dp, color = CustomCardBorderColor, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = CustomCardBackdropColor, // Matches the backdrop
        tonalElevation = 1.dp // Very slight elevation shadow
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp, vertical = 20.dp
            ), // Increased padding for comfort
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon Container Box with distinct tinted background (soft gray)
            Box(
                modifier = Modifier
                    .size(64.dp) // Large icon box
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE9EBED)), // Distinct grey-ish icon box color
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = service.icon,
                    contentDescription = service.name,
                    tint = CustomWrenchIconColor, // Matches the wrench color in the design
                    modifier = Modifier.size(28.dp) // Large icon size
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = service.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontSize = 15.sp, // Reduced size to allow longer titles to breathe
                maxLines = 2
            )
        }
    }
}
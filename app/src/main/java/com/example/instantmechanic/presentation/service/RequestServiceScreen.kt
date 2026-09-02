package com.example.instantmechanic.presentation.service

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.domain.model.Mechanic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestServiceScreen(
    mechanic: Mechanic,
    onBackClick: () -> Unit = {},
    onSubmitClick: () -> Unit
) {
    val context = LocalContext.current

    var customerName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var vehicleNumber by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf("") }
    var isServiceMenuExpanded by remember { mutableStateOf(false) }
    var problemDescription by remember { mutableStateOf("") }

    // Field-specific validation states for direct visual feedback
    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var vehicleError by remember { mutableStateOf(false) }
    var serviceError by remember { mutableStateOf(false) }
    var isSubmitted by remember { mutableStateOf(false) }

    // Toast triggered via LaunchedEffect on successful submission
    LaunchedEffect(isSubmitted) {
        if (isSubmitted) {
            Toast.makeText(
                context,
                "Service request submitted successfully!",
                Toast.LENGTH_LONG
            ).show()
            onSubmitClick()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Request Service",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Navigate back"
//                        )
//                    }
//                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Button(
                    onClick = {
                        nameError = customerName.isBlank()
                        phoneError = phoneNumber.isBlank()
                        vehicleError = vehicleNumber.isBlank()
                        serviceError = selectedService.isBlank()
                        if (!nameError && !phoneError && !vehicleError && !serviceError) {
                            isSubmitted = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Request",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mechanic Details Header Summary Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Requesting service from:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = mechanic.garageName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Customer Name Field
            OutlinedTextField(
                value = customerName,
                onValueChange = {
                    customerName = it
                    if (nameError) nameError = false
                },
                label = { Text("Customer Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                isError = nameError,
                supportingText = { if (nameError) Text("Please enter your name") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Phone Number Field
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                    if (phoneError) phoneError = false
                },
                label = { Text("Phone Number") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                isError = phoneError,
                supportingText = { if (phoneError) Text("Please enter your phone number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Vehicle Number Field
            OutlinedTextField(
                value = vehicleNumber,
                onValueChange = {
                    vehicleNumber = it
                    if (vehicleError) vehicleError = false
                },
                label = { Text("Vehicle Registration Number") },
                leadingIcon = { Icon(Icons.Default.DirectionsCar, contentDescription = null) },
                isError = vehicleError,
                supportingText = { if (vehicleError) Text("Please enter vehicle number") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            // Service Selection Dropdown
            ExposedDropdownMenuBox(
                expanded = isServiceMenuExpanded,
                onExpandedChange = { isServiceMenuExpanded = !isServiceMenuExpanded }
            ) {
                OutlinedTextField(
                    value = selectedService,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Service") },
                    leadingIcon = { Icon(Icons.Default.Build, contentDescription = null) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isServiceMenuExpanded)
                    },
                    isError = serviceError,
                    supportingText = { if (serviceError) Text("Please select a service") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = isServiceMenuExpanded,
                    onDismissRequest = { isServiceMenuExpanded = false }
                ) {
                    mechanic.services.forEach { service ->
                        DropdownMenuItem(
                            text = { Text(service.name) },
                            onClick = {
                                selectedService = service.name
                                serviceError = false
                                isServiceMenuExpanded = false
                            }
                        )
                    }
                }
            }

            // Problem Description Field
            OutlinedTextField(
                value = problemDescription,
                onValueChange = {
                    problemDescription = it
                },
                label = { Text("Problem Description") },
                placeholder = { Text("write issue....") },
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RequestServiceScreenPreview() {
    MaterialTheme {
        RequestServiceScreen(
            mechanic = DummyMechanicsData.mechanics[0],
            onSubmitClick = {}
        )
    }
}
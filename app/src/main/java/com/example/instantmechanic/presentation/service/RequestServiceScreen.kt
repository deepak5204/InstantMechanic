package com.example.instantmechanic.presentation.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instantmechanic.data.dummy.DummyMechanicsData
import com.example.instantmechanic.domain.model.Mechanic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestServiceScreen(
    mechanic: Mechanic,
    onSubmitClick: () -> Unit
) {

    var customerName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var vehicleNumber by remember {
        mutableStateOf("")
    }


    var selectedService by remember {
        mutableStateOf("")
    }

    var isServiceMenuExpanded by remember {
        mutableStateOf(false)
    }

    var problemDescription by remember {
        mutableStateOf("")
    }

    var isSubmitted by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(text = "Request Service")

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = {
                Text("Customer Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = {
                Text("Phone Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vehicleNumber,
            onValueChange = { vehicleNumber = it },
            label = {
                Text("Vehicle Number")
            },
            modifier = Modifier.fillMaxWidth()
        )


        ExposedDropdownMenuBox(
            expanded = isServiceMenuExpanded,
            onExpandedChange = {
                isServiceMenuExpanded = !isServiceMenuExpanded
            }
        ) {

            OutlinedTextField(
                value = selectedService,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Select Service")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isServiceMenuExpanded
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isServiceMenuExpanded,
                onDismissRequest = {
                    isServiceMenuExpanded = false
                }
            ) {

                mechanic.services.forEach { service ->

                    DropdownMenuItem(
                        text = {
                            Text(service)
                        },
                        onClick = {
                            selectedService = service
                            isServiceMenuExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = problemDescription,
            onValueChange = { problemDescription = it },
            label = {
                Text("Problem Description")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (customerName.isBlank()) {
                    errorMessage = "Please enter your name"
                } else if (phoneNumber.isBlank()) {
                    errorMessage = "Please enter your phone number"
                } else if (vehicleNumber.isBlank()) {
                    errorMessage = "Please enter your vehicle number"
                } else if (selectedService.isBlank()) {
                    errorMessage = "Please select a service"
                } else if (problemDescription.isBlank()) {
                    errorMessage = "Please describe the problem"
                } else {
                    errorMessage = ""
                    isSubmitted = true
                    onSubmitClick()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Request")
        }


        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage
            )
        }

        // here need to show toast
        if (isSubmitted) {
            Text(
                text = "Service request submitted successfully!"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RequestServiceScreenPreview() {
    RequestServiceScreen(
        mechanic = DummyMechanicsData.mechanics[0],
        onSubmitClick = {

        }
    )

}
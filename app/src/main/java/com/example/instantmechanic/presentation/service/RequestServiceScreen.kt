package com.example.instantmechanic.presentation.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RequestServiceScreen() {

    var customerName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var vehicleNumber by remember {
        mutableStateOf("")
    }

    var problemDescription by remember {
        mutableStateOf("")
    }

    var isSubmitted by remember {
        mutableStateOf(false)
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
                isSubmitted = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Request")
        }

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
    RequestServiceScreen()

}
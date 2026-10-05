package com.sippy.progresspicsbodytracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sippy.progresspicsbodytracker.ui.components.ErrorBanner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProgressEntryScreen(
    onSave: (weight: Double?, waist: Double?, chest: Double?, hip: Double?, arm: Double?, thigh: Double?, notes: String?) -> Unit,
    onCancel: () -> Unit,
    onCameraClick: () -> Unit = {},
    capturedPhotoPath: String? = null,
    errorMessage: String? = null
) {
    var weight by remember { mutableStateOf("") }
    var waist by remember { mutableStateOf("") }
    var chest by remember { mutableStateOf("") }
    var hip by remember { mutableStateOf("") }
    var arm by remember { mutableStateOf("") }
    var thigh by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("New progress entry") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (errorMessage != null) {
                ErrorBanner(message = errorMessage)
            }

            Text(
                text = "Body photo & metrics",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            if (capturedPhotoPath != null) {
                Text(
                    text = "✓ Photo captured: ${capturedPhotoPath.substringAfterLast("/")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            } else {
                Button(
                    onClick = onCameraClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null)
                    Text("Capture photo")
                }
            }

            Text(
                text = "Measurements",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (kg)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = chest,
                onValueChange = { chest = it },
                label = { Text("Chest (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = waist,
                onValueChange = { waist = it },
                label = { Text("Waist (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = hip,
                onValueChange = { hip = it },
                label = { Text("Hip (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = arm,
                onValueChange = { arm = it },
                label = { Text("Arm (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = thigh,
                onValueChange = { thigh = it },
                label = { Text("Thigh (cm)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        onSave(
                            weight.toDoubleOrNull(),
                            waist.toDoubleOrNull(),
                            chest.toDoubleOrNull(),
                            hip.toDoubleOrNull(),
                            arm.toDoubleOrNull(),
                            thigh.toDoubleOrNull(),
                            notes.takeIf { it.isNotEmpty() }
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Save, contentDescription = null)
                    Text("Save")
                }
            }
        }
    }
}

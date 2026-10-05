package com.sippy.progresspicsbodytracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendsScreen(
    photos: List<ProgressPhoto>,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress trends") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val weightValues = photos.mapNotNull { it.weightKg }.sorted()
            val waistValues = photos.mapNotNull { it.waistCm }.sorted()

            if (weightValues.isNotEmpty()) {
                TrendCard(
                    title = "Weight Trend",
                    currentValue = weightValues.last(),
                    startValue = weightValues.first(),
                    unit = "kg"
                )
            }

            if (waistValues.isNotEmpty()) {
                TrendCard(
                    title = "Waist Trend",
                    currentValue = waistValues.last(),
                    startValue = waistValues.first(),
                    unit = "cm"
                )
            }

            if (photos.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Summary", style = MaterialTheme.typography.titleMedium)
                        Text("Total entries: ${photos.size}")
                        Text(
                            "Date range: ${photos.last().dateTaken} to ${photos.first().dateTaken}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrendCard(
    title: String,
    currentValue: Double,
    startValue: Double,
    unit: String
) {
    val delta = currentValue - startValue
    val deltaPercent = if (startValue != 0.0) {
        ((delta / startValue) * 100).toInt()
    } else {
        0
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text("Current: $currentValue $unit", style = MaterialTheme.typography.bodyMedium)
            Text("Started at: $startValue $unit", style = MaterialTheme.typography.bodySmall)
            Text(
                "Change: ${if (delta > 0) "+" else ""}$delta $unit ($deltaPercent%)",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

package com.sippy.progresspicsbodytracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate

data class ProgressEntry(
    val id: Int,
    val date: LocalDate,
    val weightKg: Double,
    val waistCm: Double,
    val notes: String,
    val photoLabel: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDashboardScreen(
    onAddEntryClick: () -> Unit
) {
    val entries = listOf(
        ProgressEntry(
            id = 1,
            date = LocalDate.of(2026, 10, 1),
            weightKg = 82.5,
            waistCm = 89.0,
            notes = "Feeling stronger and more consistent in training.",
            photoLabel = "Front view"
        ),
        ProgressEntry(
            id = 2,
            date = LocalDate.of(2026, 9, 15),
            weightKg = 84.2,
            waistCm = 91.5,
            notes = "Recovery week - lighter cardio volume.",
            photoLabel = "Side view"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress Tracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddEntryClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add entry")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            SummaryRow()

            Text(
                text = "Recent progress",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(entries) { entry ->
                    ProgressEntryCard(entry = entry)
                }
            }
        }
    }
}

@Composable
private fun SummaryRow() {
    Surface(
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(icon = Icons.Default.PhotoCamera, label = "Photos", value = "12")
            StatItem(icon = Icons.Default.MonitorWeight, label = "Weight", value = "82.5 kg")
            StatItem(icon = Icons.Default.CompareArrows, label = "Change", value = "-1.7 kg")
        }
    }
}

@Composable
private fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, contentDescription = label)
        Text(text = value, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun ProgressEntryCard(entry: ProgressEntry) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.date.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Default.CalendarToday, contentDescription = "Date")
            }

            Text(text = "${entry.weightKg} kg")
            Text(text = "Waist: ${entry.waistCm} cm")
            Text(text = entry.photoLabel)
            Text(text = entry.notes)
        }
    }
}

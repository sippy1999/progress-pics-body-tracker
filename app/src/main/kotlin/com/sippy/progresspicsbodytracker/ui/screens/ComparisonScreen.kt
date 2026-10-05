package com.sippy.progresspicsbodytracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparisonScreen(
    latestPhoto: ProgressPhoto?,
    previousPhoto: ProgressPhoto?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress comparison") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (previousPhoto == null || latestPhoto == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Not enough photos to compare yet.")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ComparisonCard(
                        title = "First Progress",
                        date = previousPhoto.dateTaken.toString(),
                        photoUri = previousPhoto.photoUri
                    )
                    ComparisonCard(
                        title = "Latest",
                        date = latestPhoto.dateTaken.toString(),
                        photoUri = latestPhoto.photoUri
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            "Metrics change",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        if (latestPhoto.weightKg != null && previousPhoto.weightKg != null) {
                            val delta = latestPhoto.weightKg!! - previousPhoto.weightKg!!
                            Text(
                                "Weight: ${String.format("%.1f", latestPhoto.weightKg)} kg (${if (delta > 0) "+" else ""}${String.format("%.1f", delta)} kg)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        if (latestPhoto.waistCm != null && previousPhoto.waistCm != null) {
                            val delta = latestPhoto.waistCm!! - previousPhoto.waistCm!!
                            Text(
                                "Waist: ${String.format("%.1f", latestPhoto.waistCm)} cm (${if (delta > 0) "+" else ""}${String.format("%.1f", delta)} cm)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        if (latestPhoto.chestCm != null && previousPhoto.chestCm != null) {
                            val delta = latestPhoto.chestCm!! - previousPhoto.chestCm!!
                            Text(
                                "Chest: ${String.format("%.1f", latestPhoto.chestCm)} cm (${if (delta > 0) "+" else ""}${String.format("%.1f", delta)} cm)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComparisonCard(
    title: String,
    date: String,
    photoUri: String
) {
    Card(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(date, style = MaterialTheme.typography.bodySmall)

            val file = File(photoUri)
            if (file.exists()) {
                Image(
                    painter = rememberAsyncImagePainter(model = file),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            } else {
                Text(
                    "Image not found",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

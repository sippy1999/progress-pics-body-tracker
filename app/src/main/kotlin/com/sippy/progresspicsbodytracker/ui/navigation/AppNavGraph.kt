package com.sippy.progresspicsbodytracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sippy.progresspicsbodytracker.ui.camera.CameraScreen
import com.sippy.progresspicsbodytracker.ui.screens.AddProgressEntryScreen
import com.sippy.progresspicsbodytracker.ui.screens.ComparisonScreen
import com.sippy.progresspicsbodytracker.ui.screens.GalleryPickerScreen
import com.sippy.progresspicsbodytracker.ui.screens.ProgressDashboardScreen
import com.sippy.progresspicsbodytracker.ui.screens.TrendsScreen
import com.sippy.progresspicsbodytracker.ui.viewmodel.ProgressViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: ProgressViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var capturedPhotoPath by remember { mutableStateOf<String?>(null) }

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            ProgressDashboardScreen(
                photos = uiState.photos,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onAddEntryClick = {
                    capturedPhotoPath = null
                    navController.navigate("add-entry")
                },
                onDeletePhoto = { photo ->
                    viewModel.deletePhoto(photo)
                },
                onOpenComparison = {
                    navController.navigate("comparison")
                },
                onOpenTrends = {
                    navController.navigate("trends")
                },
                latestWeight = uiState.latestPhoto?.weightKg,
                weightDelta = viewModel.getWeightDelta()
            )
        }

        composable("add-entry") {
            AddProgressEntryScreen(
                onSave = { weight, waist, chest, hip, arm, thigh, notes ->
                    if (capturedPhotoPath == null) {
                        return@AddProgressEntryScreen
                    }

                    viewModel.saveProgressPhoto(
                        photoPath = capturedPhotoPath!!,
                        weightKg = weight,
                        chestCm = chest,
                        waistCm = waist,
                        hipCm = hip,
                        armCm = arm,
                        thighCm = thigh,
                        notes = notes
                    )
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                },
                onCameraClick = {
                    navController.navigate("camera")
                },
                onGalleryClick = {
                    navController.navigate("gallery")
                },
                capturedPhotoPath = capturedPhotoPath,
                errorMessage = uiState.errorMessage
            )
        }

        composable("camera") {
            CameraScreen(
                onPhotoCaptured = { path ->
                    capturedPhotoPath = path
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable("gallery") {
            GalleryPickerScreen(
                onImagePicked = { uri ->
                    capturedPhotoPath = uri
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable("comparison") {
            ComparisonScreen(
                latestPhoto = uiState.latestPhoto,
                previousPhoto = uiState.previousPhoto,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("trends") {
            TrendsScreen(
                photos = uiState.photos,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

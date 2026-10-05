package com.sippy.progresspicsbodytracker.ui.navigation

import androidx.compose.runtime.Composable
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
import com.sippy.progresspicsbodytracker.ui.screens.ProgressDashboardScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    var capturedPhotoPath by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            ProgressDashboardScreen(
                onAddEntryClick = {
                    navController.navigate("add-entry")
                }
            )
        }

        composable("add-entry") {
            AddProgressEntryScreen(
                onSave = { weight, waist, chest, hip, arm, thigh, notes ->
                    if (capturedPhotoPath == null) {
                        errorMessage = "Please capture a photo before saving."
                        return@AddProgressEntryScreen
                    }

                    errorMessage = null
                    navController.popBackStack()
                },
                onCancel = {
                    errorMessage = null
                    navController.popBackStack()
                },
                onCameraClick = {
                    navController.navigate("camera")
                },
                capturedPhotoPath = capturedPhotoPath,
                errorMessage = errorMessage
            )
        }

        composable("camera") {
            CameraScreen(
                onPhotoCaptured = { path ->
                    capturedPhotoPath = path
                    errorMessage = null
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}

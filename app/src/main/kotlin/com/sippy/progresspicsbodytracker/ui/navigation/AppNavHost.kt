package com.sippy.progresspicsbodytracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sippy.progresspicsbodytracker.ui.screens.AddProgressEntryScreen
import com.sippy.progresspicsbodytracker.ui.screens.ProgressDashboardScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
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
                onSave = {
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}

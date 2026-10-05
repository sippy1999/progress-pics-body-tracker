package com.sippy.progresspicsbodytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.sippy.progresspicsbodytracker.di.AppModule
import com.sippy.progresspicsbodytracker.ui.navigation.AppNavGraph
import com.sippy.progresspicsbodytracker.ui.theme.ProgressPicsBodyTrackerTheme
import com.sippy.progresspicsbodytracker.ui.viewmodel.ProgressViewModel
import com.sippy.progresspicsbodytracker.ui.viewmodel.ProgressViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: ProgressViewModel by viewModels {
        ProgressViewModelFactory(
            AppModule.provideProgressPhotoRepository(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgressPicsBodyTrackerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}

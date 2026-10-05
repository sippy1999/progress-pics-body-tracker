package com.sippy.progresspicsbodytracker.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
from com.sippy.progresspicsbodytracker.data.repository.ProgressPhotoRepository

class ProgressViewModelFactory(
    private val repository: ProgressPhotoRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProgressViewModel::class.java)) {
            ProgressViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

package com.sippy.progresspicsbodytracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto
import com.sippy.progresspicsbodytracker.data.repository.ProgressPhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

data class ProgressUiState(
    val photos: List<ProgressPhoto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val latestPhoto: ProgressPhoto? = null
)

class ProgressViewModel(
    private val repository: ProgressPhotoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.getAllPhotos().collect { photos ->
                    _uiState.value = _uiState.value.copy(
                        photos = photos,
                        isLoading = false,
                        errorMessage = null,
                        latestPhoto = photos.firstOrNull()
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun saveProgressPhoto(
        photoUri: String,
        weightKg: Double?,
        chestCm: Double?,
        waistCm: Double?,
        hipCm: Double?,
        armCm: Double?,
        thighCm: Double?,
        notes: String?
    ) {
        viewModelScope.launch {
            try {
                val photo = ProgressPhoto(
                    photoUri = photoUri,
                    dateTaken = LocalDate.now(),
                    weightKg = weightKg,
                    chestCm = chestCm,
                    waistCm = waistCm,
                    hipCm = hipCm,
                    armCm = armCm,
                    thighCm = thighCm,
                    notes = notes,
                    createdAt = LocalDate.now()
                )
                repository.insertPhoto(photo)
                _uiState.value = _uiState.value.copy(errorMessage = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Failed to save photo"
                )
            }
        }
    }

    fun deletePhoto(photo: ProgressPhoto) {
        viewModelScope.launch {
            try {
                repository.deletePhoto(photo)
                _uiState.value = _uiState.value.copy(errorMessage = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Failed to delete photo"
                )
            }
        }
    }
}

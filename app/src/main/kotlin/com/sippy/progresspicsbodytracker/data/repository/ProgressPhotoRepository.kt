package com.sippy.progresspicsbodytracker.data.repository

import com.sippy.progresspicsbodytracker.data.local.dao.ProgressPhotoDao
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto
import kotlinx.coroutines.flow.Flow

class ProgressPhotoRepository(
    private val progressPhotoDao: ProgressPhotoDao
) {
    fun getAllPhotos(): Flow<List<ProgressPhoto>> = progressPhotoDao.getAllPhotos()

    suspend fun insertPhoto(photo: ProgressPhoto): Long = progressPhotoDao.insertPhoto(photo)

    suspend fun updatePhoto(photo: ProgressPhoto) = progressPhotoDao.updatePhoto(photo)

    suspend fun deletePhoto(photo: ProgressPhoto) = progressPhotoDao.deletePhoto(photo)

    suspend fun getLatestPhoto(): ProgressPhoto? = progressPhotoDao.getLatestPhoto()
}

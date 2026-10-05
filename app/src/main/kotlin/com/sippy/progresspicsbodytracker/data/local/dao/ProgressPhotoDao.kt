package com.sippy.progresspicsbodytracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressPhotoDao {
    @Insert
    suspend fun insertPhoto(photo: ProgressPhoto): Long

    @Update
    suspend fun updatePhoto(photo: ProgressPhoto)

    @Delete
    suspend fun deletePhoto(photo: ProgressPhoto)

    @Query("SELECT * FROM progress_photos ORDER BY date_taken DESC")
    fun getAllPhotos(): Flow<List<ProgressPhoto>>

    @Query("SELECT * FROM progress_photos WHERE id = :id")
    suspend fun getPhotoById(id: Int): ProgressPhoto?

    @Query("SELECT * FROM progress_photos ORDER BY date_taken DESC LIMIT 1")
    suspend fun getLatestPhoto(): ProgressPhoto?

    @Query("SELECT * FROM progress_photos WHERE date_taken BETWEEN :startDate AND :endDate ORDER BY date_taken DESC")
    fun getPhotosInDateRange(startDate: String, endDate: String): Flow<List<ProgressPhoto>>
}

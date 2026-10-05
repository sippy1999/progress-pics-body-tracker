package com.sippy.progresspicsbodytracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sippy.progresspicsbodytracker.data.local.dao.ProgressPhotoDao
import com.sippy.progresspicsbodytracker.data.local.entity.ProgressPhoto
import com.sippy.progresspicsbodytracker.data.local.converters.DateConverters

@Database(
    entities = [ProgressPhoto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class ProgressDatabase : RoomDatabase() {
    abstract fun progressPhotoDao(): ProgressPhotoDao

    companion object {
        const val DATABASE_NAME = "progress_tracker.db"
    }
}

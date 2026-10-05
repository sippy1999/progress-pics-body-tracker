package com.sippy.progresspicsbodytracker.di

import android.content.Context
import androidx.room.Room
import com.sippy.progresspicsbodytracker.data.local.ProgressDatabase
import com.sippy.progresspicsbodytracker.data.repository.ProgressPhotoRepository

object AppModule {
    private var database: ProgressDatabase? = null

    fun provideDatabase(context: Context): ProgressDatabase {
        return database ?: Room.databaseBuilder(
            context,
            ProgressDatabase::class.java,
            ProgressDatabase.DATABASE_NAME
        ).build().also { database = it }
    }

    fun provideProgressPhotoRepository(context: Context): ProgressPhotoRepository {
        val db = provideDatabase(context)
        return ProgressPhotoRepository(db.progressPhotoDao())
    }
}

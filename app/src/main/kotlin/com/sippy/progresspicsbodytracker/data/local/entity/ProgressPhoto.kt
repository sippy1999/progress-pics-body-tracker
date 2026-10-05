package com.sippy.progresspicsbodytracker.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "progress_photos")
data class ProgressPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "photo_uri")
    val photoUri: String,
    @ColumnInfo(name = "date_taken")
    val dateTaken: LocalDate,
    @ColumnInfo(name = "weight_kg")
    val weightKg: Double? = null,
    @ColumnInfo(name = "chest_cm")
    val chestCm: Double? = null,
    @ColumnInfo(name = "waist_cm")
    val waistCm: Double? = null,
    @ColumnInfo(name = "hip_cm")
    val hipCm: Double? = null,
    @ColumnInfo(name = "arm_cm")
    val armCm: Double? = null,
    @ColumnInfo(name = "thigh_cm")
    val thighCm: Double? = null,
    @ColumnInfo(name = "notes")
    val notes: String? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate
)

package com.mhd_07.bloodsugar.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(
    tableName = "pressure_measures",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = PressureMeasureEntity::class,
            parentColumns = ["id"],
            childColumns = ["secondMeasureId"],
            onDelete = ForeignKey.SET_NULL
        )],
    indices = [Index("userId"), Index("secondMeasureId")]
)
data class PressureMeasureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val date: OffsetDateTime,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int?,
    val notes: String,
    val tags: List<String>,
    val secondMeasureId: Int?
)

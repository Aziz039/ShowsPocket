package com.example.abdulaziz.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val showId: Int,
    val showName: String,
    val showSummary: String,
    val showLanguage: String,
    val showImageURL: String
)
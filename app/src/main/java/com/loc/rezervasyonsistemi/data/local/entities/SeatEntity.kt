package com.loc.rezervasyonsistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seats")
data class SeatEntity(
    @PrimaryKey val id: String,
    val sessionId: String,
    val row: Int,
    val column: Int,
    val status: String, // "AVAILABLE", "LOCKED", "SOLD"
    val lockedByUserId: String? = null
)
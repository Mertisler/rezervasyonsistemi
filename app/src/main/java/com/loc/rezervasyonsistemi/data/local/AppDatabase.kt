package com.proje.rezervasyonsistemi.data.local

import androidx.room.RoomDatabase
// @Database(entities = [SeatEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // abstract fun seatDao(): SeatDao
}
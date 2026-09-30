package com.proje.rezervasyonsistemi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loc.rezervasyonsistemi.data.local.SeatDao
import com.loc.rezervasyonsistemi.data.local.entities.SeatEntity

@Database(entities = [SeatEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
     abstract fun seatDao(): SeatDao
}
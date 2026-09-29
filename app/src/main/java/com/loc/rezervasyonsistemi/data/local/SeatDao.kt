package com.loc.rezervasyonsistemi.data.local


import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.loc.rezervasyonsistemi.data.local.entities.SeatEntity

@Dao
interface SeatDao {
    // Flow sayesinde veritabanındaki değişiklikler anında arayüze yansır
    @Query("SELECT * FROM seats WHERE sessionId = :sessionId")
    fun getSeatsForSession(sessionId: String): Flow<List<SeatEntity>>

    // PESSIMISTIC LOCKING MANTIĞI:
    // Yalnızca koltuk 'AVAILABLE' (Boş) durumdaysa günceller.
    // Eğer aynı anda başka biri alıp statüyü 'LOCKED' yaptıysa, bu sorgu 0 satır günceller (başarısız olur).
    @Query("UPDATE seats SET status = 'LOCKED', lockedByUserId = :userId WHERE id = :seatId AND status = 'AVAILABLE'")
    suspend fun attemptLockSeat(seatId: String, userId: String): Int

    @Query("SELECT * FROM seats WHERE id = :seatId")
    suspend fun getSeatById(seatId: String): SeatEntity?
}
package com.loc.rezervasyonsistemi.domain.repository

import com.loc.rezervasyonsistemi.domain.models.Seat
import kotlinx.coroutines.flow.Flow

interface ISeatRepository {
    // Seansın güncel koltuk haritasını anlık olarak (Flow ile) dinler
    fun getSeatGridStatus(sessionId: String): Flow<List<Seat>>

    // Pessimistic Locking mantığını tetikleyen fonksiyon
    // Başarılıysa kilitlenen koltuğu, başarısızsa (başka biri aldıysa) hata döndürür
    suspend fun lockSeat(seatId: String, userId: String): Result<Seat>

    // Ödeme iptali veya zaman aşımında kilidi kaldırır
    suspend fun unlockSeat(seatId: String): Result<Boolean>
}
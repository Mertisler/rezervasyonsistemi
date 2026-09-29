package com.loc.rezervasyonsistemi.data.repository

import com.loc.rezervasyonsistemi.data.local.SeatDao
import com.loc.rezervasyonsistemi.data.mapper.toDomain
import com.loc.rezervasyonsistemi.domain.models.Seat
import com.loc.rezervasyonsistemi.domain.repository.ISeatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeatRepositoryImpl @Inject constructor(
    private val seatDao: SeatDao
) : ISeatRepository {

    override fun getSeatGridStatus(sessionId: String): Flow<List<Seat>> {
        // Veritabanından gelen Entity listesini anlık olarak Domain listesine dönüştürür
        return seatDao.getSeatsForSession(sessionId).map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    override suspend fun lockSeat(seatId: String, userId: String): Result<Seat> {
        return try {
            // DAO üzerinden kilit atmayı dener
            val updatedRows = seatDao.attemptLockSeat(seatId, userId)

            if (updatedRows > 0) {
                // Kilit başarılıysa güncel koltuk verisini döndürür
                val lockedSeat = seatDao.getSeatById(seatId)?.toDomain()
                    ?: throw Exception("Koltuk bulunamadı")
                Result.success(lockedSeat)
            } else {
                // Etkilenen satır yoksa, aynı milisaniyede başkası tarafından kilitlenmiştir
                Result.failure(Exception("Bu koltuk az önce başka bir kullanıcı tarafından rezerve edildi."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun unlockSeat(seatId: String): Result<Boolean> {
        // Ödeme zaman aşımı veya iptal durumu (İçeriği benzer mantıkla doldurulur)
        return Result.success(true)
    }
}
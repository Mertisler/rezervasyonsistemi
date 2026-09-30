package com.loc.rezervasyonsistemi.domain.usecase

import com.loc.rezervasyonsistemi.domain.models.Seat
import com.loc.rezervasyonsistemi.domain.repository.ISeatRepository
import javax.inject.Inject

class LockSeatUseCase @Inject constructor(
    private val seatRepository: ISeatRepository
) {
    // Sınıfın kendisini bir fonksiyon gibi çağrılabilir yapar
    suspend operator fun invoke(seatId: String, userId: String): Result<Seat> {
        // İhtiyaç duyulursa burada ekstra iş kuralları işletilebilir.
        // Örn: Kullanıcının aynı seans için maksimum bilet sınırına ulaşıp ulaşmadığının kontrolü.

        return seatRepository.lockSeat(seatId, userId)
    }
}
package com.loc.rezervasyonsistemi.data.repository


import com.loc.rezervasyonsistemi.domain.models.Ticket
import com.loc.rezervasyonsistemi.domain.repository.ITicketRepository
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor() : ITicketRepository {
    override suspend fun generateTicket(userId: String, sessionId: String, seatId: String): Result<Ticket> {
        // Bilet oluşturma mantığı
        return Result.success(Ticket("id", userId, sessionId, seatId, "qr_data"))
    }

    override suspend fun getUserTickets(userId: String): Result<List<Ticket>> {
        return Result.success(emptyList())
    }
}
package com.loc.rezervasyonsistemi.domain.repository

import com.loc.rezervasyonsistemi.domain.models.Ticket

interface ITicketRepository {
    // Ödeme başarılı olduğunda bilet oluşturup buluta kaydeder
    suspend fun generateTicket(userId: String, sessionId: String, seatId: String): Result<Ticket>

    // Kullanıcının sahip olduğu biletleri getirir
    suspend fun getUserTickets(userId: String): Result<List<Ticket>>
}
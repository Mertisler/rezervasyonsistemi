package com.loc.rezervasyonsistemi.domain.models

data class Event(
    val id: String,
    val title: String,
    val posterUrl: String,
    val durationMinutes: Int,
    val genre: String
)

data class Session(
    val id: String,
    val eventId: String,
    val hallName: String,
    val startTime: String
)

data class Seat(
    val id: String,
    val sessionId: String,
    val row: Int,
    val column: Int,
    val status: SeatStatus
)

data class Ticket(
    val id: String,
    val userId: String,
    val sessionId: String,
    val seatId: String,
    val qrCodeData: String
)

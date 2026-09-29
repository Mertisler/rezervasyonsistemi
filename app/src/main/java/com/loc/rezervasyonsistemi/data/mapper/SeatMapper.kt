package com.loc.rezervasyonsistemi.data.mapper

import com.loc.rezervasyonsistemi.data.local.entities.SeatEntity
import com.loc.rezervasyonsistemi.domain.models.Seat
import com.loc.rezervasyonsistemi.domain.models.SeatStatus

fun SeatEntity.toDomain(): Seat {
    return Seat(
        id = this.id,
        sessionId = this.sessionId,
        row = this.row,
        column = this.column,
        status = SeatStatus.valueOf(this.status) // String'i Enum'a çevirir
    )
}
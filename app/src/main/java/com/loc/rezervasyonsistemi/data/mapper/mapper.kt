package com.loc.rezervasyonsistemi.data.mapper

import com.loc.rezervasyonsistemi.domain.models.Event
import com.loc.rezervasyonsistemi.domain.models.EventDto

fun EventDto.toDomain(): Event {
    return Event(
        id = this.id,
        title = this.title,
        posterUrl = this.posterUrl,
        durationMinutes = this.durationMinutes,
        genre = this.genre
    )
}
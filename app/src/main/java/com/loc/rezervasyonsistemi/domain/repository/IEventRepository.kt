package com.loc.rezervasyonsistemi.domain.repository


import com.loc.rezervasyonsistemi.domain.models.Event
import com.loc.rezervasyonsistemi.domain.models.Session
import kotlinx.coroutines.flow.Flow

interface IEventRepository {
    // API'den güncel etkinlikleri çeker
    suspend fun getCurrentEvents(): Result<List<Event>>

    // Seçilen etkinliğe ait seansları getirir
    suspend fun getSessionsByEventId(eventId: String): Result<List<Session>>
}
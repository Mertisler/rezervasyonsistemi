package com.loc.rezervasyonsistemi.data.repository

import com.loc.rezervasyonsistemi.data.api.EventApi
import com.loc.rezervasyonsistemi.data.mapper.toDomain
import com.loc.rezervasyonsistemi.domain.models.Event
import com.loc.rezervasyonsistemi.domain.models.Session
import com.loc.rezervasyonsistemi.domain.repository.IEventRepository
import javax.inject.Inject
import kotlin.collections.map

class EventRepositoryImpl @Inject constructor(
    private val eventApi: EventApi
) : IEventRepository {

    // src/com/loc/rezervasyonsistemi/data/repository/EventRepositoryImpl.kt

    override suspend fun getCurrentEvents(): Result<List<Event>> {
        return try {
            // Gerçek API çağrısını şimdilik yorum satırına alıyoruz
            // val dtoList = eventApi.fetchCurrentEvents()
            // val eventList = dtoList.map { it.toDomain() }

            // Ekranda görebilmek için test verileri (Mock Data) üretiyoruz
            val mockEventList = listOf(
                Event(
                    id = "evt_1",
                    title = "Yıldızlararası",
                    posterUrl = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", // Örnek çalışan bir görsel linki
                    durationMinutes = 169,
                    genre = "Bilim Kurgu"
                ),
                Event(
                    id = "evt_2",
                    title = "Gladyatör",
                    posterUrl = "https://image.tmdb.org/t/p/w500/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg",
                    durationMinutes = 155,
                    genre = "Aksiyon / Tarih"
                )
            )

            // Yarım saniyelik bir yüklenme efekti simülasyonu
            kotlinx.coroutines.delay(500)

            Result.success(mockEventList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSessionsByEventId(eventId: String): Result<List<Session>> {
        return try {
            // Sistemin bu aşamasında Session verilerinin bir API'den veya
            // veritabanından getirildiği varsayılarak örnek veri oluşturulur.
            val mockSessions = listOf(
                Session(id = "sess_1", eventId = eventId, hallName = "Salon 1", startTime = "14:00"),
                Session(id = "sess_2", eventId = eventId, hallName = "Salon 2", startTime = "17:30")
            )
            Result.success(mockSessions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
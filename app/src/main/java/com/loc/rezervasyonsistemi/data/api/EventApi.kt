package com.loc.rezervasyonsistemi.data.api

import retrofit2.http.GET
import com.loc.rezervasyonsistemi.data.api.models.EventDto
interface EventApi {
    @GET("events/current")
    suspend fun fetchCurrentEvents(): List<EventDto>
}
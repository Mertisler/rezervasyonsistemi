package com.loc.rezervasyonsistemi.data.api

import retrofit2.http.GET
import com.loc.rezervasyonsistemi.domain.models.EventDto
import dagger.Provides
import javax.inject.Singleton

interface EventApi {
    @GET("events/current")
    suspend fun fetchCurrentEvents(): List<EventDto>
}
package com.loc.rezervasyonsistemi.domain.usecase

import com.loc.rezervasyonsistemi.domain.models.Event
import com.loc.rezervasyonsistemi.domain.repository.IEventRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventRepository: IEventRepository
) {
    suspend operator fun invoke(): Result<List<Event>> {
        return eventRepository.getCurrentEvents()
    }
}
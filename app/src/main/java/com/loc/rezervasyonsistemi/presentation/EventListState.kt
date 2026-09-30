package com.loc.rezervasyonsistemi.presentation


import com.loc.rezervasyonsistemi.domain.models.Event

data class EventListState(
    val isLoading: Boolean = false,
    val events: List<Event> = emptyList(),
    val errorMessage: String? = null
)
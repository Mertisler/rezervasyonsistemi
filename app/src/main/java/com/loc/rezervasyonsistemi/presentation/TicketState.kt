package com.loc.rezervasyonsistemi.presentation

import com.loc.rezervasyonsistemi.domain.models.Ticket

data class TicketState(
    val isLoading: Boolean = false,
    val ticket: Ticket? = null,
    val errorMessage: String? = null
)
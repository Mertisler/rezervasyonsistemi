package com.loc.rezervasyonsistemi.presentation

import com.loc.rezervasyonsistemi.domain.models.Seat

// Ekranın o anki fotoğrafını çeken durum sınıfı
data class SeatSelectionState(
    val isLoading: Boolean = false,
    val seats: List<Seat> = emptyList(),
    val selectedSeat: Seat? = null,
    val errorMessage: String? = null
)
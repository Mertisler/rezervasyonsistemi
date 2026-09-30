package com.loc.rezervasyonsistemi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.rezervasyonsistemi.domain.usecase.LockSeatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeatSelectionViewModel @Inject constructor(
    private val lockSeatUseCase: LockSeatUseCase
) : ViewModel() {

    // Sadece ViewModel içinden değiştirilebilen durum
    private val _state = MutableStateFlow(SeatSelectionState())
    // Arayüzün (View) dışarıdan dinleyeceği salt okunur durum
    val state: StateFlow<SeatSelectionState> = _state.asStateFlow()

    fun onSeatClicked(seatId: String, userId: String) {
        // İşlem başladığında ekranı yükleniyor moduna al ve eski hataları temizle
        _state.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            // UseCase çağrılır ve kilit mekanizması tetiklenir
            val result = lockSeatUseCase(seatId, userId)

            result.onSuccess { lockedSeat ->
                // Kilit başarılıysa seçili koltuğu state'e kaydet
                _state.update {
                    it.copy(
                        isLoading = false,
                        selectedSeat = lockedSeat
                    )
                }
            }.onFailure { error ->
                // Aynı anda başkası aldıysa veya ağ koptuysa hata mesajını arayüze ilet
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Beklenmeyen bir hata oluştu."
                    )
                }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }
}
package com.loc.rezervasyonsistemi.presentation


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.rezervasyonsistemi.domain.repository.ITicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: ITicketRepository,
    savedStateHandle: SavedStateHandle // Navigasyon argümanlarını otomatik yakalar
) : ViewModel() {

    private val _state = MutableStateFlow(TicketState())
    val state: StateFlow<TicketState> = _state.asStateFlow()

    init {
        // Rota tanımından gelen "ticketId" parametresini alır
        val ticketId: String? = savedStateHandle.get<String>("ticketId")
        if (ticketId != null) {
            loadTicketDetails(ticketId)
        } else {
            _state.update { it.copy(errorMessage = "Bilet bulunamadı.") }
        }
    }

    private fun loadTicketDetails(ticketId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            // İlgili biletin buluttan/veritabanından getirilmesi
            // result = ticketRepository.getTicketById(ticketId) vb. işlemleri yapılır
        }
    }
}
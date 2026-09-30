package com.loc.rezervasyonsistemi.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.rezervasyonsistemi.domain.usecase.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EventListState())
    val state: StateFlow<EventListState> = _state.asStateFlow()

    init {
        // ViewModel yaratıldığı an verileri çekmeye başlar
        loadEvents()
    }

    private fun loadEvents() {
        _state.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = getEventsUseCase()

            result.onSuccess { eventList ->
                _state.update {
                    it.copy(isLoading = false, events = eventList)
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(isLoading = false, errorMessage = error.message ?: "Etkinlikler yüklenemedi.")
                }
            }
        }
    }
}
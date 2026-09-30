package com.loc.rezervasyonsistemi.presentation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EventListScreen(
    // Hilt, ViewModel'i otomatik olarak enjekte eder
    viewModel: EventViewModel = hiltViewModel(),
    onEventSelected: (String) -> Unit // Navigasyon için callback
) {
    // ViewModel'deki durumu anlık olarak dinliyoruz
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator() // Yükleniyor animasyonu
        }
        state.errorMessage != null -> {
            Text(text = "Hata: ${state.errorMessage}")
        }
        else -> {
            // Veriler geldiyse ızgara şeklinde listele
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.events) { event ->
                    EventCard(
                        event = event,
                        onClick = { onEventSelected(event.id) }
                    )
                }
            }
        }
    }
}
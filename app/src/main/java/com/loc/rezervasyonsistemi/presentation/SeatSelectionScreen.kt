package com.loc.rezervasyonsistemi.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.rezervasyonsistemi.domain.models.SeatStatus

@Composable
fun SeatSelectionScreen(
    sessionId: String,
    currentUserId: String,
    onTicketPurchased: (String) -> Unit, // Navigasyon callback'i
    viewModel: SeatSelectionViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Perde - Seans: $sessionId",
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(8),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.seats) { seat ->
                    val seatColor = when (seat.status) {
                        SeatStatus.AVAILABLE -> Color.LightGray
                        SeatStatus.LOCKED -> if (seat.id == state.selectedSeat?.id) Color.Green else Color.Yellow
                        SeatStatus.SOLD -> Color.Red
                    }

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .aspectRatio(1f) // Kare görünüm
                            .background(seatColor)
                            .clickable(enabled = seat.status == SeatStatus.AVAILABLE) {
                                viewModel.onSeatClicked(seat.id, currentUserId)
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Seçili koltuk varsa Ödeme/Onay butonu belirir
            if (state.selectedSeat != null) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // Gerçek senaryoda burada ProcessPaymentUseCase çalışır
                        // Ödeme başarılı olduğunda oluşan biletin ID'si navigasyona iletilir
                        val generatedTicketId = "TICKET_${state.selectedSeat!!.id}"
                        onTicketPurchased(generatedTicketId)
                    }
                ) {
                    Text("Ödemeyi Tamamla ve Bileti Al")
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.errorMessage != null) {
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                action = {
                    Text("Tamam", modifier = Modifier.clickable { viewModel.clearError() })
                }
            ) {
                Text(state.errorMessage!!)
            }
        }
    }
}
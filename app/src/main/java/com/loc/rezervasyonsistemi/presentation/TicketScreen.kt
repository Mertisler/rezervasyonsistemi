package com.loc.rezervasyonsistemi.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TicketScreen(
    viewModel: TicketViewModel = hiltViewModel()
) {
    // TicketViewModel, URL'den/Rotadan ID'yi kendisi çekip veriyi hazırlar
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.errorMessage != null -> {
                Text(text = "Hata: ${state.errorMessage}", color = Color.Red)
            }
            state.ticket != null -> {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Biletiniz Hazır!",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(text = "Bilet ID: ${state.ticket!!.id}")
                        Text(text = "Seans ID: ${state.ticket!!.sessionId}")
                        Text(text = "Koltuk ID: ${state.ticket!!.seatId}")

                        Spacer(modifier = Modifier.height(24.dp))

                        // QR Kod için Placeholder
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "QR KOD",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
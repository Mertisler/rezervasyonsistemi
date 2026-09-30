package com.loc.rezervasyonsistemi.core.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loc.rezervasyonsistemi.presentation.EventListScreen
import com.loc.rezervasyonsistemi.presentation.SeatSelectionScreen
import com.loc.rezervasyonsistemi.presentation.TicketScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.EventList.route
    ) {
        // 1. Etkinlik Listesi Ekranı
        composable(route = Screen.EventList.route) {
            EventListScreen(
                onEventSelected = { sessionId ->
                    // Etkinlik seçilince dinamik rota oluşturulup o sayfaya gidilir
                    navController.navigate(Screen.SeatSelection.createRoute(sessionId))
                }
            )
        }

        // 2. Koltuk Seçim Ekranı
        composable(
            route = Screen.SeatSelection.route,
            arguments = listOf(navArgument("sessionId") { type = NavType.StringType })
        ) { backStackEntry ->
            // Rota içindeki parametre arayüze veya oradan ViewModel'e iletilebilir
            val sessionId = backStackEntry.arguments?.getString("sessionId") ?: return@composable

            SeatSelectionScreen(
                sessionId = sessionId,
                currentUserId = "user_123", // Gerçek senaryoda Auth sisteminden gelir
                onTicketPurchased = { ticketId ->
                    navController.navigate(Screen.Ticket.createRoute(ticketId)) {
                        // Bilet alındıktan sonra geri tuşuyla tekrar koltuk seçimine dönülmesini engeller
                        popUpTo(Screen.EventList.route)
                    }
                }
            )
        }

        // 3. Bilet Detay Ekranı
        composable(
            route = Screen.Ticket.route,
            arguments = listOf(navArgument("ticketId") { type = NavType.StringType })
        ) {
            // TicketViewModel id'yi SavedStateHandle üzerinden otomatik yakalar,
            // bu yüzden ekrana parametre geçmeye gerek kalmaz
            TicketScreen()
        }
    }
}


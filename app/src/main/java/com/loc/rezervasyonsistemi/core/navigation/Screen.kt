package com.loc.rezervasyonsistemi.core.navigation


// Uygulamadaki tüm sayfaların rotalarını ve alacakları argümanları standartlaştırır
sealed class Screen(val route: String) {
    object EventList : Screen("event_list")

    // Seans id'si zorunlu argüman olarak tanımlanır
    object SeatSelection : Screen("seat_selection/{sessionId}") {
        fun createRoute(sessionId: String) = "seat_selection/$sessionId"
    }

    // Bilet id'si zorunlu argüman olarak tanımlanır
    object Ticket : Screen("ticket/{ticketId}") {
        fun createRoute(ticketId: String) = "ticket/$ticketId"
    }
}
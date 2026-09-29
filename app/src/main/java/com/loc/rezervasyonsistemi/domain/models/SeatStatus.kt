package com.loc.rezervasyonsistemi.domain.models

enum class SeatStatus {
    AVAILABLE, // Koltuk boş ve seçilebilir
    LOCKED,    // Başka bir kullanıcı işlemi başlattı (geçici kilit)
    SOLD       // Satın alım tamamlandı
}
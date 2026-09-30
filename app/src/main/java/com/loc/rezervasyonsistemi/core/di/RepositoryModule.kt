package com.loc.rezervasyonsistemi.core.di

import com.loc.rezervasyonsistemi.data.repository.EventRepositoryImpl
import com.loc.rezervasyonsistemi.data.repository.SeatRepositoryImpl
import com.loc.rezervasyonsistemi.data.repository.TicketRepositoryImpl
import com.loc.rezervasyonsistemi.domain.repository.IEventRepository
import com.loc.rezervasyonsistemi.domain.repository.ISeatRepository
import com.loc.rezervasyonsistemi.domain.repository.ITicketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Soyut IEventRepository talep edildiğinde, somut EventRepositoryImpl nesnesini teslim eder
    @Binds
    @Singleton
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): IEventRepository

    // Soyut ISeatRepository talep edildiğinde, somut SeatRepositoryImpl nesnesini teslim eder
    @Binds
    @Singleton
    abstract fun bindSeatRepository(
        seatRepositoryImpl: SeatRepositoryImpl
    ): ISeatRepository

    // Not: Binds kullanıldığı için sınıf ve fonksiyonlar "abstract" olarak tanımlanır.
    // Hilt arka planda bu bağlamayı performansı artıracak şekilde kod üreterek yapar.


    @Binds
    @Singleton
    abstract fun bindTicketRepository(
        ticketRepositoryImpl: TicketRepositoryImpl
    ): ITicketRepository
}
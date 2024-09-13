package com.baitent.vocabulity.di

import com.baitent.vocabulity.data.repository.CardRepositoryImpl
import com.baitent.vocabulity.data.repository.MainRepositoryImpl
import com.baitent.vocabulity.data.source.local.CardRepository
import com.baitent.vocabulity.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository

    // CardRepository binding'ini ekliyoruz
    @Binds
    abstract fun bindCardRepository(repositoryImpl: CardRepositoryImpl): CardRepository
}

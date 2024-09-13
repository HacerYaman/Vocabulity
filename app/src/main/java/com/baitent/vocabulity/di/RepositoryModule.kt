package com.baitent.vocabulity.di

import com.baitent.vocabulity.data.repository.CardRepository
import com.baitent.vocabulity.data.repository.CardRepositoryImpl
import com.baitent.vocabulity.data.source.local.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun bindCardRepository(cardDao: CardDao): CardRepository{
        return CardRepositoryImpl(cardDao)
    }
}
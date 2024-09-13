package com.baitent.vocabulity.di

import android.content.Context
import androidx.room.Room
import com.baitent.vocabulity.data.source.local.CardDao
import com.baitent.vocabulity.data.source.local.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDatabase =
        Room.databaseBuilder(context, CardDatabase::class.java, "card_database").build()

    @Provides
    fun provideCardDao(db: CardDatabase): CardDao = db.cardDao()
}
package com.baitent.vocabulity.di

import android.content.Context
import androidx.room.Room
import com.baitent.vocabulity.data.source.local.CardDao
import com.baitent.vocabulity.data.source.local.RoomDB
import com.baitent.vocabulity.ui.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java,
            RoomDB::class.simpleName
        ).build()
    }

    @Provides
    fun provideMainDao(database: RoomDB): CardDao = database.mainDao()

    @Provides
    @Singleton
    fun provideUtil(): Util = Util()

}

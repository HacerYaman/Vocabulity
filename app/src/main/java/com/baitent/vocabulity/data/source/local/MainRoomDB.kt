package com.baitent.vocabulity.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baitent.vocabulity.data.model.CardItem

@Database(entities = [CardItem::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}

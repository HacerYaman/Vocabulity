package com.baitent.vocabulity.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baitent.vocabulity.data.model.CardItem

@Database(entities = [CardItem::class], version = 2, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun mainDao(): CardDao
}
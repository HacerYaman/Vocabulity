package com.baitent.vocabulity.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.baitent.vocabulity.data.model.CardItem

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    suspend fun getAllCards(): List<CardItem>

    @Query("SELECT * FROM cards WHERE status = 'notLearned'")
    suspend fun getNotLearnedCards(): List<CardItem>

    @Query("SELECT * FROM cards WHERE status = 'learned'")
    suspend fun getLearnedCards(): List<CardItem>

    @Insert
    suspend fun insertCard(cardItem: CardItem)

    @Query("UPDATE cards SET status = :status WHERE engWord = :engWord")
    suspend fun updateCardStatus(engWord: String, status: String)
}

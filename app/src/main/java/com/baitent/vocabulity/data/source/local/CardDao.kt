package com.baitent.vocabulity.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.baitent.vocabulity.data.model.CardItem

@Dao
interface CardDao {

    // Tüm kartları getir
    @Query("SELECT * FROM cards")
    suspend fun getAllCards(): List<CardItem>

    // Öğrenilmemiş kartları getir
    @Query("SELECT * FROM cards WHERE status = 'not_learned'")
    suspend fun getNotLearnedCards(): List<CardItem>

    // Öğrenilmiş kartları getir
    @Query("SELECT * FROM cards WHERE status = 'learned'")
    suspend fun getLearnedCards(): List<CardItem>

    // Yeni bir kart ekle
    @Insert
    suspend fun insertCard(card: CardItem)

    // Bir kartın durumunu güncelle
    @Query("UPDATE cards SET status = :status WHERE engWord = :engWord")
    suspend fun updateCardStatus(engWord: String, status: String)
}

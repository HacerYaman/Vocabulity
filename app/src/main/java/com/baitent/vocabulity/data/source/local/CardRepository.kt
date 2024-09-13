package com.baitent.vocabulity.data.source.local
import com.baitent.vocabulity.data.model.CardItem

interface CardRepository {
    suspend fun getAllCards(): List<CardItem>
    suspend fun getNotLearnedCards(): List<CardItem>
    suspend fun getLearnedCards(): List<CardItem>
    suspend fun insertCard(cardItem: CardItem)
    suspend fun insertAll(cards: List<CardItem>) // Liste olarak kart ekleme fonksiyonu
    suspend fun updateCardStatus(engWord: String, status: String)
}

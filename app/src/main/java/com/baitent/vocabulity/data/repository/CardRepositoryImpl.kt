package com.baitent.vocabulity.data.repository

import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardDao
import com.baitent.vocabulity.data.source.local.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : CardRepository {

    override suspend fun getAllCards(): List<CardItem> {
        val cards = cardDao.getAllCards()
        println("All Cards: ${cards.map { it.engWord }}")
        return cards
    }

    override suspend fun getNotLearnedCards(): List<CardItem> {
        val cards = cardDao.getNotLearnedCards()
        println("Not Learned Cards: ${cards.map { it.engWord }}")
        return cards
    }

    override suspend fun getLearnedCards(): List<CardItem> {
        val cards = cardDao.getLearnedCards()
        println("Learned Cards: ${cards.map { it.engWord }}")
        return cards
    }

    override suspend fun insertCard(cardItem: CardItem) {
        cardDao.insertCard(cardItem)
        println("Inserted Card: ${cardItem.engWord}")
    }

    override suspend fun insertAll(cards: List<CardItem>) {
        cardDao.insertAll(cards)
        println("Inserted All Cards: ${cards.map { it.engWord }}") // Eklenen kartları yazdır
    }

    override suspend fun updateCardStatus(engWord: String, status: String) {
        cardDao.updateCardStatus(engWord, status)
        println("Updated Card Status: $engWord, Status: $status")
    }
}

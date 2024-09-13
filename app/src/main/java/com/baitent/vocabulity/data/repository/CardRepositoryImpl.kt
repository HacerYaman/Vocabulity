package com.baitent.vocabulity.data.repository

import com.baitent.vocabulity.data.source.local.CardDao
import com.baitent.vocabulity.data.model.CardItem
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardDao: CardDao) : CardRepository {

    override suspend fun getAllCards(): List<CardItem> = cardDao.getAllCards()

    override suspend fun getNotLearnedCards(): List<CardItem> = cardDao.getNotLearnedCards()

    override suspend fun getLearnedCards(): List<CardItem> = cardDao.getLearnedCards()

    override suspend fun insertCard(cardItem: CardItem) = cardDao.insertCard(cardItem)

    override suspend fun updateCardStatus(engWord: String, status: String) = cardDao.updateCardStatus(engWord, status)
}

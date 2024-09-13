package com.baitent.vocabulity.data.repository

import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.MainDao
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardDao: MainDao) : CardRepository

//    override suspend fun getAllCards(): List<CardItem> = cardDao.getAllCards()
//
//    override suspend fun getNotLearnedCards(): List<CardItem> = cardDao.getNotLearnedCards()
//
//    override suspend fun getLearnedCards(): List<CardItem> = cardDao.getLearnedCards()
//
//    override suspend fun insertCard(cardItem: CardItem) = cardDao.insertCard(cardItem)
//
//    override suspend fun updateCardStatus(engWord: String, status: String) = cardDao.updateCardStatus(engWord, status)
//}

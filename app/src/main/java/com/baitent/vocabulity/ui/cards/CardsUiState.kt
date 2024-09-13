package com.baitent.vocabulity.ui.cards

import com.baitent.vocabulity.data.model.CardItem

data class CardsUiState(
    val isLoading: Boolean = true,
    val notLearnedCards: List<CardItem> = emptyList(),
    val learnedCards: List<CardItem> = emptyList()
)

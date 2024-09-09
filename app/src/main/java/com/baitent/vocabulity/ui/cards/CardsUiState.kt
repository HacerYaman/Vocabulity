package com.baitent.vocabulity.ui.cards

data class CardsUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)
package com.baitent.vocabulity.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardsUiState())
    val uiState: StateFlow<CardsUiState> = _uiState

    init {
        loadNotLearnedCards()
    }

    private fun loadNotLearnedCards() {
        viewModelScope.launch {
            val notLearnedCards = repository.getNotLearnedCards()
            _uiState.value = CardsUiState(
                isLoading = false,
                notLearnedCards = notLearnedCards,
                learnedCards = repository.getLearnedCards()
            )
        }
    }

    fun markCardAsLearned(card: CardItem) {
        viewModelScope.launch {
            repository.updateCardStatus(card.engWord, "learned")
            loadNotLearnedCards()
        }
    }

    fun markCardAsNotLearned(card: CardItem) {
        viewModelScope.launch {
            repository.updateCardStatus(card.engWord, "not_learned")
            loadNotLearnedCards()
        }
    }
}
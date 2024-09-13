package com.baitent.vocabulity.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository // CardRepository'yi ekledik
) : ViewModel() {

    private var _uiState = MutableStateFlow(CardsUiState())
    val uiState: StateFlow<CardsUiState> = _uiState.asStateFlow()

    // Kartı 'öğrenildi' olarak işaretle ve veritabanına kaydet
    fun markAsLearned(cardItem: CardItem) {
        viewModelScope.launch {
            cardRepository.updateCardStatus(cardItem.engWord, "learned")
        }
    }

    // Kartı 'öğrenilmedi' olarak işaretle ve veritabanına kaydet
    fun markAsNotLearned(cardItem: CardItem) {
        viewModelScope.launch {
            cardRepository.updateCardStatus(cardItem.engWord, "not learned")
        }
    }

    // Yeni kart ekleme
    fun addCard(cardItem: CardItem) {
        viewModelScope.launch {
            cardRepository.insertCard(cardItem)
        }
    }

    // Tüm kartları getirme
    fun getAllCards() {
        viewModelScope.launch {
            val cards = cardRepository.getAllCards()
            // UI'ye güncel veri gönderme işlemi burada yapılabilir
        }
    }
}

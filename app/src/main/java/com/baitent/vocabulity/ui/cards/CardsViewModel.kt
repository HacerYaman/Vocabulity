package com.baitent.vocabulity.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardRepository
import com.baitent.vocabulity.ui.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val util: Util
) : ViewModel() {

    fun insertCardsIntoDatabase() {
        viewModelScope.launch {
            val cardList = util.toCardItemList()
            cardRepository.insertAll(cardList)
        }
    }

    private var _uiState = MutableStateFlow(CardsUiState())
    val uiState: StateFlow<CardsUiState> = _uiState.asStateFlow()

    // Kartı "learned" olarak işaretleme fonksiyonu
    fun markAsLearned(cardItem: CardItem) {
        viewModelScope.launch {
            // Veritabanında kartın durumunu güncelle
            cardRepository.updateCardStatus(cardItem.engWord, "learned")
        }
    }

    // Kartı "notLearned" olarak işaretleme fonksiyonu
    fun markAsNotLearned(cardItem: CardItem) {
        viewModelScope.launch {
            // Veritabanında kartın durumunu güncelle
            cardRepository.updateCardStatus(cardItem.engWord, "notLearned")
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

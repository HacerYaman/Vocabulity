package com.baitent.vocabulity.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _learnedCards = MutableLiveData<List<CardItem>>()
    val learnedCards: LiveData<List<CardItem>> get() = _learnedCards

    private val _notLearnedCards = MutableLiveData<List<CardItem>>()
    val notLearnedCards: LiveData<List<CardItem>> get() = _notLearnedCards

    fun loadLearnedCards() {
        viewModelScope.launch {
            val cards = cardRepository.getLearnedCards()
            _learnedCards.postValue(cards)
        }
    }

    fun loadNotLearnedCards() {
        viewModelScope.launch {
            val cards = cardRepository.getNotLearnedCards()
            _notLearnedCards.postValue(cards)
        }
    }
}


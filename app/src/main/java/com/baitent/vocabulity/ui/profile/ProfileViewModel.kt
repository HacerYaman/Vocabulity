package com.baitent.vocabulity.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _learnedCards = MutableLiveData<List<CardItem>>()
    val learnedCards: LiveData<List<CardItem>> get() = _learnedCards

    fun loadLearnedCards() {
        viewModelScope.launch {
            val cards = cardRepository.getLearnedCards()
            _learnedCards.postValue(cards)
        }
    }
//
//    fun getLearnedCards(): Flow<List<CardItem>> = flow {
//        emit(cardRepository.getLearnedCards())
//    }
}

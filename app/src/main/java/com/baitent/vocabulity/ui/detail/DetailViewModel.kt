package com.baitent.vocabulity.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baitent.vocabulity.data.source.local.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    fun updateCardStatus(engWord: String, status: String) {
        viewModelScope.launch {
            cardRepository.updateCardStatus(engWord, status)
        }
    }
}

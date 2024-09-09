package com.baitent.vocabulity.ui.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)
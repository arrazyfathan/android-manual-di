package com.arrazyfathan.androidmanualdependencyinjection.presentation.quote

import com.arrazyfathan.androidmanualdependencyinjection.data.quote.Quote

sealed interface QuoteUiState {
    object Loading : QuoteUiState
    data class Success(val quote: Quote? = null) : QuoteUiState
    data class Failed(val message: String? = null) : QuoteUiState
}
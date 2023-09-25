package com.arrazyfathan.androidmanualdependencyinjection.presentation.quote


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrazyfathan.androidmanualdependencyinjection.domain.Repository
import com.arrazyfathan.androidmanualdependencyinjection.utils.Resources
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class QuoteViewModel(val repository: Repository) : ViewModel() {


    private val _quoteUiState = MutableStateFlow<QuoteUiState>(QuoteUiState.Loading)
    val quoteUiState = _quoteUiState.asStateFlow()

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)


    init {
        getRandomQuoteRepeatedly(5.seconds)
    }

    private fun getRandomQuoteRepeatedly(time: Duration) {
        viewModelScope.launch {
            while (completableJob.isActive) {
                delay(time)
                getRandomQuote()
            }
        }
    }

    private fun getRandomQuote() {
        coroutineScope.launch {
            repository.getRandomQuote()
                .collect { resource ->
                    when (resource) {
                        is Resources.Loading -> {
                            _quoteUiState.update { QuoteUiState.Loading }
                        }

                        is Resources.Error -> {
                            _quoteUiState.update {
                                QuoteUiState.Failed(resource.message)
                            }
                        }

                        is Resources.Success -> {
                            _quoteUiState.update {
                                QuoteUiState.Success(resource.data)
                            }
                        }
                    }
                }
        }
    }

    fun stopRepeating() {
        completableJob.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        completableJob.cancel()
    }

    private fun Job.status(): String = when {
        isActive -> "Active/Completing"
        isCompleted && isCancelled -> "Cancelled"
        isCancelled -> "Cancelling"
        isCompleted -> "Completed"
        else -> "New"
    }
}
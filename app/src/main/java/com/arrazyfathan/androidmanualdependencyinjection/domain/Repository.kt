package com.arrazyfathan.androidmanualdependencyinjection.domain

import com.arrazyfathan.androidmanualdependencyinjection.data.quote.Quote
import com.arrazyfathan.androidmanualdependencyinjection.utils.Resources
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRandomQuote(): Flow<Resources<Quote>>
}
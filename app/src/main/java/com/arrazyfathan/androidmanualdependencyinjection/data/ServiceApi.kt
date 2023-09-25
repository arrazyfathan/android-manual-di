package com.arrazyfathan.androidmanualdependencyinjection.data

import com.arrazyfathan.androidmanualdependencyinjection.data.quote.Quote
import retrofit2.http.GET

interface ServiceApi {

    @GET("/random")
    suspend fun getRandomQuote(): Quote
}
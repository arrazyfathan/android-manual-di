package com.arrazyfathan.androidmanualdependencyinjection.data

import com.arrazyfathan.androidmanualdependencyinjection.domain.Repository
import com.arrazyfathan.androidmanualdependencyinjection.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.lang.Exception

class RepositoryImpl(
    private val serviceApi: ServiceApi
) : Repository {

    override fun getRandomQuote() = flow {
        emit(Resources.Loading())
        try {
            val data = serviceApi.getRandomQuote()
            emit(Resources.Success(data))
        } catch (io: IOException) {
            emit(Resources.Error("No Internet Connection"))
        } catch (e: Exception) {
            emit(Resources.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}
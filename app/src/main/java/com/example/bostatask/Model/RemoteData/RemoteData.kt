package com.example.bostatask.Model.RemoteData

import com.example.bostatask.Model.ApiState
import com.example.bostatask.Model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiServices: ApiServices) : Iremote {
    override fun getCities(): Flow<ApiState<List<City>>> = flow {
     emit(ApiState.Loading)

        try {
           // emit(ApiState.Loading) // Emit loading state
            val response = apiServices.getCities()
            emit(ApiState.Success(response.data)) // Emit success state
        } catch (e: Exception) {
            emit(ApiState.Error(e.localizedMessage ?: "Unknown Error")) // Emit error state
        }
    }

    }


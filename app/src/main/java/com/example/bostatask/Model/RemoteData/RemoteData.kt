package com.example.bostatask.Model.RemoteData

import com.example.bostatask.Model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiServices: ApiServices) : Iremote {
    override fun getCities(): Flow<List<City>> = flow {
     emit(apiServices.getCities().data)
    }

}
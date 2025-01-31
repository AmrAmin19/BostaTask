package com.example.bostatask.Model

import com.example.bostatask.Model.RemoteData.Iremote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class Repo @Inject constructor(private val remoteData: Iremote): Irepo {
    override fun getCities(): Flow<ApiState<List<City>>> {
        return remoteData.getCities()
    }

}
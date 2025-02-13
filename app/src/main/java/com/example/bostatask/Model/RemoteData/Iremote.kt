package com.example.bostatask.Model.RemoteData

import com.example.bostatask.Model.ApiState
import com.example.bostatask.Model.City
import kotlinx.coroutines.flow.Flow

interface Iremote {
     fun getCities(): Flow<ApiState<List<City>>>
}
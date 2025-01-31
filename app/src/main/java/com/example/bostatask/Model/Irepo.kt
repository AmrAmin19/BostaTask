package com.example.bostatask.Model

import kotlinx.coroutines.flow.Flow

interface Irepo {
    fun getCities(): Flow<ApiState<List<City>>>
}
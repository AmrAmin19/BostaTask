package com.example.bostatask.Model.RemoteData

import com.example.bostatask.Model.City
import com.example.bostatask.Model.CityResponse
import retrofit2.http.GET

interface ApiServices {
    @GET("cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5")
    suspend fun getCities(): CityResponse
}
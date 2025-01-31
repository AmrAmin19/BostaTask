package com.example.bostatask.Model


data class CityResponse(
    val success: Boolean,
    val message: String,
    val data: List<City> // This should be a list of City objects
)

data class City(
    val cityId: String, // Match the exact field name
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<District>,
    var isExpanded: Boolean = false // test
)

data class District(
    val districtId: String, // Match the exact field name
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val coverage: String
)
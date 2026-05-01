package com.swg.realestate.data.remote.dto

data class AddressDto(
    val country: String?,
    val locality: String?,
    val postalCode: String?,
    val region: String?,
    val street: String?,
    val geoCoordinates: GeoCoordinatesDto? = null
)
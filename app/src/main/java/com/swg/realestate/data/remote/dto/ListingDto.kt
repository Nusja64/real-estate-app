package com.swg.realestate.data.remote.dto



data class ListingDto(
    val address: AddressDto?,
    val categories: List<String>?,
    val characteristics: CharacteristicsDto?,
    val id: String?,
    val lister: ListerDto?,
    val localization: LocalizationDto?,
    val offerType: String?,
    val prices: PricesDto?
)
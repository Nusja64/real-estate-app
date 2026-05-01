package com.swg.realestate.data.remote.dto

data class PricesDto(
    val buy: BuyDto?,
    val currency: String?,
    val rent: RentDto?
)
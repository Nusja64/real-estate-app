package com.swg.realestate.data.remote.dto

data class ListerBrandingDto(
    val adActive: Boolean?,
    val address: AddressDto?,
    val isPremiumBranding: Boolean?,
    val isQualityPartner: Boolean?,
    val legalName: String?,
    val logoUrl: String?,
    val name: String?,
    val profilePageUrlKeyword: String?
)
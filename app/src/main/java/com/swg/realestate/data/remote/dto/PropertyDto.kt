package com.swg.realestate.data.remote.dto

data class PropertyDto(
    val id: String?,
    val listerBranding: ListerBrandingDto?,
    val listing: ListingDto?,
    val listingType: ListingTypeDto?,
    val remoteViewing: Boolean?
)
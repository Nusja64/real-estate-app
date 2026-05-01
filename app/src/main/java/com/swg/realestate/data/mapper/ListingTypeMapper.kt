package com.swg.realestate.data.mapper

import com.swg.realestate.domain.model.enum.ListingType

fun String?.toListingType(): ListingType {
    return when (this) {
        "TOP" -> ListingType.TOP
        "BASIC" -> ListingType.BASIC
        "STANDARD" -> ListingType.STANDARD
        else -> ListingType.UNKNOWN
    }
}
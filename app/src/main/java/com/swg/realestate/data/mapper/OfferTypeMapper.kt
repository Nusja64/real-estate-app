package com.swg.realestate.data.mapper

import com.swg.realestate.domain.model.enum.OfferType

fun String?.toOfferType(): OfferType {
    return when (this) {
        "BUY" -> OfferType.BUY
        "RENT" -> OfferType.RENT
        else -> OfferType.UNKNOWN
    }
}
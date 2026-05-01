package com.swg.realestate.domain.model

import com.swg.realestate.domain.model.enum.CategoryType
import com.swg.realestate.domain.model.enum.ListingType
import com.swg.realestate.domain.model.enum.OfferType

data class Property(
    val id: String?,
    val title: String,
    val price: Long?,
    val currency: String?,
    val listingType: ListingType,
    val offerType: OfferType,
    val categories: List<CategoryType>,
    val imageUrl: String?,
    val city: String?
)
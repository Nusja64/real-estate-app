package com.swg.realestate.data.mapper

import com.swg.realestate.data.remote.dto.PropertyResponseDto
import com.swg.realestate.domain.model.Property

fun PropertyResponseDto.toDomain(): List<Property>? {
    return results?.map { item ->

        val listing = item.listing
        val de = listing?.localization?.de

        Property(
            id = listing?.id,
            title = de?.text?.title ?: "",
            price = listing?.prices?.buy?.price,
            currency = listing?.prices?.currency,
            listingType = item.listingType?.type.toListingType(),
            offerType = listing?.offerType.toOfferType(),
            categories = listing?.categories.toCategories(),
            imageUrl = de?.attachments?.firstOrNull()?.url,
            city = listing?.address?.locality
        )
    }
}
package com.swg.realestate.presentation.mapper

import com.swg.realestate.domain.model.Property
import com.swg.realestate.presentation.model.PropertyUiModel
import com.swg.realestate.presentation.util.formatPrice

fun Property.toUi(): PropertyUiModel {
    return PropertyUiModel(
        id = id ?: "",
        price = price?.formatPrice(currency) ?: "N/A",
        title = title,
        address = city ?: "",
        imageUrl = imageUrl,
        isBookmarked = false
    )
}
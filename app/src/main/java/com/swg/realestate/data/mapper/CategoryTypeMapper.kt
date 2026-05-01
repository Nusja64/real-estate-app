package com.swg.realestate.data.mapper

import com.swg.realestate.domain.model.enum.CategoryType

fun List<String>?.toCategories(): List<CategoryType> {
    return this?.map {
        when (it) {
            "HOUSE" -> CategoryType.HOUSE
            "SINGLE_HOUSE" -> CategoryType.SINGLE_HOUSE
            "MULTIPLE_DWELLING" -> CategoryType.MULTIPLE_DWELLING
            "CHALET" -> CategoryType.CHALET
            "CASTLE" -> CategoryType.CASTLE
            else -> CategoryType.UNKNOWN
        }
    } ?: emptyList()
}
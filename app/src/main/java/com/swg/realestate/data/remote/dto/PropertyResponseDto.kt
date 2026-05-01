package com.swg.realestate.data.remote.dto

data class PropertyResponseDto(
    val from: Int?,
    val maxFrom: Int?,
    val results: List<PropertyDto>?,
    val size: Int?,
    val total: Int?
)
package com.swg.realestate.presentation.model

data class PropertyUiModel(
    val id: String,
    val price: String,
    val title: String,
    val address: String,
    val imageUrl: String?,
    val isBookmarked: Boolean = false
)
package com.swg.realestate.presentation.util

import java.text.NumberFormat
import java.util.Locale

fun Long.formatPrice(currency: String?): String {
    val format = NumberFormat.getNumberInstance(Locale.GERMANY) // EU format
    return "${format.format(this)} ${currency ?: ""}"
}
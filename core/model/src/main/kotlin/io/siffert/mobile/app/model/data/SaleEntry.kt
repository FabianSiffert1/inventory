package io.siffert.mobile.app.model.data

import kotlinx.datetime.Instant

data class SaleEntry(
    val id: String,
    val assetId: String,
    val value: Double,
    val timestamp: Instant,
)

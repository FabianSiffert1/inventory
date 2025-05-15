package io.siffert.mobile.app.model.data

import kotlinx.datetime.Instant

data class PriceHistoryEntry(
    val id: Long,
    val assetId: String,
    val value: Double,
    val timestamp: Instant,
)

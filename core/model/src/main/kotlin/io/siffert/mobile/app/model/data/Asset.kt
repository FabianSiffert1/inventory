package io.siffert.mobile.app.model.data

data class Asset(
    val id: String,
    val name: String,
    val info: String,
    val trend: Trend,
)
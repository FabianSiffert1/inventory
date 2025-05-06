package io.siffert.mobile.app.core.database.util

import androidx.room.TypeConverter
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import kotlinx.serialization.json.Json
import java.time.LocalDate


class Converters {

    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    @TypeConverter
    fun fromAssetClass(value: AssetClass): String = value.name

    @TypeConverter
    fun toAssetClass(value: String): AssetClass = AssetClass.valueOf(value)

    @TypeConverter
    fun fromCurrency(value: Currency): String = value.name

    @TypeConverter
    fun toCurrency(value: String): Currency = Currency.valueOf(value)

    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long = date.toEpochDay()

    @TypeConverter
    fun toLocalDate(epochDay: Long): LocalDate = LocalDate.ofEpochDay(epochDay)
}

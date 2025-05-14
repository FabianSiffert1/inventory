package io.siffert.mobile.app.core.database.util

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class Converters {

    @TypeConverter
    fun toKotlinInstant(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(value) }
    }

    @TypeConverter
    fun fromKotlinInstant(value: Instant?): Long? {
        return value?.let { value.toEpochMilliseconds() }
    }
}

package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

/** converts kotlinx-datetime Instant to dd.MMMM.yyyy String */
fun Instant.toFullDateString(): String {
    val timeZone = TimeZone.currentSystemDefault()
    val localDateTime = this.toLocalDateTime(timeZone)

    val formatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy")
    return formatter.format(localDateTime.toJavaLocalDateTime())
}

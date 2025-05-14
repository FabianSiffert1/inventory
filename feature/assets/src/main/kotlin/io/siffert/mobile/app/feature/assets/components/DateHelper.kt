package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.prettyPrint(): String {
    return SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(this)
}

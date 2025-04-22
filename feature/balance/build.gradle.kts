plugins {
    alias(libs.plugins.inventory.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.siffert.mobile.app.feature.balance"
}
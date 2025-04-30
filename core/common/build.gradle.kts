plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.inventory.android.library)
    alias(libs.plugins.inventory.android.defaultConventionPlugin)
}

android {
    namespace = "io.siffert.mobile.app.core.common"
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}
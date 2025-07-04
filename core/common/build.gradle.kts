plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.inventory.android.library)
    alias(libs.plugins.inventory.android.defaultConventionPlugin)
}

android { namespace = "io.siffert.mobile.app.core.common" }

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.core)
    implementation(platform(libs.koin.bom))
}

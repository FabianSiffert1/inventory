plugins {
    alias(libs.plugins.inventory.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android { namespace = "io.siffert.mobile.app.feature.balance" }

dependencies {
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}

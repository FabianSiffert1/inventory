plugins {
    alias(libs.plugins.inventory.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.siffert.mobile.app.feature.assets"
}

dependencies {
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    androidTestImplementation(libs.androidx.espresso.core)
}
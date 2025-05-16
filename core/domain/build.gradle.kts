plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.inventory.android.library)
    alias(libs.plugins.inventory.android.defaultConventionPlugin)
}

android { namespace = "io.siffert.mobile.app.core.domain" }

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.junit)
    implementation(libs.androidx.foundation.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

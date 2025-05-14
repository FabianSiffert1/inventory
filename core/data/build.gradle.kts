plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.inventory.android.defaultConventionPlugin)
    alias(libs.plugins.inventory.android.library)
}

android { namespace = "io.siffert.mobile.app.core.data" }

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.androidx.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.kotlinx.datetime)
}

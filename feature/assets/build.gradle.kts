plugins {
    alias(libs.plugins.inventory.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.siffert.mobile.app.feature.assets"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:data"))

    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.androidx.compose)
    implementation(libs.material)
    androidTestImplementation(libs.androidx.espresso.core)
}
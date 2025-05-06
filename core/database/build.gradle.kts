plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.inventory.android.library)
    alias(libs.plugins.inventory.android.defaultConventionPlugin)
    alias(libs.plugins.inventory.android.room)
}

android {
    namespace = "io.siffert.mobile.app.core.database"
}

dependencies {
    implementation(project(":core:model"))


    compileOnly(libs.room.gradlePlugin)
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
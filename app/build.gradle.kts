plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.application)
    id("io.siffert.mobile.app.inventory.convention.androidDefaultConfiguration")
}

android {
    namespace = "io.siffert.mobile.app.inventory"

    defaultConfig {
        applicationId = "io.siffert.mobile.app.inventory"
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    sourceSets["main"].java.srcDirs("src/main/kotlin")

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(project(":core:designsystem"))
}
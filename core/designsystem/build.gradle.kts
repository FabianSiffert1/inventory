plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.inventory.android.defaultConventionPlugin)
}

android {
  namespace = "io.siffert.mobile.app.designsystem"

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.foundation.android)
  implementation(libs.androidx.material3.android)
  implementation(libs.androidx.material3.adaptive.navigation.suite.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  debugImplementation(libs.androidx.ui.tooling)
}

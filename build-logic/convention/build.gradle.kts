plugins {
    `kotlin-dsl`
}

group = "io.siffert.mobile.app.inventory.convention" // Package name for the our plugins

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "io.siffert.mobile.app.inventory.convention.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}
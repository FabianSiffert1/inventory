plugins {
    `kotlin-dsl`
}

group = "io.siffert.mobile.app.inventory.convention" // Package name for the our plugins

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "io.siffert.mobile.app.inventory.convention.androidDefaultConfiguration"
            implementationClass = "plugin.AndroidDefaultConfiguration"
        }
    }
}
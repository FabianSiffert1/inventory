plugins {
    `kotlin-dsl`
}

group = "io.siffert.mobile.app.inventory.buildlogic" // Package name for the our plugins

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.inventory.android.defaultConventionPlugin.get().pluginId
            implementationClass = "plugin.AndroidDefaultConventionPlugin"
        }
    }
}
plugins {
    `kotlin-dsl`
}

group = "io.siffert.mobile.app.inventory.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidDefaultConventionPlugin") {
            id = libs.plugins.inventory.android.defaultConventionPlugin.get().pluginId
            implementationClass = "plugin.AndroidDefaultConventionPlugin"
        }
        register("androidFeatureConventionPlugin"){
            id = libs.plugins.inventory.android.feature.get().pluginId
            implementationClass = "plugin.AndroidFeatureConventionPlugin"
        }
        register("androidLibraryConventionPlugin"){
            id = libs.plugins.inventory.android.library.get().pluginId
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }
    }
}
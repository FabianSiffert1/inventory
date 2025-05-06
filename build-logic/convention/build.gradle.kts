plugins {
    `kotlin-dsl`
}

group = "io.siffert.mobile.app.inventory.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidDefaultConventionPlugin") {
            id = libs.plugins.inventory.android.defaultConventionPlugin.get().pluginId
            implementationClass = "plugin.AndroidDefaultConventionPlugin"
        }
        register("androidFeatureConventionPlugin") {
            id = libs.plugins.inventory.android.feature.get().pluginId
            implementationClass = "plugin.AndroidFeatureConventionPlugin"
        }
        register("androidLibraryConventionPlugin") {
            id = libs.plugins.inventory.android.library.get().pluginId
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }
        register("androidRoomConventionPlugin") {
            id = libs.plugins.inventory.android.room.get().pluginId
            implementationClass = "plugin.AndroidRoomConventionPlugin"
        }
    }
}
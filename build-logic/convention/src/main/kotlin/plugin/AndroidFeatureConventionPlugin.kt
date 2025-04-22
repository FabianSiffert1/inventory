package plugin

import com.android.build.gradle.LibraryExtension
import configuration.ConfigureKotlinDefaults
import configuration.configureAndroidAndCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import io.siffert.mobile.app.inventory.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            extensions.configure<LibraryExtension> {
                configureAndroidAndCompose(this)
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(libs.findLibrary("navigation.compose").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
            pluginManager.apply(ConfigureKotlinDefaults::class.java)
        }
    }
}
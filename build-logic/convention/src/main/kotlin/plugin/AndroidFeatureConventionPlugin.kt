package plugin

import com.android.build.gradle.LibraryExtension
import configuration.ConfigureKoin
import configuration.ConfigureKotlinDefaults
import configuration.configureAndroidAndCompose
import io.siffert.mobile.app.inventory.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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
                "implementation"(libs.findBundle("navigation").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
            pluginManager.apply(ConfigureKotlinDefaults::class.java)
            pluginManager.apply(ConfigureKoin::class.java)
        }
    }
}

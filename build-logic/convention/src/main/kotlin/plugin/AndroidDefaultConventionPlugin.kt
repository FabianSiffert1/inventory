package plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.TestExtension
import configuration.ConfigureKotlinDefaults
import configuration.configureAndroidAndCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidDefaultConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) { apply("org.jetbrains.kotlin.android") }
            with(pluginManager) { apply("org.jetbrains.kotlin.plugin.compose") }

            when {
                pluginManager.hasPlugin("com.android.application") ->
                    extensions.configure<ApplicationExtension> { configureAndroidAndCompose(this) }

                pluginManager.hasPlugin("com.android.library") ->
                    extensions.configure<LibraryExtension> { configureAndroidAndCompose(this) }

                pluginManager.hasPlugin("com.android.test") ->
                    configure<TestExtension> { configureAndroidAndCompose(this) }

                else -> {
                    with(pluginManager) { apply("com.android.library") }
                    extensions.configure<LibraryExtension> { configureAndroidAndCompose(this) }
                }
            }

            pluginManager.apply(ConfigureKotlinDefaults::class.java)
        }
    }
}

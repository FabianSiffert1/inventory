package plugin

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import configuration.ConfigureKotlinDefaults
import configuration.configureAndroidAndCompose
import org.gradle.kotlin.dsl.configure

class AndroidDefaultConfiguration : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension>{
                configureAndroidAndCompose(this)
            }

            pluginManager.apply(ConfigureKotlinDefaults::class.java)
        }
    }
}

package configuration

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import io.siffert.mobile.app.inventory.libs
import org.gradle.kotlin.dsl.getByType

class ConfigureKoin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            dependencies {
                setupKoinBom(libs)

                when {
                    pluginManager.hasPlugin("com.android.application") -> setupKoinAndroid(libs)
                    pluginManager.hasPlugin("com.android.library") -> setupKoinAndroid(libs)
                    pluginManager.hasPlugin("com.android.test") -> {}
                    else -> setupKoinCore(libs)
                }
            }
        }
    }
}

private fun DependencyHandlerScope.setupKoinBom(libs: VersionCatalog) {
    add("implementation", platform(libs.findLibrary("koin.bom").get()))
}

private fun DependencyHandlerScope.setupKoinAndroid(libs: VersionCatalog) {
    add("implementation", libs.findLibrary("koin.android").get())
}

private fun DependencyHandlerScope.setupKoinCore(libs: VersionCatalog) {
    add("implementation", libs.findLibrary("koin.core").get())
}
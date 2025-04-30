package plugin

import configuration.ConfigureKoin
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            setupKoin()
        }
    }
}

private fun Project.setupKoin() {
    pluginManager.apply(ConfigureKoin::class.java)
}
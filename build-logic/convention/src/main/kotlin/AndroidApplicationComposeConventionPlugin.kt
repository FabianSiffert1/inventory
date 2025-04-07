import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("*** AndroidApplicationComposeConventionPlugin invoked ***")
        // Additional configuration here...
    }
}
package configuration

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryDefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

const val TARGET_SDK = 35
const val MIN_SDK  = 28
val JAVA_VERSION = JavaVersion.VERSION_17

internal fun Project.configureAndroidAndCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        compileSdk = TARGET_SDK

        defaultConfig {
            minSdk = MIN_SDK
        }

        when (defaultConfig) {
            is ApplicationDefaultConfig -> {
                (defaultConfig as ApplicationDefaultConfig).configureApplication()
            }
            is LibraryDefaultConfig -> {
                (defaultConfig as LibraryDefaultConfig).configureLibrary()
            }
        }

        compileOptions {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }

        buildFeatures {
            compose = true
        }


        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidxComposeCompiler").get().toString()
        }


        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
        }

    }
}

private fun ApplicationDefaultConfig.configureApplication() {
    targetSdk = TARGET_SDK
}

private fun LibraryDefaultConfig.configureLibrary() {
    consumerProguardFiles("consumer-rules.pro")
}

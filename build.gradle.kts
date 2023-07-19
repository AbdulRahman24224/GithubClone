import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Lib.ANDROID_GRADLE_PLUGIN)
        classpath(Lib.KOTLIN_GRADLE_PLUGIN)
        classpath(Lib.HILT_GRADLE_PLUGIN)
        classpath(Lib.NAVIGATION_SAFE_ARGS_PLUGIN)
    }
}

allprojects{

    afterEvaluate {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {

                if (configurations.findByName("kotlinCompilerPluginClasspath")
                        ?.dependencies
                        ?.any { it.group == "androidx.compose.compiler" } == true) {
                    freeCompilerArgs += listOf(
                        "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
                    )
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
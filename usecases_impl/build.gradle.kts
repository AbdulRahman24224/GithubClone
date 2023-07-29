plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
}

android {

    namespace = "com.example.githubclone"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)
    implementation(Lib.HILT_ANDROID)
    annotationProcessor(Lib.HILT_ANDROID_COMPILER)

    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.REPOSITORIES))
    implementation(project(Modules.USECASES))
}
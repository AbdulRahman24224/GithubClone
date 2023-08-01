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

    // Testing
    testImplementation (Lib.ANDROID_CORE_TESTING)
    testImplementation (Lib.COROUTINES_TEST)
    testImplementation (Lib.MOCKK)
    testImplementation (Lib.JUNIT)
    testImplementation (Lib.TURBINE)
    androidTestImplementation (Lib.ANDROID_TEST_EXT)

    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.REPOSITORIES))
    implementation(project(Modules.USECASES))
}
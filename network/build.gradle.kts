plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.HILT)
    id(Plugins.KAPT)
}

android {

    namespace = Config.APPLICATION_ID
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
    implementation(Lib.LOCAL_BROADCAST)

    implementation(Lib.TIMBER)

    implementation(Lib.HILT_ANDROID)
    annotationProcessor(Lib.HILT_ANDROID_COMPILER)
    
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)

    implementation(Lib.RETROFIT)
    implementation(Lib.RETROFIT_CONVERTER_GSON)
    implementation(Lib.GSON)
    implementation(Lib.OKHTTP3_LOGGING_INTERCEPTOR)


    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
}
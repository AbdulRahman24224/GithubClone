import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.NAVIGATION)
}

android {

    namespace = "com.example.githubClone"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
}

dependencies {

    implementation(Lib.COMPOSE_UI)
    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)
    implementation (Lib.COMPOSE_MATERIAL)
    implementation(Lib.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.COIL)
    debugImplementation(Lib.COMPOSE_UI_TOOLING)

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.COMPOSE_ACTIVITY)

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.CONSTRAINTLAYOUT)

    //ktx
    implementation(Lib.CORE_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    
    // network
    implementation(Lib.RETROFIT)
    implementation(Lib.RETROFIT_CONVERTER_GSON)
    implementation(Lib.GSON)
    implementation(Lib.OKHTTP3_LOGGING_INTERCEPTOR)

    //DI
    implementation(Lib.HILT_ANDROID)
    kapt(Lib.HILT_WORK_KAPT)
    kapt(Lib.HILT_ANDROID_COMPILER)
    kapt(Lib.ROOM_COMPILER)

    // Testing
    testImplementation (Lib.ANDROID_CORE_TESTING)
    testImplementation (Lib.COROUTINES_TEST)
    testImplementation (Lib.MOCKK)
    testImplementation (Lib.JUNIT)
    testImplementation (Lib.TURBINE)
    androidTestImplementation (Lib.ANDROID_TEST_EXT)

    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.USECASES))
}
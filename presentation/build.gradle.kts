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
        viewBinding = true
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
    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation(Lib.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.COIL)
    implementation(Lib.WORK)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)
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
    implementation(Lib.HILT_WORK)
    kapt(Lib.HILT_WORK_KAPT)

    kapt(Lib.HILT_ANDROID_COMPILER)
    kapt(Lib.ROOM_COMPILER)

    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.USECASES))
}
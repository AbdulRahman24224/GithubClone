plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
}

android {
    namespace = Config.APPLICATION_ID
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.ANDROIDX_ANNOTATION)
    implementation(Lib.CORE_KTX)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)
    implementation(Lib.TIMBER)
    implementation(Lib.VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)
    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)
    implementation(Lib.CONSTRAINTLAYOUT_COMPOSE)
    implementation(Lib.ACCOMPANIST_PLACEHOLDER)
    implementation (Lib.COMPOSE_MATERIAL)
    implementation(Lib.LOTTIE_COMPOSE)

    implementation(Lib.COIL)
    implementation(Lib.HILT_ANDROID)
    kapt(Lib.HILT_ANDROID_COMPILER)

    implementation(project(Modules.DOMAIN_MODELS))
}
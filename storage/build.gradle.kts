import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
}

android {

    namespace = "com.example.storage"
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

    implementation(Lib.TIMBER)
    implementation(Lib.HILT_ANDROID)
    annotationProcessor(Lib.HILT_ANDROID_COMPILER)

    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)
    implementation(Lib.SQL_CIPHER)
    implementation(Lib.SQLITE)
    implementation(Lib.GSON)
    kapt(Lib.ROOM_COMPILER)

    implementation(Lib.DATASTORE)
    implementation(Lib.PROTOBUF_JAVALITE)
    implementation(Lib.CRYPTO_TINK)

    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
}
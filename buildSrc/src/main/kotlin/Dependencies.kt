import Version.HILT
import Version.KOTLIN_PLUGIN
import Version.NAVIGATION

object Version {

    const val LOTTIE_VERSION = "5.0.3"
    const val COIL = "2.0.0"
    const val KOTLIN = "1.7.0"

    const val KOTLIN_STDLIB = "1.7.0"
    const val KOTLIN_PLUGIN = "1.7.0"
    const val GRADLE_PLUGIN = "8.0.0"

    const val COROUTINES = "1.7.0"
    const val ANDROIDX = "1.3.0"
    const val BIOMETRIC = "1.1.0"
    const val ANDROIDX_ANNOTATION = "1.2.0"
    const val HILT = "2.42"
    const val LIFECYCLE = "2.4.1"
    const val OKHTTP = "4.9.0"
    const val RETROFIT2 = "2.9.0"
    const val GSON = "2.8.7"
    const val LIFECYCLE_EXTENSIONS = "2.2.0"
    const val KTX = "1.8.0"
    const val ACTIVITY_KTX = "1.4.0"
    const val FRAGMENT_KTX = "1.4.0"
    const val CONSTRAINT = "2.0.4"
    const val MATERIAL = "1.4.0"
    const val TIMBER = "4.7.1"
    const val NAVIGATION = "2.5.0"
    const val ROOM = "2.4.0"
    const val WORK = "2.7.1"
    const val COMPOSE = "1.2.0"
    const val ACCOMPANIST = "0.24.4-alpha"
    const val FIREBASE = "31.2.3"

    const val KOTLINX_SERIALIZATION_JSON = "1.3.2"

}

object Lib {

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Version.GRADLE_PLUGIN}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_PLUGIN"
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$HILT"
    const val NAVIGATION_SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:$NAVIGATION"

    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.ANDROIDX}"
    const val ANDROIDX_ANNOTATION = "androidx.annotation:annotation:${Version.ANDROIDX_ANNOTATION}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Version.KTX}"
    const val BIOMETRIC = "androidx.biometric:biometric:${Version.BIOMETRIC}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    const val WORK = "androidx.work:work-runtime-ktx:${Version.WORK}"

    const val LOCAL_BROADCAST =
        "androidx.localbroadcastmanager:localbroadcastmanager:1.0.0"

    const val LIFECYCLE_PROCESS = "androidx.lifecycle:lifecycle-process:${Version.LIFECYCLE}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"
    const val SYSTEM_UI_CONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:0.17.0"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_WORK_KAPT = "androidx.hilt:hilt-compiler:1.0.0"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"
    const val KOTLINX_COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"

    const val SECURITY_CRYPTO = "androidx.security:security-crypto:1.1.0-alpha06"

    const val VIEWBINDING_PROPERTY_DELEGATE =
        "com.github.kirich1409:viewbindingpropertydelegate:1.4.7"
    const val VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.4.7"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT2}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT2}"
    const val GSON = "com.google.code.gson:gson:${Version.GSON}"
    const val OKHTTP3_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"

    // compose
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Version.ACTIVITY_KTX}"
    // compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Version.COMPOSE}"

    const val ACCOMPANIST_PLACEHOLDER =
        "com.google.accompanist:accompanist-placeholder:${Version.ACCOMPANIST}"
    // Animations
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Version.COMPOSE}"

    // Tooling support (Previews, etc.)
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Version.COMPOSE}"

    // Integration with ViewModels
    const val COMPOSE_LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

    const val COMPOSE_MATERIAL= "androidx.compose.material3:material3:1.0.0"

    const val LOTTIE_COMPOSE = "com.airbnb.android:lottie-compose:${Version.LOTTIE_VERSION}"
    const val COIL = "io.coil-kt:coil-compose:${Version.COIL}"



    //Testing
    const val ANDROID_CORE_TESTING = "androidx.arch.core:core-testing:2.2.0"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
    const val MOCKK = "io.mockk:mockk:1.13.5"
    const val JUNIT = "junit:junit:4.13.2"
    const val ANDROID_TEST_EXT = "androidx.test.ext:junit:1.5.2"
    const val TURBINE = "app.cash.turbine:turbine:1.0.0"


}
import org.gradle.kotlin.dsl.composeCompiler

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt") version "1.9.24"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" // this version matches your Kotlin version
}

android {
    namespace = "com.nomadiq.finnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nomadiq.finnews"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val apiKey = "\"XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX\""
            val baseURL = "https://content.guardianapis.com/"
            buildConfigField("String", "BASE_URL", "\"$baseURL\"")
            buildConfigField("String", "API_KEY", apiKey)
        }

        debug {
            isMinifyEnabled = false
            val apiKey = "\"b77f75d9-492b-4d80-bab3-088e00fd5f7b\""
           // val apiKey = "\"XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX\""
            val baseURL = "https://content.guardianapis.com/"
            buildConfigField("String", "BASE_URL", "\"$baseURL\"")
            buildConfigField("String", "API_KEY", apiKey)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kapt {
        // Allow references to generated code
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    // Ktx Core Kotlin
    implementation("androidx.core:core-ktx:1.16.0")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")

    // Activity for ComponentSelection UI
    implementation("androidx.activity:activity-compose:1.10.1")

    // Compose Bom / UI
    implementation(platform("androidx.compose:compose-bom:2025.07.00"))
    implementation("androidx.compose.ui:ui-android:1.8.3")
    implementation("androidx.compose.ui:ui-graphics-android:1.8.3")

    // Image Loading
    implementation("io.coil-kt:coil-compose:2.7.0")
    // Material 3 - theming
    implementation("androidx.compose.material3:material3:1.3.2")
    implementation("com.google.android.material:material:1.12.0")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.2")

    // AppCompat libs
    implementation("androidx.appcompat:appcompat:1.7.1")

    // Networking Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-moshi:3.0.0")
    implementation("com.squareup.retrofit2:converter-scalars:3.0.0")

    // Moshi converters
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
    implementation("com.squareup.moshi:moshi-adapters:1.15.2")
    implementation("com.squareup.moshi:moshi:1.15.2")

    // Logging Network call
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")

    // Data Mapping Serialisation
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // Coroutines / Asynchronous operations / Flows
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Dependency Injection (Hilt)
    implementation("com.google.dagger:hilt-android:2.57")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-compiler:2.57")

    // Hilt - For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.57")

    // Hilt - For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.57")
    kaptTest("com.google.dagger:hilt-compiler:2.57")

    // Compose preview
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.8.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.8.3")

    // For Coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // JUnit4
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")

    // JUnit Test Runner
    implementation("androidx.test.ext:junit:1.2.1")
    testImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.18.0")

    // Google Truth - Assertions
    testImplementation("com.google.truth:truth:1.4.4")

    // For Mockk
    testImplementation("io.mockk:mockk:1.14.5")

    // Robolectric
    testImplementation("org.robolectric:robolectric:4.15.1")
    testImplementation("androidx.test:core:1.6.1")
    androidTestImplementation("org.robolectric:robolectric:4.15.1")

    // Compose Test rules and transitive dependencies:
    testImplementation("androidx.compose.ui:ui-test-junit4:1.8.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.8.3")
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.8.3")

    // Instrumentation test runner
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test:rules:1.6.1")

    // Optional -- UI testing with Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // Optional -- UI testing with Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.8.3")
    // Optional -- UI testing with UI Automator
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")

    // Navigation with Compose
    implementation("androidx.navigation:navigation-compose:2.9.2")
    implementation("androidx.navigation:navigation-testing:2.9.2")

    // Timber - Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    composeCompiler {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt") version "1.9.22"
}

android {
    namespace = "com.nomadiq.finnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nomadiq.finnews"
        minSdk = 24
        targetSdk = 34
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
            val apiKey = "\"XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX\""
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
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    implementation("androidx.core:core-ktx:1.13.1")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

    // Activity for ComponentSelection UI
    implementation("androidx.activity:activity-compose:1.9.0")

    // Compose Bom / UI
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui-android:1.6.7")
    implementation("androidx.compose.ui:ui-graphics-android:1.6.7")

    // Image Loading
    implementation("io.coil-kt:coil-compose:2.6.0")
    // Material 3 - theming
    implementation("androidx.compose.material3:material3:1.2.1")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")

    // Navigation with Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // AppCompat libs
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Networking Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")

    // Moshi converters
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.moshi:moshi-adapters:1.15.1")
    implementation("com.squareup.moshi:moshi:1.15.1")

    // Logging Network call
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Data Mapping Serialisation
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Coroutines / Asynchronous operations / Flows
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Dependency Injection (Hilt)
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    // Hilt - For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.51.1")

    // Hilt - For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptTest("com.google.dagger:hilt-compiler:2.51.1")

    // Compose preview
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.6.7")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // For Coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // JUnit4
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.12.0")

    // Google Truth - Assertions
    testImplementation("com.google.truth:truth:1.4.2")
    testImplementation("com.google.truth:truth:1.4.2")

    // For Mockk
    testImplementation("io.mockk:mockk:1.13.11")

    // Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Navigation Testing
    implementation("androidx.navigation:navigation-testing:2.7.7")

    // Junit Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Robolectric
    implementation("org.robolectric:robolectric:4.12.2")

    // Testing Compose
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))

    // Timber - Logging
    implementation("com.jakewharton.timber:timber:5.0.1")
}

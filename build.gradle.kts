// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    kotlin("kapt") version "1.9.24"
    id("com.google.dagger.hilt.android") version "2.51" apply false
}

buildscript {
    repositories {
    }

    dependencies {
        // Hilt Plug-in
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}
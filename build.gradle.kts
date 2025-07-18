// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
    kotlin("kapt") version "1.9.24"
    id("com.google.dagger.hilt.android") version "2.57" apply false
}

buildscript {
    repositories {
    }

    dependencies {
        // Hilt Plug-in
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.57")
    }
}
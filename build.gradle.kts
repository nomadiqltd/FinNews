// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.1" apply false
    id("com.android.library") version "8.7.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false
}

buildscript {
    repositories {
        // Repository locations such as Google and maven()
    }

    dependencies {
        // Hilt Plug-in
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.55")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (BuildPlugins.androidBuildTools)
        classpath (BuildPlugins.kotlin)
        classpath (BuildPlugins.navigationSafeArgs)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.39.0"
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all {
                if (!DependencyUpdates.isValidCandidate(currentVersion, candidate.version)) {
                    reject("Release candidate")
                }
            }
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class.java) {
    delete (rootProject.buildDir)
}
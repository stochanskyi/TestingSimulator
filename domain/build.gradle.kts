plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.kotlinCoroutines)

    implementation(Dependencies.koin)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.kotlinCoroutinesTest)
}
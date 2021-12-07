plugins {
    id("com.android.application")

    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.flaringapp.testing_simulator"

        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
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

    flavorDimensions += "appType"
    productFlavors {
        create("user") {
            dimension = "appType"
            versionNameSuffix = "-user"
        }
        create("admin") {
            dimension = "appType"
            versionNameSuffix = "-admin"
        }
        create("shared") {
            dimension = "appType"
            versionNameSuffix = "-shared"
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

val adminImplementation by configurations
val userImplementation by configurations
val sharedImplementation by configurations

dependencies {
    implementation(project(mapOf("path" to ":core")))
    adminImplementation(project(mapOf("path" to ":admin")))
    userImplementation(project(mapOf("path" to ":user")))
    sharedImplementation(project(mapOf("path" to ":admin")))
    sharedImplementation(project(mapOf("path" to ":user")))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.kotlinCoroutines)

    implementation(AndroidDependencies.appCompat)
    implementation(AndroidDependencies.coreKtx)
    implementation(AndroidDependencies.fragmentKtx)
    implementation(AndroidDependencies.navigationKtx)
    implementation(AndroidDependencies.navigationUIKtx)
    implementation(AndroidDependencies.lifecycleViewModelKtx)
    implementation(AndroidDependencies.lifecycleLiveDataKtx)
    implementation(AndroidDependencies.lifecycleCompilerKtx)

    implementation(AndroidDependencies.material)
    implementation(AndroidDependencies.constraintLayout)
    implementation(AndroidDependencies.swipeRefreshLayout)

    implementation(AndroidDependencies.koin)

    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLogging)

    implementation(AndroidDependencies.coil)

    implementation(AndroidDependencies.viewBindingDelegate)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.kotlinCoroutinesTest)

    androidTestImplementation(AndroidDependencies.junit)
    androidTestImplementation(Dependencies.mockito)
    androidTestImplementation(Dependencies.mockitoKotlin)
    androidTestImplementation(Dependencies.mockitoDexMaker)
    androidTestImplementation(Dependencies.kotlinCoroutinesTest) {
        exclude(
            group = "org.jetbrains.kotlinx",
            module = "kotlinx-coroutines-debug"
        )
    }
    androidTestImplementation(AndroidDependencies.espresso)
}
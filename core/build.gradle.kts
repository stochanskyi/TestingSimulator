plugins {
    id("com.android.library")

    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
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
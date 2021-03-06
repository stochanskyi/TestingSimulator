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

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_URL", "\"https://vlpi.azurewebsites.net/api/\"")
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
    implementation(project(mapOf("path" to ":domain")))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.kotlinCoroutines)

    implementation(AndroidDependencies.koin)

    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshi)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLogging)

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
}
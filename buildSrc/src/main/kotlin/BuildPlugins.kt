object BuildPlugins {
    const val androidBuildTools = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // Navigation component
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationKtx}"
}
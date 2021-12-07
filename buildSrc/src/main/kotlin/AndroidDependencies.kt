@file:Suppress("SpellCheckingInspection")

object AndroidDependencies {

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val navigationKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKtx}"
    const val navigationUIKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationKtx}"
    const val lifecycleKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"
    const val lifecycleCompilerKtx =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleKtx}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    const val viewBindingDelegate =
        "com.github.Zhuinden:fragmentviewbindingdelegate-kt:${Versions.viewBindingDelegate}"

    const val koin = "io.insert-koin:koin-android:${Versions.koin}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilGif = "io.coil-kt:coil-gif:${Versions.coil}"
    const val coilSvg = "io.coil-kt:coil-svg:${Versions.coil}"
    const val coilVideo = "io.coil-kt:coil-video:${Versions.coil}"

    const val junit = "androidx.test.ext:junit:${Versions.junitAndroid}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

}
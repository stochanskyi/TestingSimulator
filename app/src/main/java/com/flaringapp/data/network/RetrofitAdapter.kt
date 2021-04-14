package com.flaringapp.data.network

import android.util.Log
import com.flaringapp.app.Constants
import com.flaringapp.data.network.modifiers.ModifierApplyInterceptor
import com.flaringapp.data.network.modifiers.ParametrizedCallAdapterFactory
import com.flaringapp.data.network.modifiers.RequestDataCache
import com.flaringapp.data.network.modifiers.modifier.RequestModifier
import com.flaringapp.data.network.modifiers.setupModifiersCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAdapter {

    companion object {
        private const val LOGGER_TAG = "Http"
    }

    private inline fun <reified T> createClient(
        prefix: String,
        vararg modifiers: RequestModifier
    ): T {
        return createRetrofitClient(prefix, modifiers.toList()).create(T::class.java)
    }

    private fun createRetrofitClient(
        prefix: String,
        modifiers: List<RequestModifier> = emptyList()
    ): Retrofit {
        val dataCache = RequestDataCache()
        return Retrofit.Builder()
            .client(createHttpClient(dataCache))
            .baseUrl(NetworkConstants.serverUrl + prefix)
            .addConverterFactory(MoshiConverterFactory.create())
            .withModifiers(dataCache, modifiers)
            .build()
            .setupModifiersCallAdapterFactory()
    }

    private fun createHttpClient(dataCache: RequestDataCache) = OkHttpClient.Builder()
        .connectTimeout(Constants.API_CALL_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.API_CALL_READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.API_CALL_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(ModifierApplyInterceptor(dataCache))
        .addInterceptor(HttpLoggingInterceptor { message ->
            Log.d(LOGGER_TAG, message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private fun Retrofit.Builder.withModifiers(
        dataCache: RequestDataCache,
        modifiers: List<RequestModifier>
    ) = apply {
        if (modifiers.isEmpty()) return@apply
        addCallAdapterFactory(
            createModifiersCallAdapterFactory(dataCache, modifiers)
        )
    }

    private fun createModifiersCallAdapterFactory(
        dataCache: RequestDataCache,
        modifiers: List<RequestModifier>
    ) = ParametrizedCallAdapterFactory(
        emptyList(),
        dataCache,
        modifiers.toSet()
    )
}
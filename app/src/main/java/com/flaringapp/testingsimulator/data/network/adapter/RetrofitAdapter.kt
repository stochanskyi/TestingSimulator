package com.flaringapp.testingsimulator.data.network.adapter

import android.util.Log
import com.flaringapp.testingsimulator.app.Constants
import com.flaringapp.testingsimulator.data.common.json.uuid.UuidStringAdapter
import com.flaringapp.testingsimulator.data.network.common.modifier.*
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentInterceptor
import com.flaringapp.testingsimulator.data.network.common.useragent.UserAgentProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAdapter(
    private val userAgentProvider: UserAgentProvider,
    private val modifierAnnotationProcessor: RequestModifierAnnotationProcessor?,
) : NetworkAdapter {

    companion object {
        private const val LOGGER_TAG = "Http"

        private val moshi: Moshi by lazy {
            Moshi.Builder()
                .add(UuidStringAdapter.instance)
                .build()
        }
    }

    override fun createClient(
        baseUrl: String,
        staticModifiers: List<RequestModifier>
    ): Retrofit {
        val dataCache = RequestDataCache()
        return Retrofit.Builder()
            .client(createHttpClient(dataCache))
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addModifiersCallAdapterFactory(dataCache, staticModifiers)
            .build()
    }

    private fun createHttpClient(dataCache: RequestDataCache) = OkHttpClient.Builder()
        .connectTimeout(Constants.API_CALL_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.API_CALL_READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.API_CALL_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(UserAgentInterceptor(userAgentProvider.provideUserAgent()))
        .addInterceptor(RequestModifierApplyingInterceptor(dataCache))
        .addInterceptor(createLoggingInterceptor())
        .build()

    private fun createLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Log.d(LOGGER_TAG, message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun Retrofit.Builder.addModifiersCallAdapterFactory(
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
        factories = emptyList(),
        dataCache = dataCache,
        annotationProcessor = modifierAnnotationProcessor,
        staticModifiers = modifiers.toSet(),
    )
}
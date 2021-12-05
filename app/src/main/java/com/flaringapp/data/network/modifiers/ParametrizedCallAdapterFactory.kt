package com.flaringapp.data.network.modifiers

import com.flaringapp.data.network.modifiers.annotations.AppendApiKey
import com.flaringapp.data.network.modifiers.annotations.WithoutApiKey
import com.flaringapp.data.network.modifiers.modifier.ComplexRequestModifier
import com.flaringapp.data.network.modifiers.modifier.RequestModifier
import com.flaringapp.data.network.modifiers.modifier.RequestTokenAppender
import com.flaringapp.data.network.modifiers.modifier.RequestTokenCleaner
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ParametrizedCallAdapterFactory(
    private var factories: List<CallAdapter.Factory>,
    private val dataCache: RequestDataCache,
    private val staticModifiers: Collection<RequestModifier>
) : CallAdapter.Factory() {

    fun setupFactories(factories: List<CallAdapter.Factory>) {
        this.factories = factories
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        for (factory in factories) {
            val adapter = factory.get(returnType, annotations, retrofit) ?: continue
            val data = createModifierFromAnnotations(annotations)
                ?: return findAnySuitableAdapter(returnType, annotations, retrofit)
            return ParametrizedCallAdapter(adapter, dataCache, data)
        }
        return null
    }

    private fun createModifierFromAnnotations(annotations: Array<Annotation>): RequestModifier? {
        val modifiers = staticModifiers.toMutableList()
        annotations.forEach {
            modifiers += when (it) {
                is AppendApiKey -> RequestTokenAppender()
                is WithoutApiKey -> RequestTokenCleaner()
                else -> return@forEach
            }
        }
        if (modifiers.isEmpty()) return null
        return ComplexRequestModifier(
            modifiers.distinctBy { it.javaClass }
        )
    }

    private fun findAnySuitableAdapter(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        factories.forEach {
            val adapter = it.get(returnType, annotations, retrofit)
            if (adapter != null) return adapter
        }
        return null
    }
}

fun ParametrizedCallAdapterFactory.setupWith(retrofit: Retrofit) {
    setupFactories(
        retrofit.callAdapterFactories().filter { it != this }
    )
}

fun Retrofit.setupModifiersCallAdapterFactory(): Retrofit {
    callAdapterFactories().filterIsInstance(ParametrizedCallAdapterFactory::class.java)
        .firstOrNull()
        ?.setupWith(this)
    return this
}
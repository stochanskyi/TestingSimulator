package com.flaringapp.testingsimulator.data.network.common.modifier

import com.flaringapp.testingsimulator.data.network.modifiers.token.AppendToken
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenCleaner
import com.b2bmeetup.data.network.modifier.campustoken.WithoutToken
import com.flaringapp.testingsimulator.data.network.common.modifier.*
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ParametrizedCallAdapterFactory(
    private val factories: List<CallAdapter.Factory>,
    private val dataCache: RequestDataCache,
    private val annotationProcessor: RequestModifierAnnotationProcessor?,
    private val staticModifiers: Collection<RequestModifier>
) : CallAdapter.Factory() {

    init {
        require(factories.isNotEmpty()) {
            "Parametrized call adapter requires not empty factories list"
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val data = createModifierFromAnnotations(annotations)
            ?: return findAnySuitableAdapter(returnType, annotations, retrofit)

        for (factory in factories) {
            val adapter = factory.get(returnType, annotations, retrofit) ?: continue
            return ParametrizedCallAdapter(adapter, dataCache, data)
        }
        return null
    }

    private fun createModifierFromAnnotations(annotations: Array<Annotation>): RequestModifier? {
        val modifiers = staticModifiers.toMutableList()
        annotations.forEach { annotation ->
            val annotationModifier = resolveCommonAnnotationModifier(annotation)
                ?: resolveSpecificAnnotationModifier(annotation)
                ?: return@forEach
            modifiers += annotationModifier
        }

        if (modifiers.isEmpty()) return null
        return ComplexRequestModifier(
            modifiers.distinctBy { it.javaClass }
        )
    }

    private fun resolveCommonAnnotationModifier(annotation: Annotation): RequestModifier? {
        return when (annotation) {
            is AppendToken -> RequestTokenAppender()
            is WithoutToken -> RequestTokenCleaner()
            else -> null
        }
    }

    private fun resolveSpecificAnnotationModifier(annotation: Annotation): RequestModifier? {
        return annotationProcessor?.resolveAnnotationModifier(annotation)
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
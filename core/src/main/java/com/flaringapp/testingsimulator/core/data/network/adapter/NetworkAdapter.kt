package com.flaringapp.testingsimulator.core.data.network.adapter

import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifier
import retrofit2.Retrofit

interface NetworkAdapter {

    fun createClient(
        baseUrl: String,
        staticModifiers: List<RequestModifier> = emptyList()
    ): Retrofit

    fun ClientStaticModifiers.createClient(baseUrl: String): Retrofit {
        return createClient(baseUrl, modifiers)
    }

}

@Suppress("unused")
fun NetworkAdapter.withModifiers(
    vararg modifiers: RequestModifier
): ClientStaticModifiers {
    return ClientStaticModifiers(modifiers.toList())
}

class ClientStaticModifiers(
    val modifiers: List<RequestModifier>
)
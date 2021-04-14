package com.flaringapp.data.network.modifiers

import com.flaringapp.data.network.modifiers.modifier.RequestModifier
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ParametrizedCallAdapter<R, T>(
    private val adapter: CallAdapter<R, T>,
    private val dataCache: RequestDataCache,
    private val modifier: RequestModifier
): CallAdapter<R, T> {

    override fun responseType(): Type {
        return adapter.responseType()
    }

    override fun adapt(call: Call<R>): T {
        val request = call.request()
        dataCache.storeRequestModifier(request, modifier)
        return adapter.adapt(call)
    }
}
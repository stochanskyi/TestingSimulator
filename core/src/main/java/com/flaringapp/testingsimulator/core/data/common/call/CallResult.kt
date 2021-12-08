package com.flaringapp.testingsimulator.core.data.common.call

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

typealias CallResultList<D> = CallResult<List<D>>
typealias CallResultNothing = CallResult<Unit?>

private typealias Transformer<D, O> = D.() -> O
private typealias InlineTransformer<D, O> = suspend (D) -> CallResult<O>

sealed class CallResult<D> {

    class Success<D>(val data: D) : CallResult<D>()

    open class Error<D>(
        val exception: Throwable?,
        val errorType: ErrorType? = null,
    ) : CallResult<D>() {

        constructor(message: String, errorType: ErrorType? = null) : this(
            RuntimeException(message), errorType
        )
    }

    class UnknownError<D> : Error<D>(null)

    fun <O> transform(transformer: Transformer<D, O>): CallResult<O> {
        return when (this) {
            is Success -> Success(transformer(data))
            is Error -> Error(exception, errorType)
        }
    }

    suspend fun <O> transformInline(transformer: InlineTransformer<D, O>): CallResult<O> {
        return when (this) {
            is Success -> transformer(data)
            is Error -> Error(exception, errorType)
        }
    }

    fun doOnSuccess(action: (D) -> Unit) = apply {
        if (this is Success) {
            action(data)
        }
    }

    suspend fun doOnSuccessSuspend(action: suspend (D) -> Unit) = apply {
        if (this is Success) {
            action(data)
        }
    }

    suspend fun doOnSuccessSuspend(
        context: CoroutineContext,
        action: suspend CoroutineScope.(D) -> Unit
    ) = apply {
        if (this is Success) {
            withContext(context) {
                action(data)
            }
        }
    }

    fun doOnError(action: () -> Unit) = apply {
        if (this is Error) {
            action()
        }
    }

    suspend fun doOnErrorSuspend(action: suspend () -> Unit) = apply {
        if (this is Error) {
            action()
        }
    }

    suspend fun doOnErrorSuspend(
        context: CoroutineContext,
        action: suspend CoroutineScope.() -> Unit
    ) = apply {
        if (this is Error) {
            withContext(context) {
                action()
            }
        }
    }

    fun ignoreData(): CallResultNothing {
        return when (this) {
            is Success -> Success(Unit)
            is Error -> Error(exception, errorType)
        }
    }

    companion object {
        suspend fun <D> anySuccess(
            specificError: Error<D>.() -> Boolean,
            noSuccess: () -> CallResult<D>,
            vararg sources: suspend () -> CallResult<D>,
        ): CallResult<D> {
            sources.forEach {
                when (val callResult = it()) {
                    is Success<*> -> return callResult
                    is Error -> {
                        if (!specificError(callResult)) return callResult
                    }
                }
            }
            return noSuccess()
        }
    }
}

fun <D, O> CallResult<List<D>>.transformList(transformer: Transformer<D, O>) = transform {
    map { it.transformer() }
}

fun <D, O> CallResult<List<D>>.transformListNotNull(
    transformer: Transformer<D, O?>
): CallResult<List<O>> = transform {
    mapNotNull { it.transformer() }
}

val CallResult<*>.isSuccess: Boolean
    get() = this is CallResult.Success

val <D> CallResult<D>.dataIfSuccess: D?
    get() = if (this is CallResult.Success) data else null

fun <D> CallResult<*>.castedError(): CallResult.Error<D>? {
    if (this !is CallResult.Error) return null
    return CallResult.Error(exception, errorType)
}

fun <D> CallResult.Error<*>.castedError(): CallResult.Error<D> {
    return CallResult.Error(exception, errorType)
}
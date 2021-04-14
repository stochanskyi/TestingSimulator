package com.flaringapp.data.common.call

typealias CallResultList<T> = CallResult<List<T>>
typealias CallResultNothing = CallResult<Unit?>

private typealias Transformer<T, U> = T.() -> U
private typealias InlineTransformer<T, U> = T.() -> CallResult<U>

sealed class CallResult<T> {

    class Success<T>(val data: T) : CallResult<T>()

    open class Error<T>(
        val errorType: String? = null,
        val message: String
    ) : CallResult<T>() {

        constructor(message: String) : this(null, message)
    }

    class UnknownError<T> : Error<T>("")

    fun <U> transform(transformer: Transformer<T, U>): CallResult<U> {
        return when (this) {
            is Success -> Success(transformer(data))
            is Error -> Error(errorType, message)
        }
    }

    fun <U> transformInline(transformer: InlineTransformer<T, U>): CallResult<U> {
        return when (this) {
            is Success -> transformer(data)
            is Error -> Error(errorType, message)
        }
    }

    fun doOnSuccess(action: (T) -> Unit) = apply {
        if (this is Success) {
            action(data)
        }
    }

    fun doOnError(action: () -> Unit) = apply {
        if (this is Error) {
            action()
        }
    }

    fun ignoreData(): CallResultNothing {
        return when (this) {
            is Success -> Success(Unit)
            is Error -> Error(errorType, message)
        }
    }

    companion object {
        suspend fun <T> anySuccess(
            specificError: Error<T>.() -> Boolean,
            noSuccess: () -> CallResult<T>,
            vararg sources: suspend () -> CallResult<T>,
        ): CallResult<T> {
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

fun <T, U> CallResult<List<T>>.transformList(transformer: Transformer<T, U>) = transform {
    map { it.transformer() }
}

fun <T, U> CallResult<List<T>>.transformListNotNull(
    transformer: Transformer<T, U?>
): CallResult<List<U>> = transform {
    mapNotNull { it.transformer() }
}

val CallResult<*>.isSuccess: Boolean
    get() = this is CallResult.Success

val <T> CallResult<T>.dataIfSuccess: T?
    get() = if (this is CallResult.Success) data else null

fun <T> CallResult<*>.castedError(): CallResult.Error<T>? {
    if (this !is CallResult.Error) return null
    return CallResult.Error(errorType, message)
}

fun <T> CallResult.Error<*>.castedError(): CallResult.Error<T> {
    return CallResult.Error(errorType, message)
}
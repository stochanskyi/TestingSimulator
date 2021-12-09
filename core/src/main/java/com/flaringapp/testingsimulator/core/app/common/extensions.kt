package com.flaringapp.testingsimulator.core.app.common

typealias Action = () -> Unit

fun <T> MutableCollection<T>.clearAndAdd(other: Collection<T>) {
    clear()
    addAll(other)
}

fun <T> MutableCollection<T>.tryAdd(item: T?) {
    if (item == null) return
    add(item)
}

fun <T> List<T>.reversedIf(condition: Boolean): List<T> {
    return if (condition) reversed()
    else this
}

inline fun <K, V, M : Map<out K, V>> M.forEachIndexed(action: (index: Int, key: K, value: V) -> Unit) {
    entries.forEachIndexed { index, entry ->
        action(index, entry.key, entry.value)
    }
}

fun <K, V> Map<K, V>.getKey(value: V) =
    entries.firstOrNull { it.value == value }?.key

fun <K, V> MutableMap<K, V>.clearAndPut(other: Map<K, V>) {
    clear()
    putAll(other)
}

fun <T> Sequence<T>.isEmpty() = !iterator().hasNext()

fun <T> Sequence<T>.isNotEmpty() = iterator().hasNext()

fun String.takeIfNotEmpty() = takeIf { it.isNotEmpty() }

infix fun String.orIfEmpty(other: String): String {
    return takeIfNotEmpty() ?: other
}

infix fun String?.orIfNullOrEmpty(other: String): String {
    return this?.takeIfNotEmpty() ?: other
}

fun String.uppercaseFirstChar(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecaseChar()
        else it
    }
}

fun <T> lazyNonSynchronized(initializer: () -> T) = lazy(initializer)
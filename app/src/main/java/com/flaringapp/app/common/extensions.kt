package com.flaringapp.app.common

typealias Action = () -> Unit

fun <T> MutableCollection<T>.clearAndAdd(other: Collection<T>) {
    clear()
    addAll(other)
}

inline fun <K, V, M : Map<out K, V>> M.forEachIndexed(action: (index: Int, key: K, value: V) -> Unit) {
    entries.forEachIndexed { index, entry ->
        action(index, entry.key, entry.value)
    }
}

fun <K, V> Map<K, V>.getKey(value: V) =
    entries.firstOrNull { it.value == value }?.key

fun String.takeIfNotEmpty() = takeIf { it.isNotEmpty() }

infix fun String.orIfEmpty(other: String): String {
    return takeIfNotEmpty() ?: other
}
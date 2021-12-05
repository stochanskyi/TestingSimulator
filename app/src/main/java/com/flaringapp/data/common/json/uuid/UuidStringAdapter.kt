package com.flaringapp.data.common.json.uuid

import com.squareup.moshi.*
import java.lang.reflect.Type

class UuidStringAdapter(
    private val nullUuidChecker: NullUuidChecker,
    private val delegateAdapter: JsonAdapter<String?>,
) : JsonAdapter<String?>() {

    companion object {
        val instance = UuidStringAdapterFactory()
    }

    override fun fromJson(reader: JsonReader): String? {
        val string = delegateAdapter.fromJson(reader)
        return string.takeIf { !nullUuidChecker.isNullUuid(string) }
    }

    override fun toJson(writer: JsonWriter, value: String?) =
        throw UnsupportedOperationException("UuidStringAdapter is only used to deserialize objects")

    class UuidStringAdapterFactory : Factory {
        override fun create(
            type: Type,
            annotations: Set<Annotation>,
            moshi: Moshi
        ): JsonAdapter<String?>? {
            val delegateAnnotations = Types.nextAnnotations(annotations, UuidString::class.java)
                ?: return null

            if (Types.getRawType(type) != String::class.java) {
                throw IllegalArgumentException("Only string may be annotated with @UuidString. Found: $type")
            }

            val elementAdapter: JsonAdapter<String?> =
                moshi.nextAdapter(this, type, delegateAnnotations)

            return UuidStringAdapter(NullUuidChecker.instance, elementAdapter)
        }
    }
}
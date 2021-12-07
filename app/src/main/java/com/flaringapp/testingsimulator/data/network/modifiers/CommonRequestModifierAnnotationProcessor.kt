package com.flaringapp.testingsimulator.data.network.modifiers

import com.b2bmeetup.data.network.modifier.campustoken.WithoutToken
import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifier
import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifierAnnotationProcessor
import com.flaringapp.testingsimulator.data.network.modifiers.token.AppendToken
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenAppender
import com.flaringapp.testingsimulator.data.network.modifiers.token.RequestTokenCleaner

class CommonRequestModifierAnnotationProcessor : RequestModifierAnnotationProcessor {

    override fun resolveAnnotationModifier(annotation: Annotation): RequestModifier? {
        return when (annotation) {
            is AppendToken -> RequestTokenAppender()
            is WithoutToken -> RequestTokenCleaner()
            else -> null
        }
    }


}
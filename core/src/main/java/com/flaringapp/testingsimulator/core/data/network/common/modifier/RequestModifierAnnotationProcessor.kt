package com.flaringapp.testingsimulator.core.data.network.common.modifier

interface RequestModifierAnnotationProcessor {

    fun resolveAnnotationModifier(annotation: Annotation): RequestModifier?

}
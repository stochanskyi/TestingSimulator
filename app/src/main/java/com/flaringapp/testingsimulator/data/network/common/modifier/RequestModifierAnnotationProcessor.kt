package com.flaringapp.testingsimulator.data.network.common.modifier

interface RequestModifierAnnotationProcessor {

    fun resolveAnnotationModifier(annotation: Annotation): RequestModifier?

}
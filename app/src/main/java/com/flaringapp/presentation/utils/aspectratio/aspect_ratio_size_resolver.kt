package com.flaringapp.presentation.utils.aspectratio

import android.view.View
import kotlin.math.min

class AspectRatioSize(val width: Int, val height: Int) {

    companion object {
        val ZERO = AspectRatioSize(0, 0)
    }

}

abstract class SizeResolver {

    var aspectRatioWidth: Int = 1
    var aspectRatioHeight: Int = 1

    abstract fun resolve(widthSpec: Int, heightSpec: Int): AspectRatioSize

    protected fun isSpecExactSize(spec: Int): Boolean {
        return View.MeasureSpec.getMode(spec) == View.MeasureSpec.EXACTLY
    }
}

class DominantWidthSizeResolver : SizeResolver() {

    override fun resolve(widthSpec: Int, heightSpec: Int): AspectRatioSize {
        if (!isSpecExactSize(widthSpec)) return AspectRatioSize.ZERO

        val width = View.MeasureSpec.getSize(widthSpec)
        val height = width / aspectRatioWidth * aspectRatioHeight

        return AspectRatioSize(width, height)
    }
}

class DominantHeightSizeResolver : SizeResolver() {

    override fun resolve(widthSpec: Int, heightSpec: Int): AspectRatioSize {
        if (!isSpecExactSize(heightSpec)) return AspectRatioSize.ZERO

        val height = View.MeasureSpec.getSize(heightSpec)
        val width = height * aspectRatioWidth / aspectRatioHeight

        return AspectRatioSize(width, height)
    }
}

class DominantSmallerSideSizeResolver : SizeResolver() {

    override fun resolve(widthSpec: Int, heightSpec: Int): AspectRatioSize {
        val width = View.MeasureSpec.getSize(widthSpec)
        val height = View.MeasureSpec.getSize(heightSpec)
        val size = min(width, height)
        return AspectRatioSize(size, size)
    }
}

class AutoSizeResolver(
    private val dominantWidthResolver: SizeResolver,
    private val dominantHeightResolver: SizeResolver,
    private val dominantSmallestResolver: SizeResolver,
) : SizeResolver() {

    override fun resolve(widthSpec: Int, heightSpec: Int): AspectRatioSize {
        val isWidthExact = isSpecExactSize(widthSpec)
        val isHeightExact = isSpecExactSize(heightSpec)

        val resolver = when {
            isWidthExact && isHeightExact -> dominantSmallestResolver
            isWidthExact -> dominantWidthResolver
            isHeightExact -> dominantHeightResolver
            else -> return AspectRatioSize.ZERO
        }

        resolver.aspectRatioWidth = aspectRatioWidth
        resolver.aspectRatioHeight = aspectRatioHeight

        return resolver.resolve(widthSpec, heightSpec)
    }
}
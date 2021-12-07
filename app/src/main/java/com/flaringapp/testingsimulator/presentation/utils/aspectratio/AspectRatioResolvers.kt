package com.flaringapp.testingsimulator.presentation.utils.aspectratio

object AspectRatioResolvers {

    val DOMINANT_WIDTH_RESOLVER = DominantWidthSizeResolver()
    val DOMINANT_HEIGHT_RESOLVER = DominantHeightSizeResolver()
    val DOMINANT_SMALLEST_RESOLVER = DominantSmallerSideSizeResolver()
    val DOMINANT_AUTO_RESOLVER = AutoSizeResolver(
        DOMINANT_WIDTH_RESOLVER,
        DOMINANT_HEIGHT_RESOLVER,
        DOMINANT_SMALLEST_RESOLVER
    )

}
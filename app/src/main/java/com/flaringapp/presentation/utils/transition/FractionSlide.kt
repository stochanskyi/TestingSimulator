package androidx.transition

import android.animation.Animator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.IntDef
import androidx.annotation.RestrictTo
import androidx.core.content.res.TypedArrayUtils
import androidx.core.view.ViewCompat
import org.xmlpull.v1.XmlPullParser

class FractionSlide : Visibility {

    companion object {

        private val sDecelerate: TimeInterpolator = DecelerateInterpolator()
        private val sAccelerate: TimeInterpolator = AccelerateInterpolator()
        private val PROPNAME_SCREEN_POSITION = "android:slide:screenPosition"

        /** @hide
         */
        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
        @Retention(AnnotationRetention.SOURCE)
        @IntDef(
            Gravity.LEFT, Gravity.TOP, Gravity.RIGHT, Gravity.BOTTOM, Gravity.START, Gravity.END
        )
        annotation class GravityFlag

        private interface CalculateSlide {
            /** Returns the translation value for view when it goes out of the scene  */
            fun getGoneX(sceneRoot: ViewGroup, view: View): Float

            /** Returns the translation value for view when it goes out of the scene  */
            fun getGoneY(sceneRoot: ViewGroup, view: View): Float
        }

        private abstract class CalculateSlideHorizontal : CalculateSlide {
            override fun getGoneY(sceneRoot: ViewGroup, view: View): Float {
                return view.translationY
            }
        }

        private abstract class CalculateSlideVertical : CalculateSlide {
            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                return view.translationX
            }
        }

        private class CalculateSlideFraction(
            source: CalculateSlide,
            private val fraction: Float
        ) : CalculateSlide {

            val source: CalculateSlide = (source as? CalculateSlideFraction)?.source ?: source

            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                val diff = source.getGoneX(sceneRoot, view) - view.translationX
                return view.translationX + diff * fraction
            }

            override fun getGoneY(sceneRoot: ViewGroup, view: View): Float {
                val diff = source.getGoneY(sceneRoot, view) - view.translationY
                return view.translationY + diff * fraction
            }
        }

        private val sCalculateLeft: CalculateSlide = object : CalculateSlideHorizontal() {
            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                return view.translationX - sceneRoot.width
            }
        }

        private val sCalculateStart: CalculateSlide = object : CalculateSlideHorizontal() {
            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                val isRtl =
                    (ViewCompat.getLayoutDirection(sceneRoot) == ViewCompat.LAYOUT_DIRECTION_RTL)
                return if (isRtl) {
                    view.translationX + sceneRoot.width
                } else {
                    view.translationX - sceneRoot.width
                }
            }
        }

        private val sCalculateTop: CalculateSlide = object : CalculateSlideVertical() {
            override fun getGoneY(sceneRoot: ViewGroup, view: View): Float {
                return view.translationY - sceneRoot.height
            }
        }

        private val sCalculateRight: CalculateSlide = object : CalculateSlideHorizontal() {
            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                return view.translationX + sceneRoot.width
            }
        }

        private val sCalculateEnd: CalculateSlide = object : CalculateSlideHorizontal() {
            override fun getGoneX(sceneRoot: ViewGroup, view: View): Float {
                val isRtl = (ViewCompat.getLayoutDirection(sceneRoot)
                        == ViewCompat.LAYOUT_DIRECTION_RTL)
                return if (isRtl) {
                    view.translationX - sceneRoot.width
                } else {
                    view.translationX + sceneRoot.width
                }
            }
        }

        private val sCalculateBottom: CalculateSlide = object : CalculateSlideVertical() {
            override fun getGoneY(sceneRoot: ViewGroup, view: View): Float {
                return view.translationY + sceneRoot.height
            }
        }
    }

    constructor(
        slideFraction: Float,
        slideEdge: Int = Gravity.BOTTOM
    ) {
        setFraction(slideFraction)
        setSlideEdge(slideEdge)
    }

    @SuppressLint("RestrictedApi") // remove once core lib would be released with the new
    // LIBRARY_GROUP_PREFIX restriction. tracking in b/127286008
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, Styleable.SLIDE)
        val edge = TypedArrayUtils.getNamedInt(
            a, (attrs as XmlPullParser?)!!, "slideEdge",
            Styleable.Slide.SLIDE_EDGE, Gravity.BOTTOM
        )
        a.recycle()
        setSlideEdge(edge)
    }

    private var mfraction: Float = 1f
    private var mSlideCalculator = sCalculateBottom
    private var mSlideEdge = Gravity.BOTTOM

    private fun captureValues(transitionValues: TransitionValues) {
        val view = transitionValues.view
        val position = IntArray(2)
        view.getLocationOnScreen(position)
        transitionValues.values[PROPNAME_SCREEN_POSITION] = position
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        super.captureStartValues(transitionValues)
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        super.captureEndValues(transitionValues)
        captureValues(transitionValues)
    }

    fun setFraction(fraction: Float) {
        mfraction = fraction
        mSlideCalculator = CalculateSlideFraction(mSlideCalculator, fraction)
    }

    /**
     * Change the edge that Views appear and disappear from.
     *
     * @param slideEdge The edge of the scene to use for Views appearing and disappearing. One of
     * [android.view.Gravity.LEFT], [android.view.Gravity.TOP],
     * [android.view.Gravity.RIGHT], [android.view.Gravity.BOTTOM],
     * [android.view.Gravity.START], [android.view.Gravity.END].
     */
    fun setSlideEdge(@GravityFlag slideEdge: Int) {
        mSlideCalculator = when (slideEdge) {
            Gravity.LEFT -> sCalculateLeft
            Gravity.TOP -> sCalculateTop
            Gravity.RIGHT -> sCalculateRight
            Gravity.BOTTOM -> sCalculateBottom
            Gravity.START -> sCalculateStart
            Gravity.END -> sCalculateEnd
            else -> throw IllegalArgumentException("Invalid slide direction")
        }
        mSlideCalculator = CalculateSlideFraction(mSlideCalculator, mfraction)
        mSlideEdge = slideEdge
        val propagation = SidePropagation()
        propagation.setSide(slideEdge)
        setPropagation(propagation)
    }

    /**
     * Returns the edge that Views appear and disappear from.
     *
     * @return the edge of the scene to use for Views appearing and disappearing. One of
     * [android.view.Gravity.LEFT], [android.view.Gravity.TOP],
     * [android.view.Gravity.RIGHT], [android.view.Gravity.BOTTOM],
     * [android.view.Gravity.START], [android.view.Gravity.END].
     */
    @GravityFlag
    fun getSlideEdge(): Int {
        return mSlideEdge
    }

    override fun onAppear(
        sceneRoot: ViewGroup, view: View,
        startValues: TransitionValues?, endValues: TransitionValues?
    ): Animator? {
        if (endValues == null) {
            return null
        }
        val position = endValues.values[PROPNAME_SCREEN_POSITION] as IntArray?
        val endX = view.translationX
        val endY = view.translationY
        val startX: Float = mSlideCalculator.getGoneX(sceneRoot, view)
        val startY: Float = mSlideCalculator.getGoneY(sceneRoot, view)
        return TranslationAnimationCreator
            .createAnimation(
                view, endValues, position!![0], position[1],
                startX, startY, endX, endY, sDecelerate, this
            )
    }

    override fun onDisappear(
        sceneRoot: ViewGroup, view: View,
        startValues: TransitionValues?, endValues: TransitionValues?
    ): Animator? {
        if (startValues == null) {
            return null
        }
        val position = startValues.values[PROPNAME_SCREEN_POSITION] as IntArray?
        val startX = view.translationX
        val startY = view.translationY
        val endX: Float = mSlideCalculator.getGoneX(sceneRoot, view)
        val endY: Float = mSlideCalculator.getGoneY(sceneRoot, view)
        return TranslationAnimationCreator
            .createAnimation(
                view, startValues, position!![0], position[1],
                startX, startY, endX, endY, sAccelerate, this
            )
    }

}
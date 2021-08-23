package com.vrise.bazaar.newpages.utilities

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView


internal class Progress(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val mProgressImage: ImageView
    private var mProgressAnimation: TranslateAnimation? = null

    init {

        mProgressImage = ImageView(getContext())
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        mProgressImage.layoutParams = layoutParams
        addView(mProgressImage)
    }

    fun setBackgroundAsTile(tileImageResId: Int) {
        val tileBitmap = BitmapFactory.decodeResource(resources, tileImageResId)
        val tileRepeatedBitmap = BitmapDrawable(resources, tileBitmap)
        tileRepeatedBitmap.tileModeX = Shader.TileMode.REPEAT
        initAnimation(tileBitmap.width)
        mProgressImage.background = tileRepeatedBitmap
    }

    private fun initAnimation(tileImageWidth: Int) {
        val layoutParams = mProgressImage.layoutParams as FrameLayout.LayoutParams
        layoutParams.setMargins(-tileImageWidth, 0, 0, 0)

        // *HACK* tileImageWidth-3 is used because of *lags*(slow pause) in the moment of animation END-RESTART.
        mProgressAnimation = TranslateAnimation(0f, (tileImageWidth - 3).toFloat(), 0f, 0f)
        mProgressAnimation!!.interpolator = LinearInterpolator()
        mProgressAnimation!!.duration = 1000
        mProgressAnimation!!.repeatCount = Animation.INFINITE
    }

    fun startAnimation() {
        mProgressImage.startAnimation(mProgressAnimation)
    }

    fun stopAnimation() {
        mProgressImage.clearAnimation()
    }
}
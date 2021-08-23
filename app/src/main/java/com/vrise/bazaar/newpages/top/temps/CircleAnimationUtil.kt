package com.vrise.bazaar.newpages.top.temps

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import java.lang.ref.WeakReference


class CircleAnimationUtil {
    private var mTarget: View? = null
    private var mDest: View? = null

    private var originX: Float = 0.toFloat()
    private var originY: Float = 0.toFloat()
    private var destX: Float = 0.toFloat()
    private var destY: Float = 0.toFloat()

    private var mCircleDuration = DEFAULT_DURATION_DISAPPEAR / 2
    private var mMoveDuration = DEFAULT_DURATION
    private val mDisappearDuration =
        DEFAULT_DURATION_DISAPPEAR

    private var mContextReference: WeakReference<Activity>? = null
    private var mBorderWidth = 4
    private var mBorderColor = Color.BLACK
    private var mBitmap: Bitmap? = null
    private var mImageView: ImageView? = null
    private var mAnimationListener: Animator.AnimatorListener? = null

    private val avatarRevealAnimator: AnimatorSet
        get() {
            val endRadius = /*Math.max(destX, destY) / 2*/    10F
            val startRadius = /*Math.max(originX, originY)*/ 10F

            val mRevealAnimator = ObjectAnimator.ofFloat(mImageView, "drawableRadius", startRadius, endRadius * 1.05f, endRadius * 0.9f, endRadius)
            mRevealAnimator.interpolator = AccelerateInterpolator()
            val scaleFactor = 0.5f
            val scaleAnimatorY = ObjectAnimator.ofFloat(mImageView, View.SCALE_Y, 1F, 1F, scaleFactor, scaleFactor)
            val scaleAnimatorX = ObjectAnimator.ofFloat(mImageView, View.SCALE_X, 1F, 1F, scaleFactor, scaleFactor)

            val animatorCircleSet = AnimatorSet()
            animatorCircleSet.duration = mCircleDuration.toLong()
            animatorCircleSet.playTogether(scaleAnimatorX, scaleAnimatorY, mRevealAnimator)
            animatorCircleSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    if (mAnimationListener != null)
                        mAnimationListener?.onAnimationStart(animation)
                }

                override fun onAnimationEnd(animation: Animator) {
                    val src = IntArray(2)
                    val dest = IntArray(2)
                    mImageView?.getLocationOnScreen(src)
                    mDest?.getLocationOnScreen(dest)

                    val y = mImageView?.y ?: 0F
                    val x = mImageView?.x ?: 0F
                    val translatorX = ObjectAnimator.ofFloat(mImageView, View.X, x, x + dest[0] - (src[0] + (originX * scaleFactor - 2f * endRadius * scaleFactor) / 2) + (0.5f * destX - scaleFactor * endRadius))
                    translatorX.interpolator = TimeInterpolator { input -> (-Math.pow((input - 1).toDouble(), 2.0) + 1f).toFloat() }
                    val translatorY = ObjectAnimator.ofFloat(mImageView, View.Y, y, y + dest[1] - (src[1] + (originY * scaleFactor - 2f * endRadius * scaleFactor) / 2) + (0.5f * destY - scaleFactor * endRadius))
                    translatorY.interpolator = LinearInterpolator()

                    val animatorMoveSet = AnimatorSet()
                    animatorMoveSet.playTogether(translatorX, translatorY)
                    animatorMoveSet.duration = mMoveDuration.toLong()

                    val animatorDisappearSet = AnimatorSet()
                    val disappearAnimatorY = ObjectAnimator.ofFloat(mImageView, View.SCALE_Y, scaleFactor, 0F)
                    val disappearAnimatorX = ObjectAnimator.ofFloat(mImageView, View.SCALE_X, scaleFactor, 0F)
                    animatorDisappearSet.duration = mDisappearDuration.toLong()
                    animatorDisappearSet.playTogether(disappearAnimatorX, disappearAnimatorY)


                    val total = AnimatorSet()
                    total.playSequentially(animatorMoveSet, animatorDisappearSet)
                    total.addListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {
                            if (mAnimationListener != null)
                                mAnimationListener?.onAnimationEnd(animation)
                            reset()
                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {

                        }
                    })
                    total.start()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })

            return animatorCircleSet
        }

    fun attachActivity(activity: Activity): CircleAnimationUtil {
        mContextReference = WeakReference<Activity>(activity)
        return this
    }

    fun setTargetView(view: View): CircleAnimationUtil {
        mTarget = view
        setOriginRect(mTarget?.width?.toFloat() ?: 0F, mTarget?.height?.toFloat() ?: 0F)
        return this
    }

    private fun setOriginRect(x: Float, y: Float): CircleAnimationUtil {
        originX = x
        originY = y
        return this
    }

    private fun setDestRect(x: Float, y: Float): CircleAnimationUtil {
        destX = x
        destY = y
        return this
    }

    fun setDestView(view: View): CircleAnimationUtil {
        mDest = view
        setDestRect(mDest?.width?.toFloat() ?: 0F, mDest?.width?.toFloat() ?: 0F)
        return this
    }

    fun setBorderWidth(width: Int): CircleAnimationUtil {
        mBorderWidth = width
        return this
    }

    fun setBorderColor(color: Int): CircleAnimationUtil {
        mBorderColor = color
        return this
    }

    fun setCircleDuration(duration: Int): CircleAnimationUtil {
        mCircleDuration = duration
        return this
    }

    fun setMoveDuration(duration: Int): CircleAnimationUtil {
        mMoveDuration = duration
        return this
    }

    private fun prepare(): Boolean {
        if (mContextReference?.get() != null) {
            val decoreView = mContextReference?.get()?.window?.decorView as ViewGroup

                mBitmap = drawViewToBitmap(mTarget, mTarget?.width, mTarget?.height)

            if (mImageView == null)
                mImageView = ImageView(mContextReference?.get())
                mImageView?.setImageBitmap(mBitmap)


            val src = IntArray(2)
            mTarget?.getLocationOnScreen(src)
            val params = FrameLayout.LayoutParams(mTarget?.width ?: 0, mTarget?.height ?: 0)
            params.setMargins(src[0], src[1], 0, 0)
            if (mImageView?.parent == null)
                decoreView.addView(mImageView, params)
        }
        return true
    }

    fun startAnimation() {

        if (prepare()) {
            mTarget?.visibility = View.INVISIBLE
            avatarRevealAnimator.start()
        }
    }

    private fun drawViewToBitmap(view: View?, width: Int?, height: Int?): Bitmap {
        val dest = Bitmap.createBitmap(width ?: 0, height ?: 0, Bitmap.Config.ARGB_8888)
        val drawable = BitmapDrawable(view?.resources, dest)
        val c = Canvas(dest)
        drawable.bounds = Rect(0, 0, width?: 0, height?: 0)
        drawable.draw(c)
        view?.draw(c)
        return dest
    }

    private fun reset() {
        mBitmap?.recycle()
        mBitmap = null
        if (mImageView?.parent != null)
            (mImageView?.parent as ViewGroup).removeView(mImageView)
        mImageView = null
    }

    fun setAnimationListener(listener: Animator.AnimatorListener): CircleAnimationUtil {
        mAnimationListener = listener
        return this
    }

    companion object {
        private val DEFAULT_DURATION = 100
        private val DEFAULT_DURATION_DISAPPEAR = 100
    }
}
package com.vrise.bazaar.ex.util.custom

/*
 * Copyright (c) 2017. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.graphics.Paint.Style
import android.graphics.drawable.ColorDrawable
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Property
import android.view.animation.OvershootInterpolator
import androidx.annotation.IntRange
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.stateful.ExtendableSavedState
import com.vrise.R

private val STATE_KEY = CounterFab::class.java.name + ".STATE"
private const val COUNT_STATE = "COUNT"

private const val NORMAL_MAX_COUNT = 99
private const val NORMAL_MAX_COUNT_TEXT = "99+"

private const val MINI_MAX_COUNT = 9
private const val MINI_MAX_COUNT_TEXT = "9+"

private const val TEXT_SIZE_DP = 11
private const val TEXT_PADDING_DP = 2
private val MASK_COLOR = Color.parseColor("#33000000") // Translucent black as mask color
private val ANIMATION_INTERPOLATOR = OvershootInterpolator()
private const val RIGHT_TOP_POSITION = 0

class CounterFab @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.floatingActionButtonStyle) : FloatingActionButton(context, attrs, defStyleAttr) {

	private val animationProperty = object : Property<CounterFab, Float>(Float::class.java, "animation") {

		override fun set(counterFab: CounterFab, value: Float) {
			animationFactor = value
			postInvalidateOnAnimation()
		}

		override fun get(counterFab: CounterFab): Float {
			return 0f
		}
	}

	private val textSize = TEXT_SIZE_DP * resources.displayMetrics.density
	private val textPadding = TEXT_PADDING_DP * resources.displayMetrics.density

	private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		style = Style.FILL
	}
	private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		style = Style.FILL
		color = MASK_COLOR
	}
	private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		style = Style.FILL_AND_STROKE
		textSize = this@CounterFab.textSize
		textAlign = Paint.Align.CENTER
		typeface = Typeface.SANS_SERIF
	}
	private val textBounds: Rect = run {
		val maxCountText = NORMAL_MAX_COUNT_TEXT
		val textBounds = Rect()
		textPaint.getTextBounds(maxCountText, 0, maxCountText.length, textBounds)
		textBounds
	}
	private val circleBounds = Rect()
	private val contentBounds = Rect()

	private val animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
	private var animationFactor = 1f
	private var animator = ObjectAnimator()
	private val isAnimating: Boolean
		get() = animator.isRunning
	private val isSizeMini: Boolean
		get() = size == SIZE_MINI
	private val badgePosition: Int
	private var countText: String = ""

	var count: Int = 0
		set(@IntRange(from = 0) value) {
			if (value == field) return
			field = if (value > 0) value else 0

			updateCountText()
			if (ViewCompat.isLaidOut(this)) {
				startAnimation()
			}
		}

	init {
		val styledAttributes = context.theme.obtainStyledAttributes(
			attrs, R.styleable.CounterFab, 0, 0
		)
		textPaint.color = styledAttributes.getColor(R.styleable.CounterFab_badgeTextColor, Color.WHITE)
		circlePaint.color = styledAttributes.getColor(R.styleable.CounterFab_badgeBackgroundColor, getDefaultBadgeColor())
		badgePosition = 0
		styledAttributes.recycle()

		updateCountText()
	}

	private fun updateCountText() {
		countText = if (isSizeMini) when {
			count > MINI_MAX_COUNT -> MINI_MAX_COUNT_TEXT
			else -> count.toString()
		}
		else when {
			count > NORMAL_MAX_COUNT -> NORMAL_MAX_COUNT_TEXT
			else -> count.toString()
		}
	}

	private fun getDefaultBadgeColor(): Int = run {
		val colorStateList = backgroundTintList
		if (colorStateList != null) {
			colorStateList.defaultColor
		} else {
			val background = background
			if (background is ColorDrawable) {
				background.color
			} else {
				circlePaint.color
			}
		}
	}

	private fun startAnimation() {
		var start = 0f
		var end = 1f
		if (count == 0) {
			start = 1f
			end = 0f
		}

		if (isAnimating) animator.cancel()

		animator = ObjectAnimator.ofObject(
			this, animationProperty, null, start, end
		).apply {
			interpolator = ANIMATION_INTERPOLATOR
			duration = animationDuration.toLong()
			start()
		}
	}

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		calculateCircleBounds()
	}

	private fun calculateCircleBounds() {
		val circleRadius = Math.max(textBounds.width(), textBounds.height()) / 2f + textPadding
		val circleEnd = (circleRadius * 2).toInt()
		if (isSizeMini) {
			val circleStart = (circleRadius / 2).toInt()
			circleBounds.set(circleStart, circleStart, circleEnd, circleEnd)
		} else {
			val circleStart = 0
			circleBounds.set(circleStart, circleStart, (circleRadius * 2).toInt(), (circleRadius * 2).toInt())
		}
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		if (count > 0 || isAnimating) {
			if (getContentRect(contentBounds)) {
				val newLeft: Int
				val newTop: Int
				when (badgePosition) {
					RIGHT_TOP_POSITION -> {
						newLeft = contentBounds.left + contentBounds.width() - circleBounds.width()
						newTop = contentBounds.top
					}
					else -> {
						newLeft = contentBounds.left + contentBounds.width() - circleBounds.width()
						newTop = contentBounds.top
					}
				}
				circleBounds.offsetTo(newLeft, newTop)
			}
			val cx = circleBounds.centerX().toFloat()
			val cy = circleBounds.centerY().toFloat()
			val radius = circleBounds.width() / 2f * animationFactor
			// Solid circle
			canvas.drawCircle(cx, cy, radius, circlePaint)
			// Mask circle
			canvas.drawCircle(cx, cy, radius, maskPaint)
			// Count text
			textPaint.textSize = textSize * animationFactor
			canvas.drawText(countText, cx, cy + textBounds.height() / 2f, textPaint)
		}
	}

	override fun onSaveInstanceState(): Parcelable? {
		val superState = super.onSaveInstanceState()
		if (superState is ExtendableSavedState) {
			superState.extendableStates.put(STATE_KEY, bundleOf(COUNT_STATE to count))
		}
		return superState
	}

	override fun onRestoreInstanceState(state: Parcelable?) {
		super.onRestoreInstanceState(state)
		if (state !is ExtendableSavedState) return

		val bundle = state.extendableStates.get(STATE_KEY)
		count = bundle?.getInt(COUNT_STATE) ?: 0

		requestLayout()
	}
}
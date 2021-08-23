package com.vrise.bazaar.newpages.top.sliderui.adapter.indicatordesign

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout

import java.util.ArrayList

import com.vrise.R
import com.vrise.bazaar.newpages.top.sliderui.Interfaces

class SlideIndicatorsGroup(contexts: Context, private val selectedSlideIndicator: Drawable?, private val unselectedSlideIndicator: Drawable?, private val defaultIndicator: Int, private val indicatorSize: Int, mustAnimateIndicators: Boolean) : LinearLayout(contexts), Interfaces.OnSlideChangeListener {

    private var mustAnimateIndicator = true
    private val indicatorShapes = ArrayList<IndicatorShape>()

    init {
        this.mustAnimateIndicator = mustAnimateIndicators
        setup()
    }

    fun setSlides(slidesCount: Int) {
        removeAllViews()
        indicatorShapes.clear()
        for (i in 0 until slidesCount) {
            onSlideAdd()
        }
    }

    fun onSlideAdd() {
        addIndicator()
    }

    private fun addIndicator() {
        val indicatorShape: IndicatorShape
        if (selectedSlideIndicator != null && unselectedSlideIndicator != null) {
            indicatorShape = object : IndicatorShape(context, indicatorSize, mustAnimateIndicator) {
                override fun onCheckedChange(isChecked: Boolean) {
                    super.onCheckedChange(isChecked)
                    background = if (isChecked) {
                        when {
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN -> selectedSlideIndicator
                            else -> selectedSlideIndicator
                        }
                    } else {
                        when {
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN -> unselectedSlideIndicator
                            else -> unselectedSlideIndicator
                        }
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                indicatorShape.setBackground(unselectedSlideIndicator)
            } else {
                indicatorShape.setBackground(unselectedSlideIndicator)
            }
            indicatorShapes.add(indicatorShape)
            addView(indicatorShape)

        } else {
            when (defaultIndicator) {
                IndicatorShape.CIRCLE -> {
                    indicatorShape = CircleIndicator(
                        context,
                        indicatorSize,
                        mustAnimateIndicator
                    )
                    indicatorShapes.add(indicatorShape)
                    addView(indicatorShape)
                }
                else -> {
                }
            }
        }
    }

    fun setup() {
        orientation = LinearLayout.HORIZONTAL
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, indicatorSize * 2)
        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        val margin = resources.getDimensionPixelSize(R.dimen.indicator_margins) * 2
        layoutParams.setMargins(0, 0, 0, margin)
        setLayoutParams(layoutParams)
    }

    override fun onSlideChange(selectedSlidePosition: Int) {
        for (i in indicatorShapes.indices) {
            if (i == selectedSlidePosition) {
                indicatorShapes[i].onCheckedChange(true)
            } else {
                indicatorShapes[i].onCheckedChange(false)
            }
        }
    }

}

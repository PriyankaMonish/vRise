package com.vrise.bazaar.newpages.top.sliderui.adapter.indicatordesign

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

import androidx.core.content.res.ResourcesCompat
import com.vrise.R

class CircleIndicator(context: Context, indicatorSize: Int, mustAnimateChanges: Boolean) : IndicatorShape(context, indicatorSize, mustAnimateChanges) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = ResourcesCompat.getDrawable(resources, R.drawable.indicator_circle_unselected, null)
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.indicator_circle_unselected)
        }
    }

    override fun onCheckedChange(isChecked: Boolean) {
        super.onCheckedChange(isChecked)
        background = if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ResourcesCompat.getDrawable(resources, R.drawable.indicator_circle_selected, null)
            } else {
                ContextCompat.getDrawable(context, R.drawable.indicator_circle_selected)
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ResourcesCompat.getDrawable(resources, R.drawable.indicator_circle_unselected, null)
            } else {
                ContextCompat.getDrawable(context, R.drawable.indicator_circle_unselected)
            }
        }
    }
}

/*
 * Copyright (C) 2013 Leszek Mzyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vrise.bazaar.newpages.top.sliderui.adapter

import android.content.Context
import android.util.AttributeSet

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class LooperWrapViewPager : ViewPager {
    internal var mOuterPageChangeListener: ViewPager.OnPageChangeListener? = null
    private var mAdapter: LoopPagerAdapterWrapper? = null
    private val mBoundaryCaching = DEFAULT_BOUNDARY_CASHING

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        private var mPreviousOffset = -1f
        private var mPreviousPosition = -1f

        override fun onPageSelected(position: Int) {

            val realPosition = mAdapter?.toRealPosition(position)
            if (mPreviousPosition != realPosition?.toFloat()) {
                mPreviousPosition = realPosition?.toFloat() ?: 0F
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener?.onPageSelected(realPosition ?: 0)
                }
            }
        }

        override fun onPageScrolled(
            position: Int, positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            var realPosition = position
            mAdapter?.let {
                realPosition = it.toRealPosition(position) ?: 0

                if (positionOffset == 0f
                    && mPreviousOffset == 0f
                    && (position == 0 || position == it.count - 1)
                ) {
                    setCurrentItem(realPosition, false)
                }
            }

            mPreviousOffset = positionOffset
            if (mOuterPageChangeListener != null) {
                if (realPosition != mAdapter!!.realCount - 1) {
                    mOuterPageChangeListener?.onPageScrolled(realPosition, positionOffset, positionOffsetPixels)
                } else {
                    if (positionOffset > .5) {
                        mOuterPageChangeListener?.onPageScrolled(0, 0f, 0)
                    } else {
                        mOuterPageChangeListener?.onPageScrolled(
                            realPosition,
                            0f, 0
                        )
                    }
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            mAdapter?.let {
                val position = super@LooperWrapViewPager.getCurrentItem()
                val realPosition = it.toRealPosition(position)
                if (state == ViewPager.SCROLL_STATE_IDLE && (position == 0 || position == it.count - 1)) {
                    setCurrentItem(realPosition, false)
                }
            }
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener?.onPageScrollStateChanged(state)
            }
        }
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        mAdapter = LoopPagerAdapterWrapper(adapter!!)
        mAdapter?.setBoundaryCaching(mBoundaryCaching)
        super.setAdapter(mAdapter)
        setCurrentItem(0, false)
    }

    override fun getAdapter(): PagerAdapter? {
        return if (mAdapter != null) mAdapter?.realAdapter else mAdapter
    }

    override fun getCurrentItem(): Int {
        return mAdapter?.toRealPosition(super.getCurrentItem()) ?: 0
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        mAdapter?.let {
            val realItem = it.toInnerPosition(item)
            if (it.count > 1)
                super.setCurrentItem(realItem, smoothScroll)
        }

    }

    override fun setCurrentItem(item: Int) {
        if (currentItem != item && mAdapter!!.count > 1) {
            setCurrentItem(item, true)
        }
    }

    override fun addOnPageChangeListener(listener: OnPageChangeListener) {
        mOuterPageChangeListener = listener
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        super.addOnPageChangeListener(onPageChangeListener)
    }

    companion object {
        private val DEFAULT_BOUNDARY_CASHING = false
    }

}

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

import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class LoopPagerAdapterWrapper(val realAdapter: PagerAdapter) : PagerAdapter() {

    private var mToDestroy = SparseArray<ToDestroy>()

    private var mBoundaryCaching: Boolean = false

    private val realFirstPosition: Int
        get() = 1

    private val realLastPosition: Int
        get() = realFirstPosition + realCount - 1

    val realCount: Int
        get() = realAdapter.count

    fun setBoundaryCaching(flag: Boolean) {
        mBoundaryCaching = flag
    }

    override fun notifyDataSetChanged() {
        mToDestroy = SparseArray()
        super.notifyDataSetChanged()
    }

    fun toRealPosition(position: Int): Int {
        val realCount = realCount
        if (realCount == 0)
            return 0
        var realPosition = (position - 1) % realCount
        if (realPosition < 0)
            realPosition += realCount

        return realPosition
    }

    fun toInnerPosition(realPosition: Int): Int {
        return realPosition + 1
    }

    override fun getCount(): Int {
        return if (realAdapter.count == 1) {
            1
        } else {
            realAdapter.count + 2
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val realPosition = if (realAdapter is FragmentPagerAdapter || realAdapter is FragmentStatePagerAdapter)
            position
        else
            toRealPosition(position)

        if (mBoundaryCaching) {
            val toDestroy = mToDestroy.get(position)
            if (toDestroy != null) {
                mToDestroy.remove(position)
                return toDestroy.`object`
            }
        }
        return realAdapter.instantiateItem(container, realPosition)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val realFirst = realFirstPosition
        val realLast = realLastPosition
        val realPosition = if (realAdapter is FragmentPagerAdapter || realAdapter is FragmentStatePagerAdapter)
            position
        else
            toRealPosition(position)

        if (mBoundaryCaching && (position == realFirst || position == realLast)) {
            mToDestroy.put(
                position, ToDestroy(
                    container, realPosition,
                    `object`
                )
            )
        } else {
            realAdapter.destroyItem(container, realPosition, `object`)
        }
    }

    override fun finishUpdate(container: ViewGroup) {
        realAdapter.finishUpdate(container)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return realAdapter.isViewFromObject(view, `object`)
    }

    override fun restoreState(bundle: Parcelable?, classLoader: ClassLoader?) {
        realAdapter.restoreState(bundle, classLoader)
    }

    override fun saveState(): Parcelable? {
        return realAdapter.saveState()
    }

    override fun startUpdate(container: ViewGroup) {
        realAdapter.startUpdate(container)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        realAdapter.setPrimaryItem(container, position, `object`)
    }

    internal class ToDestroy(var container: ViewGroup, var position: Int, var `object`: Any)

}
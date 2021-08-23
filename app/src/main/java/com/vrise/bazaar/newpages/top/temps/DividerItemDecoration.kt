package com.vrise.bazaar.newpages.top.temps

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView


class DividerItemDecoration(private val divider: Drawable?, val orientation: Int, val spanCount: Int) : RecyclerView.ItemDecoration() {

    val HORIZONTAL = LinearLayout.HORIZONTAL
    val VERTICAL = LinearLayout.VERTICAL

    private val mBounds = Rect()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.layoutManager == null || divider == null) {
            return
        }

        if (orientation == VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }

    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        c.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            c.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            //multiple of spanCount == 0
            /*For Testing
            for (int i = 0; i <= 30; i++){
	            System.out.println("i "+ i%10);
	        }*/
            if ((i % spanCount == spanCount - 1)) {
            } else {
                parent.layoutManager?.getDecoratedBoundsWithMargins(child, mBounds)
                val right = mBounds.right + Math.round(child.translationX)
                val left = right - (divider?.intrinsicWidth ?: 0)
                divider?.setBounds(left, top, right, bottom)
                divider?.draw(c)
            }
        }
        c.restore()
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        c.save()
        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            c.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }

        val childCount: Int = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            if (i > ((childCount - 1) - spanCount)) {
            } else {
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom = mBounds.bottom + Math.round(child.translationY)
                val top = bottom - (divider?.intrinsicHeight ?: 0)
                divider?.setBounds(left, top, right, bottom)
                divider?.draw(c)
            }
        }
        c.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == state.itemCount) {
            outRect.setEmpty()
            return
        }

        if (orientation == VERTICAL) {
            outRect.set(0, 0, 0, (divider?.intrinsicHeight ?: 0))
        } else if (orientation == HORIZONTAL) {
            outRect.set(0, 0, (divider?.intrinsicWidth ?: 0), 0)
        }

    }

}
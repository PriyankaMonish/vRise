package com.vrise.bazaar.newpages.top.sliderui.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.viewpager.widget.ViewPager
import com.vrise.R
import com.vrise.bazaar.newpages.top.sliderui.adapter.indicatordesign.IndicatorShape
import com.vrise.bazaar.newpages.top.sliderui.adapter.LooperWrapViewPager
import com.vrise.bazaar.newpages.top.sliderui.model.Slide
import com.vrise.bazaar.newpages.top.sliderui.adapter.indicatordesign.SlideIndicatorsGroup
import com.vrise.bazaar.newpages.top.sliderui.adapter.SliderAdapter
import java.util.Random

class NewSlider : FrameLayout, ViewPager.OnPageChangeListener {

    private var viewPager: LooperWrapViewPager? = null
    private var itemClickListener: AdapterView.OnItemClickListener? = null

    private var selectedSlideIndicator: Drawable? = null
    private var unSelectedSlideIndicator: Drawable? = null

    private var defaultIndicator: Int = 0

    private var indicatorSize: Int = 0

    private var mustAnimateIndicators: Boolean = false
    private var mustLoopSlides: Boolean = false

    private var slideIndicatorsGroup: SlideIndicatorsGroup? = null
    private var slideShowInterval = 5000

    val handle = Handler()

    private var slideCount: Int = 0
    private var currentPageNumber: Int = 0
    private var hideIndicators = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        parse(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        parse(attrs)
    }

    private fun parse(attrs: AttributeSet?) {
        try {
            if (attrs != null) {
                val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewSlider)
                try {
                    indicatorSize = typedArray.getDimensionPixelSize(
                        R.styleable.NewSlider_indicatorSize,
                        resources.getDimensionPixelSize(R.dimen.size_of_indicator)
                    )
                    selectedSlideIndicator = typedArray.getDrawable(R.styleable.NewSlider_selected)
                    unSelectedSlideIndicator =
                            typedArray.getDrawable(R.styleable.NewSlider_unselected)
                    defaultIndicator =
                            typedArray.getInt(R.styleable.NewSlider_defaultIndicators,
                                IndicatorShape.CIRCLE
                            )
                    mustAnimateIndicators = typedArray.getBoolean(R.styleable.NewSlider_animateIndicators, true)
                    mustLoopSlides = typedArray.getBoolean(R.styleable.NewSlider_loopSlide, false)
                    hideIndicators = typedArray.getBoolean(R.styleable.NewSlider_hideIndicator, false)
                    val slideShowIntervalSecond = typedArray.getInt(R.styleable.NewSlider_intervalSecond, 5)
                    slideShowInterval = slideShowIntervalSecond * 1000

                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    typedArray.recycle()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun addSlides(slideList: ArrayList<Slide>?) {
        if (slideList == null || slideList.size == 0)
            return

        viewPager = LooperWrapViewPager(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewPager?.id = View.generateViewId()
        } else {
            val id = Math.abs(Random().nextInt(5000 - 1000 + 1) + 1000)
            viewPager?.id = id
        }
        viewPager?.layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewPager?.addOnPageChangeListener(this@NewSlider)
        addView(viewPager)
        val adapter = SliderAdapter(context, slideList, AdapterView.OnItemClickListener { adapterView, view, i, l ->
            if (itemClickListener != null)
                itemClickListener?.onItemClick(adapterView, view, i, l)
        })
        viewPager?.adapter = adapter
        slideCount = slideList.size
        viewPager?.currentItem = slideCount - 1
        if (!hideIndicators && slideCount > 1) {
            slideIndicatorsGroup = SlideIndicatorsGroup(
                context,
                selectedSlideIndicator,
                unSelectedSlideIndicator,
                defaultIndicator,
                indicatorSize,
                mustAnimateIndicators
            )
            addView(slideIndicatorsGroup)
            slideIndicatorsGroup?.setSlides(slideCount)
            slideIndicatorsGroup?.onSlideChange(slideCount - 1)
        }
        if (slideCount > 1)
            setupTimer()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        currentPageNumber = position
        if (slideIndicatorsGroup != null && !hideIndicators) {
            if (position == 0) {
                slideIndicatorsGroup?.onSlideChange(slideCount - 1)
            } else if (position == slideCount + 1) {
                slideIndicatorsGroup?.onSlideChange(0)
            } else {
                slideIndicatorsGroup?.onSlideChange(position - 1)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        when (state) {
            ViewPager.SCROLL_STATE_DRAGGING -> handle.removeCallbacksAndMessages(null)
            ViewPager.SCROLL_STATE_IDLE -> setupTimer()
        }
    }

    private fun setupTimer() {
        try {
            if (mustLoopSlides) {
                handle.postDelayed(object : Runnable {
                    override fun run() {
                        try {
                            if (currentPageNumber < slideCount)
                                currentPageNumber += 1
                            else
                                currentPageNumber = 1

                            viewPager?.setCurrentItem(currentPageNumber - 1, true)

                            handle.removeCallbacksAndMessages(null)
                            handle.postDelayed(this, slideShowInterval.toLong())

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }, slideShowInterval.toLong())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setItemClickListener(itemClickListener: AdapterView.OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}

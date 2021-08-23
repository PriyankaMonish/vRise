package com.vrise.bazaar.newpages.top.sliderui.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.vrise.bazaar.newpages.top.sliderui.model.Slide
import com.vrise.bazaar.newpages.top.temps.Misc.blurTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.*


class SliderAdapter(
    context: Context,
    items: ArrayList<Slide>,
    private val itemClickListener: AdapterView.OnItemClickListener?
) : PagerAdapter() {

    private val layoutInflater: LayoutInflater
    private var items = ArrayList<Slide>()

    init {
        this.items = items
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(com.vrise.R.layout.row_slider, container, false)
        val sliderImage = view.findViewById<View>(com.vrise.R.id.sliderImage) as ImageView
        loadImage(sliderImage, items[position].imageUrl)
        val parent = view.findViewById<View>(com.vrise.R.id.ripple)
        parent.setOnClickListener {
            itemClickListener?.onItemClick(null, null, position, 0)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var objects: Any? = `object`
        // Removes the page from the container for the given position. We simply removed object using removeView()
        // but could’ve also used removeViewAt() by passing it the position.
        try {
            // Remove the view from the container
            container.removeView(objects as View)
            // Invalidate the object
            objects = null
        } catch (e: Exception) {
            Log.w("NewSlider", "destroyItem: failed to destroy item and clear it's used resources", e)
        }

    }


    private fun loadImage(imageView: ImageView, url: String) {
        if (!TextUtils.isEmpty(url)) {
            /*    Glide.with(imageView.context)
                    .load(url)
                    .bitmapTransform(CenterCrop(imageView.context))
                    .animate(com.ibrbazaar.R.anim.fade_in)
                    .into(imageView)

            */
            /**/
            // image url goes here
            /*.resize(imageViewWidth, imageViewHeight)*/
            /*.resize(imageView.getMeasuredWidth(), imageView.getMeasuredHeight())*/

            Picasso.get()
                .load(url)
                .placeholder(com.vrise.R.drawable.ic_logo_1)
                .transform(blurTransformation(imageView.context))
                .fit()
                .into(imageView, object : Callback {
                    override fun onError(e: java.lang.Exception) {

                    }

                    override fun onSuccess() {
                        Picasso.get()
                            .load(url)
                            .placeholder(imageView.drawable)
                            .fit()
                            .into(imageView)
                    }
                })


        }
    }
}

package com.vrise.bazaar.ex.util.custom

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.shape.CornerFamily
import com.vrise.R
import com.vrise.bazaar.ex.model.submodels.BannerItem
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances.showBlur
import com.vrise.bazaar.ex.util.Instances.zoomImage


class SliderAdapter(
    private var activity: FragmentActivity?,
    private val imageList: ArrayList<BannerItem>?
) :
    PagerAdapter() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = LayoutInflater.from(activity).inflate(
            R.layout.item_custome_slider_view,
            container,
            false
        )
        val imageView = imageLayout.findViewById(R.id.image) as ImageView
        if (imageList != null) {
            if (imageList.isNotEmpty()) {
                showBlur(activity, imageList[position].image.toString(), imageView)
                imageView.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {
                        zoomImage(activity, imageList[position].image.toString())
                    }
                })
            }
        }
       imageView.clipToOutline = true
        container.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imageList?.size ?: 0
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
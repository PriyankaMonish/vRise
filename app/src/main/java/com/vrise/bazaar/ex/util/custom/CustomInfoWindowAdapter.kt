package com.vrise.bazaar.ex.util.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.vrise.R

class CustomInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

    private var customView: View = LayoutInflater.from(context).inflate(R.layout.custominfowindow, null)

    override fun getInfoContents(p0: Marker?): View? {
        return setValues(p0, customView)
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return setValues(p0, customView)
    }

    private fun setValues(marker: Marker?, view: View): View? {
        marker?.let {
            if (!it.title.isNullOrBlank()) {
                view.findViewById<TextView?>(R.id.text1)?.text = it.title
            }
        }
        return view
    }

}

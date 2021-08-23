package com.vrise.bazaar.newpages.utilities

import androidx.fragment.app.FragmentActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.models.Requests

class EmptyList(val activity: FragmentActivity?, val data: Requests?) : RecyclerView.Adapter<EmptyList.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.empty_recyclerview, p0, false))
    }

    override fun getItemCount(): Int {
        when {
            data != null -> return 1
            else -> return 0
        }
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if (data != null) {
            if (data.id != null) {
                activity?.let {
                    p0.imageView.setImageDrawable(ContextCompat.getDrawable(activity, data.id.toInt()))
                }
            }
            if (data.display != null) {
                p0.textView.text = data.display.toString()
            }
        }
    }
}
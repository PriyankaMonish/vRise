package com.vrise.bazaar.newpages.agency.menus.newsubscriberrequest

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
import com.squareup.picasso.Picasso

//class ListOfSubscriber(val activity: Activity, val dataItem: List<SublistItem>) : RecyclerView.Adapter<ListOfSubscriber.ViewHolder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subcriber_new_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.imageView8.borderColor = (ContextCompat.getColor(activity, R.color.cb_errorRed))
//        Picasso.get().load(dataItem[p0.adapterPosition].imgLink.toString()).into(p0.imageView8)
//        p0.textView47.text = dataItem[p0.adapterPosition].name
//        p0.textView48.text = dataItem[p0.adapterPosition].subscriptions
//        p0.item.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItem[p0.adapterPosition].id)
//            navigateTo(activity as FragmentActivity, Constants.FRAGMENT, true, NewlyRequstedSubscribers(), null, bundle, null, "")
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView8 = itemView.findViewById<com.mikhaellopez.circularimageview.CircularImageView>(R.id.imageView8)
//        val textView47 = itemView.findViewById<TextView>(R.id.textView47)
//        val textView48 = itemView.findViewById<TextView>(R.id.textView48)
//        val imageView9 = itemView.findViewById<ImageView>(R.id.imageView9)
//        val item = itemView.findViewById<LinearLayout>(R.id.item)
//    }
//}
package com.vrise.bazaar.newpages.agency.dashboard.view

import android.content.Context
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.vrise.R
//import com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers.SubscriberList
import com.vrise.bazaar.newpages.utilities.ClickListener
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.models.submodels.RootlistItem

//class RouteList(val activity: Context, val dataItem : ArrayList<RootlistItem> ) : RecyclerView.Adapter<RouteList.ViewHolder>() {
//
//    private val viewBinderHelper = ViewBinderHelper()
//
//    init {
//        this.viewBinderHelper.setOpenOnlyOne(true)
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_route_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val dataObject = dataItem[position]
//        viewBinderHelper.bind(holder.swipeRevealLayout, dataObject.id)
//
//        holder.item.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, dataItem[holder.adapterPosition].id)
//                bundle.putString(Constants.SECOND, dataItem[holder.adapterPosition].name)
//                HelperMethods.navigateTo(activity as FragmentActivity, Constants.FRAGMENT, true, SubscriberList(), null, bundle, null, "")
//            }
//        })
//        holder.textView41.text = dataItem[holder.adapterPosition].name
//        holder.textView19.text = dataItem[holder.adapterPosition].area.toString()
//        if (dataItem[holder.adapterPosition].subCount != null) {
//            if (dataItem[holder.adapterPosition].subCount.toString().isNotBlank()) {
//                if (dataItem[holder.adapterPosition].subCount.toString().toDouble() > 0) {
//                    holder.textView162.visibility = View.VISIBLE
//                    holder.textView162.text = dataItem[holder.adapterPosition].subCount.toString()
//                } else {
//                    holder.textView162.visibility = View.GONE
//
//                }
//            }
//        }
//
//        if (dataItem[holder.adapterPosition].request_count != null) {
//            if (dataItem[holder.adapterPosition].request_count!!.isNotBlank()) {
//                if (dataItem[holder.adapterPosition].request_count.toString().toDouble() > 0) {
//                    holder.textView42.visibility = View.VISIBLE
//                    holder.textView42.text = dataItem[holder.adapterPosition].request_count.toString()
//                } else {
//                    holder.textView42.visibility = View.GONE
//                }
//            }
//        }
//
//        holder.imageView16.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putSerializable(Constants.A_DATAS, dataItem[holder.adapterPosition])
//            bundle.putString(Constants.FROM, "update")
//            navigateTo(activity as FragmentActivity, Constants.FRAGMENT, true, AddRoute(), null, bundle, null, "")
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)
//        val textView162 = itemView.findViewById<TextView>(R.id.textView162)
//        val textView41 = itemView.findViewById<TextView>(R.id.textView41)
//        val textView42 = itemView.findViewById<TextView>(R.id.textView42)
//        val textView19 = itemView.findViewById<TextView>(R.id.textView19)
//        val item = itemView.findViewById<ConstraintLayout>(R.id.item)
//        val imageView16 = itemView.findViewById<ImageView>(R.id.imageView16)
//    }
//}
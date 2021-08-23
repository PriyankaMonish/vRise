package com.vrise.bazaar.newpages.agency.menus.todo.due.routes

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vrise.R

import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.models.submodels.DuelistItem

//class DueList(val activity: FragmentActivity, val dataItems: ArrayList<DuelistItem>) : RecyclerView.Adapter<DueList.ViewHolder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_route_as_dues, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView70.text = dataItems[p0.adapterPosition].name
//        p0.textView71.text = "₹ " + dataItems[p0.adapterPosition].due
//        p0.textView16.text = dataItems[p0.adapterPosition].area
//        p0.item.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItems[p0.adapterPosition].id.toString())
//            HelperMethods.navigateTo(activity, Constants.FRAGMENT, true, DueSubs(), null, bundle, null, "")
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView70 = itemView.findViewById<TextView>(R.id.textView70)
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView16 = itemView.findViewById<TextView>(R.id.textView16)
//        val item = itemView.findViewById<ConstraintLayout>(R.id.item)
//    }
//
//}
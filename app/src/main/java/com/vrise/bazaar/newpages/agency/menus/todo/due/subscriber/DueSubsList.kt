package com.vrise.bazaar.newpages.agency.menus.todo.due.subscriber

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vrise.R
//import com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails.SubscriberDetails
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.models.submodels.SubduelistItem
import com.squareup.picasso.Picasso

//class DueSubsList(val activity: FragmentActivity, val dataItems: ArrayList<SubduelistItem>) : RecyclerView.Adapter<DueSubsList.ViewHolder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_due_subs, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView70.text = dataItems[p0.adapterPosition].name
//        Picasso.get().load(dataItems[p0.adapterPosition].imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(p0.imageView13)
//        p0.textView71.text = dataItems[p0.adapterPosition].mobile
//        p0.textView72.text = dataItems[p0.adapterPosition].code
//        p0.textView74.text = "₹ ${dataItems[p0.adapterPosition].due}"
//        p0.item.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItems[p0.adapterPosition].id)
//            HelperMethods.navigateTo(activity, Constants.FRAGMENT, true, SubscriberDetails(), null, bundle, null, "")
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val item = itemView.findViewById<ConstraintLayout>(R.id.item)
//        val textView70 = itemView.findViewById<TextView>(R.id.textView70)
//        val imageView13 = itemView.findViewById<ImageView>(R.id.imageView13)
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//        val imageView14 = itemView.findViewById<ImageView>(R.id.imageView14)
//        val textView73 = itemView.findViewById<TextView>(R.id.textView73)
//        val textView74 = itemView.findViewById<TextView>(R.id.textView74)
//
//        init {
//            imageView14.visibility = View.GONE
//        }
//    }
//
//}
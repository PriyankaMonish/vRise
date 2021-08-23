package com.vrise.bazaar.newpages.subscriber.addproducts

//import android.app.Activity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem

//class SubscriptionList(val activity: Activity, val dataItem: ArrayList<SubscriptionsItem>?, val invokeObserve: Interfaces.ReturnId) : RecyclerView.Adapter<SubscriptionList.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_multi_subscription_list_new, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.close.visibility = View.VISIBLE
//        p0.textView63.text = dataItem!![p0.adapterPosition].name
//        p0.close.setOnClickListener {
//            if (dataItem[p0.adapterPosition] != null) {
//                print("Before" + dataItem.size)
//                dataItem.removeAt(p0.adapterPosition)
//                print("After" + dataItem.size)
//                invokeObserve.returnId(0.toString())
///*
//                notifyItemChanged(p0.adapterPosition)
//                notifyDataSetChanged()
//*/
//            }
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView63 = itemView.findViewById<TextView>(R.id.textView61)
//        val close = itemView.findViewById<ImageView>(R.id.close)
//    }
//}
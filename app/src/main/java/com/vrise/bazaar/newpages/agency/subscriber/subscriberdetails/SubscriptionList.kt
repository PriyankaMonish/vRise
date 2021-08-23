package com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails

//import android.app.Activity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.vrise.R
//import com.vrise.bazaar.ex.retrofit.RetroClient
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem
//import com.squareup.picasso.Picasso

//class SubscriptionList(val activity: Activity, val dataItem: List<SubscriptionsItem>?) : RecyclerView.Adapter<SubscriptionList.ViewHolder>() {
//    private var dlink = ""
//
//    init {
//        dlink = "${RetroClient.getInstance(activity).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subscription_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        if (dataItem!![p0.adapterPosition].imgLink.toString() != dlink) {
//            p0.textView63.text = ""
//            p0.imageView63.visibility = View.VISIBLE
//            Picasso.get().load(dataItem[p0.adapterPosition].imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(p0.imageView63)
//        } else {
//            p0.imageView63.visibility = View.GONE
//            p0.textView63.text = dataItem[p0.adapterPosition].name
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView63 = itemView.findViewById<TextView>(R.id.textView61)
//        val imageView63 = itemView.findViewById<ImageView>(R.id.imageView61)
//    }
//}
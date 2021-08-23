package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions

//import android.app.Activity
//import androidx.cardview.widget.CardView
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.ex.retrofit.RetroClient
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem
//import com.squareup.picasso.Picasso

//class SubscriptionList(val activity: Activity, val dataItem: ArrayList<SubscriptionsItem>, val wholeitem: ArrayList<PrdlistItem?>?, val observer: Interfaces.ReturnSubscriptionsItem, val param: Interfaces.ReturnPrdlistItem) : RecyclerView.Adapter<SubscriptionList.ViewHolder>() {
//    private var dlink = ""
//
//    init {
//        dlink = "${RetroClient.getInstance(activity).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_already_image_subs, p0, false)) //item_multi_subscription_list
//    }
//
//    override fun getItemCount(): Int {
//        return wholeitem!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//
//        p0.close.visibility = View.VISIBLE
//        if (wholeitem!![p0.adapterPosition]?.imgLink.toString() != dlink) {
//            p0.textView63.text = ""
//            p0.imageView63.visibility = View.VISIBLE
//            Picasso.get().load(wholeitem[p0.adapterPosition]?.imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(p0.imageView63)
//        } else {
//            p0.imageView63.visibility = View.INVISIBLE
//            p0.textView63.text = wholeitem[p0.adapterPosition]!!.name
//        }
//
//        p0.close.setOnClickListener {
//            remoVeItems(wholeitem, p0)
//        }
//        p0.cardView4.setOnClickListener {
//            remoVeItems(wholeitem, p0)
//        }
//    }
//
//    private fun remoVeItems(wholeitem: ArrayList<PrdlistItem?>, p0: ViewHolder) {
//        if (wholeitem[p0.adapterPosition] != null) {
//            /*observer.removeData(dataItem[p0.adapterPosition])
//            print("Before $dataItem")
//            dataItem.removeAt(p0.adapterPosition)
//            print(" After $dataItem")
//            */
//            param.removeData(wholeitem[p0.adapterPosition])
//            print("Before ${wholeitem}")
//            wholeitem.removeAt(p0.adapterPosition)
//            print("After ${wholeitem}")
//            notifyItemChanged(p0.adapterPosition)
//            notifyDataSetChanged()
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView63 = itemView.findViewById<TextView>(R.id.textView61)
//        val cardView4 = itemView.findViewById<CardView>(R.id.cardView4)
//        val imageView63 = itemView.findViewById<ImageView>(R.id.imageView61)
//        val close = itemView.findViewById<ImageView>(R.id.close)
//    }
//}
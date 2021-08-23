package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions

//import androidx.fragment.app.FragmentActivity
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
//import com.squareup.picasso.Picasso

//class SubscribedList(val activity: FragmentActivity, val dataItems: ArrayList<PrdlistItem?>, val observer: Interfaces.ReturnPrdlistItem) : RecyclerView.Adapter<SubscribedList.ViewHolder>() {
//
//    private var dlink = ""
//
//    init {
//        dlink = "${RetroClient.getInstance(activity).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_already_image_subs, p0, false))/*return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_already_subs, p0, false))*/
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.close.visibility = View.VISIBLE
//        if (dataItems[p0.adapterPosition]?.imgLink.toString() != dlink) {
//            p0.textView63.text = ""
//            p0.imageView63.visibility = View.VISIBLE
//            Picasso.get().load(dataItems[p0.adapterPosition]?.imgLink.toString()).placeholder(R.drawable.ic_placeholder).into(p0.imageView63)
//        } else {
//            p0.imageView63.visibility = View.INVISIBLE
//            p0.textView63.text = dataItems[p0.adapterPosition]!!.name
//        }
//
//        p0.close.setOnClickListener {
//            removeItem(p0, dataItems,observer)
//        }
//
//        p0.cardView4.setOnClickListener{
//            removeItem(p0, dataItems,observer)
//        }
//    }
//
//    private fun removeItem(p0: ViewHolder, dataItems: ArrayList<PrdlistItem?>, observer: Interfaces.ReturnPrdlistItem) {
//        if (dataItems[p0.adapterPosition] != null) {
//            observer.removeData(dataItems[p0.adapterPosition])
//            print("Before $dataItems")
//            dataItems.removeAt(p0.adapterPosition)
//            print(" After $dataItems")
//            notifyItemChanged(p0.adapterPosition)
//            notifyDataSetChanged()
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView63 = itemView.findViewById<TextView>(R.id.textView61)
//        val imageView63 = itemView.findViewById<ImageView>(R.id.imageView61)
//        val close = itemView.findViewById<ImageView>(R.id.close)
//        val cardView4 = itemView.findViewById<CardView>(R.id.cardView4)
//    }
//
//}

package com.vrise.bazaar.newpages.agency.todo.bills

//import android.app.Activity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.models.submodels.BillProductsItem

//class SubItemList(val activity: Activity, val dataItem: List<BillProductsItem?>?) : RecyclerView.Adapter<SubItemList.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.subitem_bills, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView70.text = dataItem!![p0.adapterPosition]!!.productName
//        p0.textView71.text = dataItem[p0.adapterPosition]!!.type
//        p0.textView72.text = "₹ ${dataItem[p0.adapterPosition]!!.totalAmount}"
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView70 = itemView.findViewById<TextView>(R.id.textView70)
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//    }
//
//}
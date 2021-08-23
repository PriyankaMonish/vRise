package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.models.submodels.BillProductsItem

class NewList(val billProducts: List<BillProductsItem?>?) : RecyclerView.Adapter<NewList.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_amount, p0, false))
    }

    override fun getItemCount(): Int {
        return billProducts!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.ctitle.text = billProducts!![p0.adapterPosition]!!.productName
        p0.cmonth.text = billProducts[p0.adapterPosition]!!.type
        p0.camount.text = "₹ ${billProducts[p0.adapterPosition]!!.totalAmount}"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ctitle = itemView.findViewById<TextView>(R.id.title)
        val cmonth = itemView.findViewById<TextView>(R.id.month)
        val camount = itemView.findViewById<TextView>(R.id.amount)
    }

}
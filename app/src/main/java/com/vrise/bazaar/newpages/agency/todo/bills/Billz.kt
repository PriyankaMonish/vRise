package com.vrise.bazaar.newpages.agency.todo.bills

//import android.os.Bundle
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.todo.BillEditing
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.HelperMethods.changeDateFormat
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem

//class Billz(val activity: FragmentActivity, val dataItem: List<BilllistItem?>) : RecyclerView.Adapter<Billz.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_bills, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView70.text = dataItem[p0.adapterPosition]!!.subscriberName
//        p0.textView71.text = changeDateFormat("yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy", dataItem[p0.adapterPosition]!!.billDate.toString())
//        p0.textView72.text = dataItem[p0.adapterPosition]!!.billCode
//        p0.textView74.text = "₹ ${dataItem[p0.adapterPosition]?.totalAmount}"
//
//        if (dataItem[p0.adapterPosition]?.billProducts != null) {
//            if (dataItem[p0.adapterPosition]?.billProducts!!.isNotEmpty()) {
//                p0.recyclerView10.adapter = SubItemList(activity, dataItem[p0.adapterPosition]!!.billProducts)
//                p0.recyclerView10.layoutManager = LinearLayoutManager(activity)
//            } else {
//                p0.recyclerView10.visibility = View.GONE
//            }
//        } else {
//            p0.recyclerView10.visibility = View.GONE
//        }
//
//        if (dataItem[p0.adapterPosition]!!.paymentStatus == "1") {
//            p0.textView75.text = "Paid"
//            p0.imageView17.visibility = View.GONE
//            p0.textView76.visibility = View.GONE
//        } else {
//            p0.imageView17.visibility = View.VISIBLE
//            p0.textView76.visibility = View.VISIBLE
//            p0.textView75.text = "Not Paid"
//        }
//        p0.textView76.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putSerializable(Constants.ID, dataItem[p0.adapterPosition])
//            navigateTo(activity, Constants.FRAGMENT, true, BillEditing(), null, bundle, null, "")
//        }
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView70 = itemView.findViewById<TextView>(R.id.textView70)
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//        val recyclerView10 = itemView.findViewById<RecyclerView>(R.id.recyclerView10)
//        val textView74 = itemView.findViewById<TextView>(R.id.textView74)
//        val textView75 = itemView.findViewById<TextView>(R.id.textView75)
//        val textView76 = itemView.findViewById<TextView>(R.id.textView76)
//        val imageView17 = itemView.findViewById<ImageView>(R.id.imageView17)
//    }
//}
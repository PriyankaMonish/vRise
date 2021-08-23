package com.vrise.bazaar.newpages.agency.menus.todo.reminder

//import android.os.Bundle
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.agency.menus.todo.newlyadded.NewlyAdded
//import com.vrise.bazaar.newpages.utilities.ClickListener
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.models.submodels.ReminderRoutesItem

//class RouteList(val activity: FragmentActivity, val dataItem: List<ReminderRoutesItem>?) : RecyclerView.Adapter<RouteList.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_remainder_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView71.text = dataItem!![p0.adapterPosition].name
//        p0.textView20.text = dataItem[p0.adapterPosition].area
//
//        if (dataItem[p0.adapterPosition].newaddd != null) {
//            if (dataItem[p0.adapterPosition].newaddd!!.isNotBlank()) {
//                if (dataItem[p0.adapterPosition].newaddd.toString().toDouble() > 0) {
//                    p0.textView72.text = dataItem[p0.adapterPosition].newaddd
//                    p0.textView72.visibility = View.VISIBLE
//                } else {
//                    p0.textView72.visibility = View.GONE
//                }
//            }
//        }
//
//        p0.item.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                val bundle = Bundle()
//                bundle.putString(Constants.ID, dataItem[p0.adapterPosition].id)
//                navigateTo(activity, Constants.FRAGMENT, true, NewlyAdded(), null, bundle, null, "")
//            }
//        })
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//        val textView20 = itemView.findViewById<TextView>(R.id.textView20)
//        val item = itemView.findViewById<ConstraintLayout>(R.id.item)
//    }
//}
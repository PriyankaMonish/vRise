package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import com.vrise.R

import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
import com.vrise.bazaar.newpages.utilities.Constants.Upi

import com.vrise.bazaar.newpages.utilities.HelperMethods.changeDateFormat
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.models.submodels.BilllistItem
import com.payu.india.Payu.PayuConstants
import kotlinx.android.synthetic.main.settl.view.*

class BillingList(val activity: FragmentActivity, val dataItem: List<BilllistItem?>?,
                  val dues: String) : RecyclerView.Adapter<BillingList.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subscriber_bill,
            p0, false))
    }

    override fun getItemCount(): Int {
        return dataItem?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            dataItem?.let {
//                holder.companyName?.text = it[position]?.subscriberName
                holder.date?.text = changeDateFormat("yyyy-MM-dd HH:mm:ss",
                    "dd-MM-yyyy", it[position]?.billDate.toString())
                holder.code?.text = it[position]?.billCode
                holder.dueam?.text = "₹ ${it[position]?.dueAmount.toString()}"
                holder.amount?.text = "₹ ${it[position]?.totalAmount}"
                holder.recyclerView16?.layoutManager = LinearLayoutManager(activity)
                if (it[position]?.billProducts?.isEmpty()!!) {

                } else {
                    holder.recyclerView16?.adapter = NewList(it[position]?.billProducts)
                }
                if (it[holder.adapterPosition]?.paymentStatus == "1") {
                    holder.pays?.text = "Paid"
                } else {
                    holder.pays?.text = "Pay Now"
                    holder.pays?.setOnClickListener { click ->
                        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.settl, null)
                        val mBuilder = AlertDialog.Builder(ContextThemeWrapper(activity,
                            R.style.AlertDialogTheme))
                                .setView(mDialogView)
                                .setTitle("Pay Now")

                        val mAlertDialog = mBuilder.show()
                        if (!it[position]?.totalAmount.isNullOrBlank()) {
                            mDialogView.textView66.text = dues
                        }

                        mDialogView.textView166.setOnClickListener {
                            if (mDialogView.textView68.text.toString().isNotBlank()) {
      if (mDialogView.textView68.text.toString().toDouble() > 0 && mDialogView.textView68.text.toString().toDouble() <= 2000) {
            if (mDialogView.textView68.text.toString().toDouble() <= mDialogView.textView66.text.toString().toDouble()) {

                                        mAlertDialog.dismiss()
                                        val bundle = Bundle()
                                        bundle.putString(Upi, dataItem.get(0)?.agent_upi_id.toString())
                                        bundle.putString(PayuConstants.AMOUNT,
                                            mDialogView.textView68?.text.toString())
                  navigateTo(activity, FRAGMENT,true,BankFragment(),
                      null,bundle,null,"")
//
                                 bundle.putString(PayuConstants.AMOUNT, mDialogView.textView68?.text.toString())
//                                        bundle.putString(PayuConstants.PRODUCT_INFO, holder.companyName?.text.toString())
//                                        bundle.putString(PayuConstants.FIRST_NAME, dataItem[position]?.subscriberName.toString())
//                                        bundle.putString(PayuConstants.LASTNAME, dataItem[position]?.agentName.toString())
//                                     navigateTo(activity, FRAGMENT, true, BankFragment(), null, bundle, null, "")
                                   }


                                    else {
                                        mDialogView.textView68.error = "Given amount is more than due amount"
                                    }
                                } else {
                                    mDialogView.textView68.error = "Enter amount between 1 & 2000"
                                }
                            } else {
                                mDialogView.textView68.error = "Enter amount > 0"
                            }
                        }
                        mDialogView.textView167.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pays = itemView.findViewById<Button?>(R.id.pays)
        val date = itemView.findViewById<TextView?>(R.id.date)
        val code = itemView.findViewById<TextView?>(R.id.code)
        val amount = itemView.findViewById<TextView?>(R.id.amount)
        val dueam = itemView.findViewById<TextView?>(R.id.dueam)
//        val companyName = itemView.findViewById<TextView?>(R.id.companyName)
        val recyclerView16 = itemView.findViewById<RecyclerView?>(R.id.recyclerView16)
    }
}
package com.vrise.bazaar.newpages.subscriber.addproducts

//import android.app.Activity
//import android.content.DialogInterface
//import androidx.fragment.app.FragmentActivity
//import androidx.core.content.ContextCompat
//import androidx.appcompat.app.AlertDialog
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.retrofit.ApiService
//import com.vrise.bazaar.newpages.utilities.ConsAndroidCallback
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.vrise.bazaar.newpages.utilities.models.submodels.ProductListItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionsItem

//class LiscubLisee(val activity: Activity, val item: ArrayList<ProductListItem?>?, val apiService: ApiService?) : RecyclerView.Adapter<LiscubLisee.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_already_subs, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return item?.size ?: 0
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.close.visibility = View.VISIBLE
//        p0.textView63.text = item!![p0.adapterPosition]!!.name
//        p0.close.setOnClickListener {
//            if (item[p0.adapterPosition] != null) {
//                notifyItemChanged(p0.adapterPosition)
//                notifyDataSetChanged()
//            }
//        }
//
//
//        if (item[p0.adapterPosition]!!.removeRequest == 1.toString()) {
//            p0.close.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_autorenew_black_24dp))
//            p0.close.setOnClickListener { openPopUp(item[p0.adapterPosition]) }
//        } else {
//            p0.close.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.ic_close_red))
//            p0.close.setOnClickListener {
//
//                val alertDialog = AlertDialog.Builder(androidx.appcompat.view.ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//                        .setTitle("Unsubscribe from ${item[p0.adapterPosition]!!.name}")
//                        .setMessage("Do you want to send a request to unsubscribe this product")
//                        .setCancelable(true)
//                        .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
//                            override fun onClick(p01: DialogInterface?, p1: Int) {
//                                val subscriptionsItem = ArrayList<SubscriptionsItem>()
//                                subscriptionsItem.add(SubscriptionsItem(id = item[p0.adapterPosition]!!.id))
//                                print(Request(
//                                        utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                        productList = subscriptionsItem
//                                ))
//                                apiService?.productremove(Request(
//                                        utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                        productList = subscriptionsItem
//                                ))?.enqueue(object : ConsAndroidCallback(activity as FragmentActivity, null, object : Interfaces.Callback {
//                                    override fun additionalData(display: String?, logout: Boolean) {
//
//                                    }
//
//                                    override fun success(response: Response?, data: Data?, state: Boolean) {
//                                        if (state) {
//                                            item[p0.adapterPosition]!!.removeRequest = 1.toString()
//                                            notifyDataSetChanged()
//                                        } else {
//
//                                        }
//                                    }
//
//                                    override fun failed(t: Throwable) {
//                                        t.printStackTrace()
//                                    }
//                                }) {})
//                            }
//                        })
//                        .show()
//                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).width = LinearLayout.LayoutParams.MATCH_PARENT
//
//            }
//        }
//    }
//
//    private fun openPopUp(productListItem: ProductListItem?) {
//        val alertDialog = AlertDialog.Builder(androidx.appcompat.view.ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//                .setTitle("Processing Request...")
//                .setMessage("Your Request to unsubscribe from ${productListItem!!.name} is already under processing")
//                .setCancelable(true)
//                .setPositiveButton("Ok") { p01, p1 -> p01!!.dismiss() }
//                .show()
//
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).width = LinearLayout.LayoutParams.MATCH_PARENT
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView63 = itemView.findViewById<TextView>(R.id.textView61)
//        val close = itemView.findViewById<ImageView>(R.id.close)
//
//    }
//}
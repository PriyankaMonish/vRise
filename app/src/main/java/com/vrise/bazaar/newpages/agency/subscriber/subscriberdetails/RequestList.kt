package com.vrise.bazaar.newpages.agency.subscriber.subscriberdetails

//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.FragmentActivity
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.TextView
//import androidx.appcompat.view.ContextThemeWrapper
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.ex.retrofit.RetroClient
//import com.vrise.bazaar.newpages.retrofit.ApiService
//import com.vrise.bazaar.newpages.utilities.ConsAndroidCallback
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.vrise.bazaar.newpages.utilities.models.submodels.SubscriptionRequestItem
//import com.squareup.picasso.Picasso
//import retrofit2.Call
//import retrofit2.Callback
//
//class RequestList(
//    val activity: FragmentActivity,
//    val dataItem: ArrayList<SubscriptionRequestItem?>?,
//    val subscriber: String,
//    val observer: Interfaces.ReturnId,
//    val apiService: ApiService?
//) : RecyclerView.Adapter<RequestList.ViewHolder>() {
//
//    private var dlink = ""
//
//    init {
//        dlink = "${RetroClient.getInstance(activity).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_requests_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem?.size ?: 0
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        if (dataItem != null) {
//            if (dataItem[p0.adapterPosition]?.imgLink.toString() == dlink) {
//                p0.textView61.text = dataItem[p0.adapterPosition]?.name
//                p0.imageView63.visibility = View.GONE
//            } else {
//                p0.imageView63.visibility = View.VISIBLE
//                p0.textView61.text = ""
//                Picasso.get().load(dataItem[p0.adapterPosition]?.imgLink.toString()).into(p0.imageView63)
//            }
//            if (dataItem[p0.adapterPosition]?.requestType == "remove") {
//                p0.textView21.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent))
//                p0.textView21.text = "Please remove \"${dataItem[p0.adapterPosition]?.name}\""
//                p0.textView63.setOnClickListener {
//                    p0.progressBar.visibility = View.VISIBLE
//                    alert(p0, dataItem[p0.adapterPosition], "accept", "remove", "noneed")
//                }
//                p0.textView62.setOnClickListener {
//                    p0.progressBar.visibility = View.VISIBLE
//                    alert(p0, dataItem[p0.adapterPosition], "reject", "remove", "noneed")
//                }
//            } else {
//                p0.textView21.setTextColor(ContextCompat.getColor(activity, R.color.green_drawer))
//                p0.textView21.text = "Please add \"${dataItem[p0.adapterPosition]?.name}\""
//                p0.textView62.visibility = View.VISIBLE
//                p0.textView63.setOnClickListener {
//                    p0.progressBar.visibility = View.VISIBLE
//                    alert(p0, dataItem[p0.adapterPosition], "accept", "", "update")
//                }
//                p0.textView62.setOnClickListener {
//                    alert(p0, dataItem[p0.adapterPosition], "reject", "", "update")
//                }
//            }
//        }
//    }
//
//    fun alert(
//        p0: ViewHolder,
//        subscriptionRequestItem: SubscriptionRequestItem?,
//        status: String,
//        requestType: String,
//        towhere: String
//    ) {
//        AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//            .setMessage(
//                "Do you want to $status the request to" +
//                        when (towhere) {
//                            "update" -> " add ${subscriptionRequestItem?.name} ?"
//                            "noneed" -> " remove ${subscriptionRequestItem?.name} ?"
//                            else -> " add/remove ${subscriptionRequestItem?.name} ?"
//                        }
//            )
//            .setCancelable(false)
//            .setNegativeButton("No") { p01, p1 ->
//                p01?.dismiss()
//                p0.progressBar.visibility = View.GONE
//            }
//            .setPositiveButton("Yes") { p01, p1 ->
//                if (towhere == "noneed") {
//                    noneed(p0, subscriptionRequestItem, status, requestType)
//                } else if (towhere == "update") {
//                    update(p0, status, subscriptionRequestItem)
//                }
//                p01?.dismiss()
//            }
//            .show()
//    }
//
//    private fun noneed(p0: ViewHolder, data: SubscriptionRequestItem?, status: String?, requestType: String) {
//        apiService?.processproductreq(
//            Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                id = data?.id,
//                product_id = data?.productId,
//                subscriberId = subscriber,
//                string = status,
//                requestType = requestType
//            )
//        )?.enqueue(object : ConsAndroidCallback(activity, null, object : Interfaces.Callback {
//            override fun additionalData(display: String?, logout: Boolean) {
//
//            }
//
//            override fun success(response: Response?, data: Data?, state: Boolean) {
//                if (state) {
//                    p0.progressBar.visibility = View.GONE
//                    dataItem?.removeAt(p0.adapterPosition)
//                    notifyItemRemoved(p0.adapterPosition)
//                    notifyDataSetChanged()
//                } else {
//                    p0.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun failed(t: Throwable) {
//                p0.progressBar.visibility = View.GONE
//                t.printStackTrace()
//            }
//        }) {})
//    }
//
//    private fun update(s: ViewHolder, s1: String, subscriptionRequestItem: SubscriptionRequestItem?) {
//        print(
//            GsonBuilder().setPrettyPrinting().create().toJson(
//                Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                    id = subscriptionRequestItem?.id,
//                    product_id = subscriptionRequestItem?.productId,
//                    subscriberId = subscriber,
//                    string = s1,
//                    requestType = subscriptionRequestItem?.requestType
//                )
//            )
//        )
//        apiService?.processproductreq(
//            Request(
//                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                id = subscriptionRequestItem?.id,
//                product_id = subscriptionRequestItem?.productId,
//                subscriberId = subscriber,
//                string = s1,
//                requestType = subscriptionRequestItem?.requestType
//            )
//        )?.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                s.progressBar.visibility = View.GONE
//                t?.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                s.progressBar.visibility = View.GONE
//                if (hasData(activity, response)) {
//                    if (s1 == "accept") {
//                        observer.returnId(s.adapterPosition.toString())
//                    }
//                    dataItem?.removeAt(s.adapterPosition)
//                    notifyItemRemoved(s.adapterPosition)
//                    notifyDataSetChanged()
//                }
//            }
//        })
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView61 = itemView.findViewById<TextView>(R.id.textView61)
//        val imageView63 = itemView.findViewById<ImageView>(R.id.imageView63)
//        val textView21 = itemView.findViewById<TextView>(R.id.textView21)
//        val textView62 = itemView.findViewById<TextView>(R.id.textView62)
//        val textView63 = itemView.findViewById<TextView>(R.id.textView63)
//        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
//    }
//}
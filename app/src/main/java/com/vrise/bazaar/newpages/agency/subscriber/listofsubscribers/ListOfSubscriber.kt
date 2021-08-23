package com.vrise.bazaar.newpages.agency.subscriber.listofsubscribers

import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.vrise.R

import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.utilities.ConsAndroidCallback
import com.vrise.bazaar.newpages.utilities.Constants
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.SublistItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback


//class ListOfSubscriber(
//    val activity: FragmentActivity,
//    val dataItem: ArrayList<SublistItem>,
//    val apiService: ApiService?
//) : RecyclerView.Adapter<ListOfSubscriber.ViewHolder>() {
//    private val viewBinderHelper = ViewBinderHelper()
//
//    init {
//        this.viewBinderHelper.setOpenOnlyOne(true)
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subcriber_list, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItem.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//
//        val dataObject = dataItem[p1]
//        viewBinderHelper.bind(p0.swipeRevealLayout, dataObject.id)
//
//        p0.item.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItem[p0.adapterPosition].id)
//            HelperMethods.navigateTo(activity, Constants.FRAGMENT, true, SubscriberDetails(), null, bundle, null, "")
//        }
//
//        p0.image_edit.setOnClickListener {
//            p0.progress2.visibility = View.VISIBLE
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItem[p0.adapterPosition].id)
//            bundle.putString(Constants.FROM, 0.toString())
//            navigateTo(activity, Constants.FRAGMENT, true, SubscribersDetail(), null, bundle, null, "")
//            try {
//                p0.progress2.visibility = View.GONE
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        Picasso.get().load(dataItem[p0.adapterPosition].imgLink.toString()).placeholder(R.drawable.ic_placeholder)
//            .into(p0.imageView8)
//
//        p0.imageView8.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.ID, dataItem[p0.adapterPosition].id)
//            bundle.putString(Constants.FROM, 1.toString())
//            navigateTo(activity, Constants.FRAGMENT, true, SubscribersDetail(), null, bundle, null, "")
//        }
//
//        if (dataItem[p0.adapterPosition].deviceIds.isNullOrBlank()) {
//            p0.imageView8.borderColor = ContextCompat.getColor(activity, R.color.cb_errorRed)
//        } else {
//            p0.imageView8.borderColor = ContextCompat.getColor(activity, R.color.bright_turquoise)
//        }
//
//        p0.textView48.text = dataItem[p0.adapterPosition].code
//        p0.textView47.text = dataItem[p0.adapterPosition].name
//        p0.textView49.text = dataItem[p0.adapterPosition].mobile
//        p0.textView200.text = dataItem[p0.adapterPosition].subscriptions.toString()
//        p0.imageView16.setOnClickListener {
//            p0.progressBar11.visibility = View.VISIBLE
//            apiService?.getAgentDashBoard(
//                Request(
//                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID)
//                )
//            )?.enqueue(object : Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    p0.progressBar11.visibility = View.INVISIBLE
//                    t!!.printStackTrace()
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (HelperMethods.hasData(activity, response)) {
//                        if (response!!.body()!!.data != null) {
//                            if (response.body()!!.data!!.rootlist != null) {
//                                if (response.body()!!.data!!.rootlist!!.isNotEmpty()) {
//                                    var array: ArrayList<Request>? = ArrayList()
//                                    for (i in 0 until response.body()!!.data!!.rootlist!!.size) {
//                                        array?.add(
//                                            Request(
//                                                name = response.body()!!.data!!.rootlist!![i].name,
//                                                code = response.body()!!.data!!.rootlist!![i].id
//                                            )
//                                        )
//                                    }
//                                    array =
//                                        array?.filter { `is` -> `is`.code.toString().toLowerCase() != dataItem[p0.adapterPosition].routeId.toString().toLowerCase() } as ArrayList<Request>
//
//                                    if (array.isNotEmpty()) {
//                                        val dialog = Dialog(activity)
//                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                                        dialog.setCancelable(true)
//                                        dialog.setContentView(R.layout.custom_list_dialog)
//                                        val alertRecyclerView = dialog.findViewById<RecyclerView>(R.id.recyclerView18)
//                                        val needtext = dialog.findViewById<TextView>(R.id.needtext)
//                                        needtext.visibility = View.VISIBLE
//                                        needtext.text = "Move to.."
//                                        alertRecyclerView.layoutManager =
//                                            LinearLayoutManager(activity)
//                                        val adapter = HelperMethods.CustomAdapter(
//                                            activity,
//                                            array,
//                                            dialog,
//                                            object : Interfaces.ClickedItem {
//                                                override fun returnIdValue(
//                                                    clickPosPosition: String,
//                                                    clickPosvalue: String
//                                                ) {
//                                                    apiService?.changeRoute(
//                                                        Request(
//                                                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
//                                                                activity,
//                                                                DATAFILE,
//                                                                Constants.ID
//                                                            ),
//                                                            routeId = clickPosPosition,
//                                                            subscriberId = dataItem[p0.adapterPosition].id
//                                                        )
//                                                    )?.enqueue(object : Callback<Response> {
//                                                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                                            if (activity != null) {
//                                                                t!!.printStackTrace()
//                                                            }
//                                                        }
//
//                                                        override fun onResponse(
//                                                            call: Call<Response>?,
//                                                            response: retrofit2.Response<Response>?
//                                                        ) {
//                                                            if (hasData(activity, response)) {
//                                                                dialog.dismiss()
//                                                                dataItem.removeAt(p0.adapterPosition)
//                                                                notifyDataSetChanged()
//                                                            }
//                                                        }
//                                                    })
//                                                }
//                                            })
//                                        alertRecyclerView.adapter = adapter
//                                        adapter.notifyDataSetChanged()
//                                        dialog.show()
//                                    } else {
//                                        toastit(activity, "No other routes available")
//                                    }
//
//                                }
//                            }
//                        }
//                    }
//                    p0.progressBar11.visibility = View.INVISIBLE
//                }
//            })
//
//        }
//        p0.imageView166.setOnClickListener {
//            p0.progressBar111.visibility = View.VISIBLE
//
//            val builder: AlertDialog.Builder
//            builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//            } else {
//                AlertDialog.Builder(activity)
//            }
//            builder
//                .setCancelable(false)
//                .setTitle("Remove Subscriber")
//                .setMessage("Are you sure you want to remove this subscriber?")
//                .setPositiveButton(android.R.string.yes) { dialog, which ->
//                    apiService?.subscriberdelete(
//                        Request(
//                            utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
//                                activity,
//                                DATAFILE,
//                                Constants.ID
//                            ),
//                            subscriberId = dataItem[p0.adapterPosition].id
//                        )
//                    )?.enqueue(object : ConsAndroidCallback(activity, null, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//                                dialog.dismiss()
//                                p0.progressBar111.visibility = View.INVISIBLE
//                                dataItem.removeAt(p0.adapterPosition)
//                                notifyDataSetChanged()
//                            } else {
//                                dialog.dismiss()
//                                p0.progressBar111.visibility = View.INVISIBLE
//                            }
//                        }
//
//                        override fun failed(t: Throwable) {
//                            dialog.dismiss()
//                            p0.progressBar111.visibility = View.INVISIBLE
//                            t.printStackTrace()
//                        }
//                    }) {})
//                }
//                .setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { dialog, which ->
//                    p0.progressBar111.visibility = View.INVISIBLE
//                    dialog.dismiss()
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show()
//
//        }
//    }
//
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val swipeRevealLayout = itemView.findViewById<SwipeRevealLayout>(R.id.swipeRevealLayout)
//        val imageView8 = itemView.findViewById<com.mikhaellopez.circularimageview.CircularImageView>(R.id.imageView8)
//        val textView47 = itemView.findViewById<TextView>(R.id.textView47)
//        val textView48 = itemView.findViewById<TextView>(R.id.textView48)
//        val imageView9 = itemView.findViewById<ImageView>(R.id.imageView9)
//        val item = itemView.findViewById<LinearLayout>(R.id.item)
//        val progressBar11 = itemView.findViewById<ProgressBar>(R.id.progressBar11)
//        val imageView16 = itemView.findViewById<ImageView>(R.id.imageView16)
//        val imageView166 = itemView.findViewById<ImageView>(R.id.imageView166)
//        val image_edit = itemView.findViewById<ImageView>(R.id.image_edit)
//        val progress2 = itemView.findViewById<ProgressBar>(R.id.progress2)
//        val progressBar111 = itemView.findViewById<ProgressBar>(R.id.progressBar111)
//        val textView49 = itemView.findViewById<TextView>(R.id.textView49)
//        val textView200 = itemView.findViewById<TextView>(R.id.textView200)
//    }
//}
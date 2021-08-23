//package com.vrise.bazaar.newpages.agency.menus.subscriberprice
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.app.Dialog
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.Window
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.TextView
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import com.chauthai.swipereveallayout.ViewBinderHelper
//import com.vrise.R
//import com.vrise.bazaar.ex.retrofit.RetroClient
//import com.vrise.bazaar.newpages.retrofit.ApiService
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.squareup.picasso.Picasso
//
//
//class SubscriberPriceData(val activity: FragmentActivity?, val dataItems: List<PrdlistItem?>, val invoke: Interfaces.Invoke,
//                          val apiService: ApiService?) : RecyclerView.Adapter<SubscriberPriceData.ViewHolder>() {
//
//    private val viewBinderHelper = ViewBinderHelper()
//    private var dlink = ""
//
//    init {
//        this.viewBinderHelper.setOpenOnlyOne(true)
//        dlink = "${RetroClient.getInstance(activity!!).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subscriber_price, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems.size
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//
//        val dataObject = dataItems[p1]
//        viewBinderHelper.bind(p0.swipeRevealLayout, dataObject!!.id)
//        if (dataItems[p0.adapterPosition]!!.imgLink != null) {
//            if (dataItems[p0.adapterPosition]!!.imgLink!!.isNotBlank()) {
//                if (!dataItems[p0.adapterPosition]!!.imgLink.equals(dlink)) {
//                    /*p0.imageView30.visibility = View.VISIBLE
//                    p0.textView88.visibility = View.GONE*/
//                    p0.textView88.text = ""
//                    Picasso.get().load(dataItems[p0.adapterPosition]!!.imgLink.toString()).into(p0.imageView30)
//                } else {
//                    /*
//                    p0.imageView30.visibility = View.GONE
//                    p0.textView88.visibility = View.VISIBLE*/
//                    Picasso.get().load(R.drawable.round_grey).into(p0.imageView30)
//                    p0.textView88.text = dataItems[p0.adapterPosition]!!.name
//                }
//            }
//        }
//        /*p0.textView88.text = dataItems[p0.adapterPosition]!!.name
//        p0.textView89.text = dataItems[p0.adapterPosition]!!.subscription*/
//        if (dataItems[p0.adapterPosition]!!.agentStatus == "1") {
//            p0.textView90.text = "₹" + dataItems[p0.adapterPosition]!!.agentPrice
//        } else {
//            p0.textView90.text = "₹" + dataItems[p0.adapterPosition]!!.price
//        }
//        p0.imageView16.setOnClickListener {
//            openPriceEditDialog(activity!!, p0.adapterPosition, dataItems)
//        }
//    }
//
//    private fun openPriceEditDialog(context: Activity, adapterPosition: Int, dataItemss: List<PrdlistItem?>) {
//        val dialog = Dialog(context)
//        dialog.setCancelable(true)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.item_edit_dialog)
//        context.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        val textView91 = dialog.findViewById<TextView>(R.id.textView91)
//        val imageView33 = dialog.findViewById<ImageView>(R.id.imageView33)
//        val progressBar = dialog.findViewById<ProgressBar>(R.id.progressBar)
//        val textView172 = dialog.findViewById<TextView>(R.id.textView172)
//
//        if (!dataItemss[adapterPosition]!!.imgLink.equals(dlink)) {
//            imageView33.visibility = View.VISIBLE
//            textView91.text = ""
//            textView172.text = dataItemss[adapterPosition]!!.name
//            Picasso.get().load(dataItemss[adapterPosition]!!.imgLink).into(imageView33)
//        } else {
//            textView91.text = dataItemss[adapterPosition]!!.name
//            textView172.text = ""
//            imageView33.visibility = View.GONE
//        }
//        val textView93 = dialog.findViewById<EditText>(R.id.textView93)
//
//        textView93.addTextChangedListener(DecimalInputTextWatcher(textView93, 2))
//
//        if (dataItemss[adapterPosition]!!.agentStatus == "1") {
//            textView93.setText(dataItemss[adapterPosition]!!.agentPrice)
//        } else {
//            textView93.setText(dataItemss[adapterPosition]!!.price)
//        }
//
//        textView93.setSelection(textView93.text.length)
//
//        val textView94 = dialog.findViewById<TextView>(R.id.textView94)
//        textView94.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                try {
//                    if (hasConnection(activity)) {
//                        progressBar.visibility = View.VISIBLE
//                        apiService?.addAgentProductPrice(Request(
//                                utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                product_id = dataItemss[adapterPosition]!!.id,
//                                agent_price = textView93.text.toString()
//                        ))?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                            override fun additionalData(display: String?, logout: Boolean) {
//
//                            }
//
//                            override fun failed(t: Throwable) {
//                                progressBar.visibility = View.GONE
//                                t.printStackTrace()
//                            }
//
//                            override fun success(response: Response?, data: Data?, state: Boolean) {
//                                if (state) {
//                                    progressBar.visibility = View.GONE
//                                    dialog.dismiss()
//                                    invoke.invokeMe()
//                                } else {
//                                    progressBar.visibility = View.GONE
//                                }
//                            }
//                        }) {})
//                    }
//                } catch (e: Exception) {
//                    progressBar.visibility = View.VISIBLE
//                    e.printStackTrace()
//                }
//            }
//        })
//        dialog.show()
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val swipeRevealLayout = itemView.findViewById<com.chauthai.swipereveallayout.SwipeRevealLayout>(R.id.swipeRevealLayout)
//        val textView88 = itemView.findViewById<TextView>(R.id.textView88)
//        val textView89 = itemView.findViewById<TextView>(R.id.textView89)
//        val textView90 = itemView.findViewById<TextView>(R.id.textView90)
//        val imageView30 = itemView.findViewById<ImageView>(R.id.imageView30)
//        val imageView16 = itemView.findViewById<ImageView>(R.id.imageView16)
//    }
//}
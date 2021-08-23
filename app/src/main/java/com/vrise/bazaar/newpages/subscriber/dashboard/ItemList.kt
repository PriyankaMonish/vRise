package com.vrise.bazaar.newpages.subscriber.dashboard

//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.Constants.MINUSONE
//import com.vrise.bazaar.newpages.utilities.Constants.ONE
//import com.vrise.bazaar.newpages.utilities.Constants.ZERO
//import com.vrise.bazaar.newpages.utilities.models.submodels.PendingListItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.ProductListItem
//import com.squareup.picasso.Picasso


//class ItemList(val activity: FragmentActivity?, val dataItems: ArrayList<Any?>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    class FViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView7 = itemView.findViewById<ImageView>(R.id.imageView7)
//        val textView26 = itemView.findViewById<TextView>(R.id.textView26)
//        val floatingActionButton3 = itemView.findViewById<ImageView>(R.id.floatingActionButton3)
//
//        init {
//            textView26.isSelected = true
//        }
//    }
//
//    class SViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView = itemView.findViewById<ImageView>(R.id.imageViews)
//        val textView = itemView.findViewById<TextView>(R.id.textViews)
//        val pending = itemView.findViewById<LinearLayout>(R.id.pending)
//
//        init {
//            textView.isSelected = true
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return when {
//            dataItems!![position] is ProductListItem -> ZERO
//            dataItems[position] is PendingListItem -> ONE
//            else -> MINUSONE
//        }
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
//        return when (p1) {
//            ZERO -> FViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_dash, p0, false))
//            ONE -> SViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_pending, p0, false))
//            else -> SViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_pending, p0, false))
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems?.size ?: 0
//    }
//
//    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
//        when (p0.itemViewType) {
//            ONE -> {
//                val p2 = p0 as SViewHolder
//                val products = dataItems!![p2.adapterPosition] as PendingListItem?
//                if (products != null) {
//                    p2.textView.text = products.name
//                    Picasso.get().load(products.imgLink.toString()).into(p2.imageView)
//                }
//                println(products.toString() + ONE.toString() + "," + p0.itemViewType + ":" + p2.adapterPosition)
//            }
//            ZERO -> {
//                val p4 = p0 as FViewHolder
//                val products = dataItems!![p4.adapterPosition] as ProductListItem?
//                if (products != null) {
//                    p4.textView26.text = products.name
//                    Picasso.get().load(products.imgLink.toString()).into(p4.imageView7)
//                    p4.floatingActionButton3.visibility = View.GONE
//                }
//                println(products.toString() + ZERO.toString() + "," + p0.itemViewType + ":" + p4.adapterPosition)
//            }
//            else -> {
//            }
//        }
//
//        /*
//        if (dataItems[p0.adapterPosition]!!.removeRequest == 1.toString()) {
//            p0.floatingActionButton3.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_autorenew_black_24dp))
//            p0.floatingActionButton3.setOnClickListener { openPopUp(dataItems[p0.adapterPosition]) }
//        } else {
//            p0.floatingActionButton3.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_close_red))
//            p0.floatingActionButton3.setOnClickListener {
//                val alertDialog = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//                        .setTitle("Unsubscribe from ${dataItems[p0.adapterPosition]!!.name}")
//                        .setMessage("Do you want to send a request to unsubscribe this product")
//                        .setCancelable(true)
//                        .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
//                            override fun onClick(p01: DialogInterface?, p1: Int) {
//                                val subscriptionsItem = ArrayList<SubscriptionsItem>()
//                                subscriptionsItem.add(SubscriptionsItem(id = dataItems[p0.adapterPosition]!!.id))
//                                print(Request(
//                                        utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                        productList = subscriptionsItem
//                                ))
//                                apiService?.productremove(Request(
//                                        utoken = Preference.getSinglePreference(activity, DATAFILE, Constants.ID),
//                                        productList = subscriptionsItem
//                                ))?.enqueue(object : ConsAndroidCallback(activity, object : Interfaces.Callback {
//                                    override fun success(response: Response?, data: Data?, state: Boolean) {
//                                        if (state) {
//                                            dataItems[p0.adapterPosition]!!.removeRequest = 1.toString()
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
//*/
//
//    }
//
//    /*private fun openPopUp(productListItem: ProductListItem?) {
//        val alertDialog = AlertDialog.Builder(androidx.appcompat.view.ContextThemeWrapper(activity, R.style.AlertDialogTheme))
//                .setTitle("Processing Request...")
//                .setMessage("Your Request to unsubscribe from ${productListItem!!.name} is already under processing")
//                .setCancelable(true)
//                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
//                    override fun onClick(p01: DialogInterface?, p1: Int) {
//                        p01!!.dismiss()
//                    }
//                })
//                .show()
//
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).width = LinearLayout.LayoutParams.MATCH_PARENT
//    }*/
//
//}
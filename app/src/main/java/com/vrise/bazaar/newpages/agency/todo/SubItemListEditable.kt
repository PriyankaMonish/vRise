package com.vrise.bazaar.newpages.agency.todo

//import androidx.appcompat.app.AlertDialog
//import android.app.Dialog
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.Window
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.retrofit.ApiService
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import com.vrise.bazaar.newpages.utilities.models.submodels.BillProductsItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data

//class SubItemListEditable(val context: FragmentActivity, val billProducts: MutableList<BillProductsItem?>?, val billId: String?, val observer: Interfaces.ReturnBillProductsItem, val apiService: ApiService?, val status: String?) : RecyclerView.Adapter<SubItemListEditable.ViewHolder>() {
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.bill_edit_item, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return billProducts?.size ?: 0
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.textView70.text = billProducts!![p0.adapterPosition]?.productName
//        p0.textView71.text = billProducts[p0.adapterPosition]?.type
//        p0.textView72.text = "₹ ${billProducts[p0.adapterPosition]?.totalAmount.toString()}"
//        if (status != null ){
//            if (status == "0"){
//                p0.textView73.visibility = View.VISIBLE
//                p0.imageView34.visibility = View.VISIBLE
//                p0.imageView34.setOnClickListener {
//                    val builder = AlertDialog.Builder(context)
//                    builder.setTitle("Delete Product")
//                    builder.setMessage("${billProducts[p0.adapterPosition]?.productName}")
//                    builder.setPositiveButton("OK") { dialog, _ ->
//                        if (hasConnection(context)) {
//                            apiService?.deletebillproduct(Request(
//                                    utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(context, DATAFILE, Constants.ID),
//                                    billId = billId,
//                                    billproductsd = billProducts[p0.adapterPosition]?.id,
//                                    product_id = billProducts[p0.adapterPosition]?.productId
//                            ))?.enqueue(object : CallbackClient(context, object : Interfaces.Callback {
//                                override fun additionalData(display: String?, logout: Boolean) {
//
//                                }
//
//                                override fun failed(t: Throwable) {
//                                    dialog.dismiss()
//                                    t.printStackTrace()
//                                }
//
//                                override fun success(response: Response?, data: Data?, state: Boolean) {
//                                    if (state) {
//                                        billProducts[p0.adapterPosition]?.let { it ->
//                                            billProducts.remove(it)
//                                            observer.removeData(it)
//                                            notifyItemRemoved(p0.adapterPosition)
//                                        }
//                                        dialog.dismiss()
//                                    } else {
//                                        dialog.dismiss()
//                                    }
//                                }
//                            }) {})
//                        }
//                    }
//                    builder.setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
//                    builder.create()
//                    builder.show()
//                }
//                p0.textView72.setOnClickListener {
//                    openDialog(context, p0.adapterPosition)
//                }
//                p0.textView73.setOnClickListener {
//                    openDialog(context, p0.adapterPosition)
//                }
//            } else {
//                p0.textView73.visibility = View.GONE
//                p0.imageView34.visibility = View.GONE
//            }
//        }
//    }
//
//    private fun openDialog(context: FragmentActivity, adapterPosition: Int) {
//        val dialog = Dialog(context)
//        dialog.setCancelable(true)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.item_edit_dialog)
//        context.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        val textView91 = dialog.findViewById<TextView>(R.id.textView91)
//        textView91.text = billProducts!![adapterPosition]!!.productName
//        val textView93 = dialog.findViewById<EditText>(R.id.textView93)
//        textView93.setText(billProducts[adapterPosition]!!.totalAmount.toString())
//        val textView94 = dialog.findViewById<TextView>(R.id.textView94)
//        textView93.setSelection(textView93.text.length)
//        textView93.addTextChangedListener(DecimalInputTextWatcher(textView93, 2))
//        textView94.setOnClickListener {
//            if (textView93.text.toString().isNotBlank()) {
//                billProducts[adapterPosition]?.totalAmount = textView93.text.toString()
//                dialog.dismiss()
//                observer.addData(billProducts[adapterPosition]!!)
//                notifyDataSetChanged()
//            } else {
//                textView93.error = "Enter Amount"
//            }
//        }
//        dialog.show()
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView70 = itemView.findViewById<TextView>(R.id.textView70)
//        val imageView34 = itemView.findViewById<ImageView>(R.id.imageView34)
//        val textView71 = itemView.findViewById<TextView>(R.id.textView71)
//        val textView72 = itemView.findViewById<TextView>(R.id.textView72)
//        val textView73 = itemView.findViewById<ImageView>(R.id.textView73)
//    }
//
//}

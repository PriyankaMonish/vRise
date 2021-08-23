package com.vrise.bazaar.newpages.agency.subscriber.addnewsubscriptions

//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import com.google.gson.GsonBuilder
//import com.vrise.R
//import com.vrise.bazaar.ex.retrofit.RetroClient
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.Subdata
//import com.squareup.picasso.Picasso


//class ItemList(val activity: FragmentActivity?, var dataItems: MutableList<PrdlistItem?>?, val subscriberObserver: Interfaces.ReturnPrdlistItem, var subscribeditem: Subdata?) : RecyclerView.Adapter<ItemList.ViewHolder>() {
//
//    var tempData: MutableList<PrdlistItem?>? = null
//    private var dlink = ""
//
//    init {
//        tempData = dataItems
//        dlink = "${RetroClient.getInstance(activity!!).imageUrl}/default_img/product.png"
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_check_new, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        if (dataItems!![p0.adapterPosition]!!.imgLink == dlink) {
//            p0.textView26.visibility = View.GONE
//            p0.textView22.text = dataItems!![p0.adapterPosition]!!.name
//        } else {
//            p0.textView22.text = ""
//            p0.textView26.visibility = View.VISIBLE
//            Picasso.get().load(dataItems!![p0.adapterPosition]!!.imgLink).placeholder(R.drawable.ic_placeholder).into(p0.textView26)
//        }
//
//        p0.view13.setOnClickListener {
//            subscriberObserver.returnData(dataItems!![p0.adapterPosition]!!)
//        }
//    }
//
//    fun addToList(clickPosvalue: PrdlistItem, selectedlanguage: String?, selectedcategory: String?) {
//        if (tempData != null) {
//            if (!tempData!!.contains(clickPosvalue)) {
//                try {
//                    tempData!!.add(clickPosvalue)
//                    updateList(selectedlanguage, selectedcategory)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }
//
//    fun removeFromList(clickPosvalue: PrdlistItem, selectedlanguage: String?, selectedcategory: String?) {
//        if (tempData != null) {
//            if (tempData!!.contains(clickPosvalue)) {
//                try {
//                    tempData!!.remove(clickPosvalue)
//                    updateList(selectedlanguage, selectedcategory)
//                } catch (e: Exception) {
//                }
//            }
//        }
//    }
//
//    fun updateList(language: String?, category: String?) {
//        print("$language and $category")
//
//        if (language != null && category != null) {
//
//            try {
//
//                dataItems = when {
//                    language.isBlank() && category.isBlank()-> tempData
//                    language.isBlank() -> tempData?.filter { it?.category.equals(category) }?.toMutableList()
//                    category.isBlank() -> tempData?.filter { it?.language.equals(language) }?.toMutableList()
//                    language.isNotBlank() && category.isNotBlank() -> tempData?.filter { it?.language.equals(language) && it?.category.equals(category) }?.toMutableList()
//                    else -> tempData?.filter { it?.language.equals(language) && it?.category.equals(category) }?.toMutableList()
//                }
//
//                notifyDataSetChanged()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } else if (language == null) {
//            try {
//                dataItems = tempData?.filter { it?.category.equals(category) }?.toMutableList()
//                notifyDataSetChanged()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        } else if (category == null) {
//            try {
//                dataItems = tempData?.filter { it?.language.equals(language) }?.toMutableList()
//                notifyDataSetChanged()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        println(GsonBuilder().setPrettyPrinting().create().toJson(dataItems))
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView22 = itemView.findViewById<TextView>(R.id.textView22)
//        val textView26 = itemView.findViewById<ImageView>(R.id.checkedTextView)
//        val selection = itemView.findViewById<CheckBox>(R.id.selection)
//        val imageView31 = itemView.findViewById<ImageView>(R.id.imageView31)
//        val view13 = itemView.findViewById<LinearLayout>(R.id.view13)
//    }
//}
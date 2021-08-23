package com.vrise.bazaar.ex.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.newmodels.ItemsItem
import com.vrise.bazaar.ex.model.newmodels.ShopData
import com.vrise.bazaar.ex.model.newmodels.ShopProductsItem
import com.vrise.bazaar.ex.model.submodels.ShopSubCategoriesItem
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference

class CategoryShopListAdapter(
    private val shopListItem: List<ShopSubCategoriesItem?>?,
    private val activity: FragmentActivity?,
    val param: Interfaces.ReturnAny
) : RecyclerView.Adapter<CategoryShopListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tab_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return shopListItem?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        shopListItem?.get(holder.adapterPosition)?.let {


            Instances.showBlur(activity, it.img_link.toString(), holder.imageView)
            holder.textView.text = it.name.toString().split(' ').joinToString(" ") { it.capitalize() }
            holder.itemVal.setOnClickListener { _ ->
                holder.click(shopListItem, holder.adapterPosition, activity)
                param.getValue(it)
            }
            holder.container.setOnClickListener { _ ->
                holder.click(shopListItem, holder.adapterPosition, activity)
                param.getValue(it)
                Preference.put(
                    activity, Preference.MAIN_SUB_CATEGORY_ID,
                    shopListItem[holder.adapterPosition]?.id!!, Preference.DATAFILE
                )
            }

        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemVal = item
        val imageView = itemVal.findViewById<ImageView>(R.id.view37)
        val container = itemVal.findViewById<ConstraintLayout>(R.id.container)
        val textView = itemVal.findViewById<TextView>(R.id.view38)
//        val no = itemVal.findViewById<ConstraintLayout>(R.id.no_data)

        fun click(it: List<ShopSubCategoriesItem?>?, pos: Int, activity: FragmentActivity?) {
            it?.forEach {
                it?.select = false
            }
            it?.get(pos)?.select = true
        }

    }
}

class ItemList(
    private val item: List<ItemsItem?>?,
    private val shos: ShopData?,
    private val params: Interfaces.ReturnAny,
    val activity: FragmentActivity?
) : RecyclerView.Adapter<ItemList.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.offers_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return item?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (item != null) {
//            if (!item[holder.adapterPosition]?.sizeStockPrice.isNullOrEmpty()) {
//                holder.textViewa?.text =
//                    (item[holder.adapterPosition]?.sizeStockPrice!![0]?.sizeName
//                        ?: "") + " ₹" + Math.round(
//                        (item[holder.adapterPosition]?.sizeStockPrice?.get(
//                            0
//                        )?.sizePrice!!.toDouble())
//                    )
//            }
            Instances.showBlur(
                activity,
                item[holder.adapterPosition]?.imgLink.toString(),
                holder.imageView
            )
//            activity?.let {
//                when {
//                    item[holder.adapterPosition]?.vegNon.toString() == "veg" -> holder.imageViewb?.setImageDrawable(
//                        ContextCompat.getDrawable(activity, R.drawable.ic_vegetarian)
//                    )
//                    item[holder.adapterPosition]?.vegNon.toString() == "non" -> holder.imageViewb?.setImageDrawable(
//                        ContextCompat.getDrawable(activity, R.drawable.ic_non_vegetarian)
//                    )
//                    else -> holder.imageViewb?.setImageDrawable(null)
//                }
//            }
//
//            if (item[holder!!.adapterPosition]!!.sizeStockPrice!![0]!!.pickup_discount == "") {
//                holder.textViewbgg!!.visibility = View.GONE
//            } else {
//                holder.textViewbgg!!.text =
//                    item[holder!!.adapterPosition]!!.sizeStockPrice!![0]!!.pickup_discount + " " + "for pickup discount "
//
//            }

            holder.textView?.text = item[holder.adapterPosition]?.name ?: ""
            holder.itemVal.setOnClickListener {
                params.getValue(item.get(holder.adapterPosition))
            }
//            if (item[holder.adapterPosition]?.spotDelivery != "1") {
//                holder.textViewb?.text = item[holder.adapterPosition]?.nextAvailable.toString()
//            }


        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemVal = item
        val imageView = itemVal.findViewById<ImageView?>(R.id.imageStore)
        val textView = itemVal.findViewById<TextView?>(R.id.txtStoreName)
//        val textViewa = itemVal.findViewById<TextView?>(R.id.textView270)
//        val textViewb = itemVal.findViewById<TextView?>(R.id.textView271)
//        val textViewbgg = itemVal.findViewById<TextView?>(R.id.textView308)
//        val imageViewb = itemVal.findViewById<ImageView?>(R.id.imageView57)
    }
}

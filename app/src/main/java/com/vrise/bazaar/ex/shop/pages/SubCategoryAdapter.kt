package com.vrise.bazaar.ex.shop.pages
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.newmodels.ItemsItem
class SubCategoryAdapter (
    private val item: List<ItemsItem?>?,
    val activity: FragmentActivity?,
    val fragment: Fragment?,
    val view: View?,
    val context: Context
    ) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_subcategory_item,
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
                holder.sub_category_item_name.text =
                    item[holder.adapterPosition]?.subcategory
            }
        }
        class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val itemVal = item
            val sub_category_item_name = itemVal.findViewById<TextView?>(R.id.sub_category_item_name)
        }
        interface OnSelectListenerInterface {
            fun onSelected(position: Int)
        }
}
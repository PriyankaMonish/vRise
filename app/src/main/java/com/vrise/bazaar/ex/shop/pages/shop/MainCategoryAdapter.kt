package com.vrise.bazaar.ex.shop.pages.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.submodels.SubcategoryLevel

class MainCategoryAdapter(
    private val item: List<SubcategoryLevel>?,
    val activity: FragmentActivity?,
    val fragment: Fragment?,
    val view: View?,
    val context: Context,
    val onSubMainCategoryClicked: OnSubMainCategoryClicked,
) : RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_category_item,
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

            if (position == selectedPosition) {
                holder.container.setBackgroundResource(R.drawable.bg_border_gr_full)
                holder.main_category_item_name.setTextColor(context.resources.getColor(R.color.wild_sand))
            } else {
              holder.container.setBackgroundResource(R.color.quantum_white_text)
                holder.main_category_item_name.setTextColor(context.resources.getColor(R.color.text_heading))
            }

            holder.main_category_item_name.text =
                item[holder.adapterPosition]?.name

            holder.container.setOnClickListener {
                onSubMainCategoryClicked.onSubMainClick(position)
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemVal = item
        val main_category_item_name = itemVal.findViewById<TextView?>(R.id.main_category_item_name)
        val container = itemVal.findViewById<ConstraintLayout?>(R.id.container)
    }

    interface OnSelectListenerInterface {
        fun onSelected(position: Int)
    }
}
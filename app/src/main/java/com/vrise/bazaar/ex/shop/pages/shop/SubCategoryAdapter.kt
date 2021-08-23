package com.vrise.bazaar.ex.shop.pages.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.submodels.SubLevel
import com.vrise.bazaar.ex.util.Constants

class SubCategoryAdapter(
    private val item: List<SubLevel>?,
    val activity: FragmentActivity?,
    val fragment: Fragment?,
    val view: View?,
    val context: Context,
    val onSubCategoryClicked: OnSubCategoryClicked
) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>(), Filterable {
    var selectedPosition: Int = 0
    var subCategoryFilterList = ArrayList<SubLevel>()

    init {
        subCategoryFilterList = item as ArrayList<SubLevel>
    }

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
        return subCategoryFilterList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (subCategoryFilterList != null) {
            holder.sub_category_item_name.text =
                subCategoryFilterList[holder.adapterPosition].name
            if (position == selectedPosition) {
                holder.container.setBackgroundResource(R.drawable.bg_border_gr_full)
                holder.sub_category_item_name.setTextColor(context.resources.getColor(R.color.quantum_white_text))
            } else {
                holder.container.setBackgroundResource(R.drawable.bg_border_gr_wh)
                holder.sub_category_item_name.setTextColor(context.resources.getColor(R.color.text_heading))
            }
            holder.container.setOnClickListener {
                onSubCategoryClicked.onSubClick(position)
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    subCategoryFilterList = item as ArrayList<SubLevel>
                } else {
                    val resultList = ArrayList<SubLevel>()
                    if (item != null) {
                        for (row in item) {
                            if (row?.subCategory?.equals(Constants.ALL)!! || row?.subCategory?.toLowerCase()
                                    ?.contains(constraint.toString().toLowerCase())!!
                            ) {
                                resultList.add(row)
                            }
                        }
                    }
                    subCategoryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = subCategoryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.values != null)
                    subCategoryFilterList = results?.values as ArrayList<SubLevel>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemVal = item
        val sub_category_item_name = itemVal.findViewById<TextView?>(R.id.sub_category_item_name)
        val container = itemVal.findViewById<ConstraintLayout?>(R.id.container)
    }

    interface OnSelectListenerInterface {
        fun onSelected(position: Int)
    }
}
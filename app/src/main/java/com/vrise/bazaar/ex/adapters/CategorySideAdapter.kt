package com.vrise.bazaar.ex.adapters

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.submodels.CategoryTree
import com.vrise.bazaar.ex.model.submodels.MainSubCategories
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import kotlinx.android.synthetic.main.fragment_side_bar_category.*


class CategorySideAdapter(
    val activity: FragmentActivity?,
    val viewModel: ItemDetailViewModel,
    val sub: List<CategoryTree?>?

    ) : RecyclerView.Adapter<CategorySideAdapter.ViewHolder>() {
    var catId:String? =null
    var newQty: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_expand, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return sub?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (sub != null) {

            holder.itemname.text = sub.get(position)!!.name.toString().split(' ').joinToString(" ") { it.capitalize() }

            catId = sub.get(position)!!.id.toString()

            BaseApp.categoryGlobal = catId
            val radius = 15
            Glide.with(activity!!)
                .load(sub.get(position)!!.image_link.toString())
                .transform(RoundedCorners(radius))
                .thumbnail(0.1f)
                .into(holder.image)
//            holder.answer.layoutParams.height = (cat?.get(position) ?: 0) as Int

            holder.DOWN.visibility =View.VISIBLE

            holder.DOWN.setOnClickListener {
                holder.UP.visibility = View.VISIBLE
                holder.subs.visibility = View.VISIBLE
                holder.DOWN.visibility =View.GONE
                holder.subs.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                var adapter = SubCategorySideAdapter(activity,viewModel,it,catId!!, sub.get(position)!!.main_subcategories)

                holder.subs.adapter = adapter
            }

            holder.UP.setOnClickListener {
                holder.UP.visibility = View.GONE
                holder.subs.visibility = View.GONE
                holder.DOWN.visibility =View.VISIBLE
            }
            }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView?>(R.id.imageView72)
        var itemname = itemView.findViewById<TextView?>(R.id.textView317)
        var subcat = itemView.findViewById<TextView?>(R.id.textView319)
        var answer = itemView.findViewById<ConstraintLayout?>(R.id.answer)
        var qstn = itemView.findViewById<ConstraintLayout?>(R.id.constraintLayout31)
        var subs = itemView.findViewById<RecyclerView?>(R.id.recy_sub)
        var DOWN = itemView.findViewById<ImageView?>(R.id.down)
        var UP = itemView.findViewById<ImageView?>(R.id.up)


    }

}



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
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.CategoryTree
import com.vrise.bazaar.ex.model.submodels.MainSubCategories
import com.vrise.bazaar.ex.model.submodels.SubLevel
import com.vrise.bazaar.ex.model.submodels.shippingmethods
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.shop.pages.ItemsAdapter
import com.vrise.bazaar.ex.shop.pages.main.ShippingMethod
import com.vrise.bazaar.ex.shop.pages.main.SideBarCategory
import com.vrise.bazaar.ex.shop.pages.shop.ItemFragment
import com.vrise.bazaar.ex.shop.pages.shop.MainCategoryAdapter
import com.vrise.bazaar.ex.shop.pages.shop.SubCategoryAdapter
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.fragment_side_bar_category.*


class SubCategorySideAdapter(
    val activity: FragmentActivity?,
    val viewModel: ItemDetailViewModel,
    val view: View,
    var catId:String,
    val sub: List<MainSubCategories?>?, ) : RecyclerView.Adapter<SubCategorySideAdapter.ViewHolder>() {
    var newQty: String? = null
    var shopidss:String?=null
    var catIdss : String? =null
    var subs : String? =null
    var child : String? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_subitem, parent, false
            )
        )


    }

    override fun getItemCount(): Int {
        return sub?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (sub != null) {
            shopidss = BaseApp.globalVariable
            catIdss = BaseApp.categoryGlobal
            holder.subcat.text = sub.get(position)!!.name.toString().split(' ').joinToString(" ") { it.capitalize() }


            holder.answer.setOnClickListener {
                subs = sub.get(position)!!.category
                child = sub.get(position)!!.id


                child?.let { it1 -> deleteItem(sub, viewModel, it1) }
            }
        }
    }
    private fun deleteItem(
        item: List<MainSubCategories?>, viewModel: ItemDetailViewModel,child:String
    ) {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getItemList(
                    Request(
                        utoken = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.TOKEN
                        ),
                        search_Key = "",
                        shop_id = shopidss,
                        category_id = subs,
                        main_subcategory_id = child,
                        subcategory_id = ""
                    )
                )?.observe(activity!!, Observer {
                    if (it != null) {
                        Preference.put(
                            activity, Preference.SHOP_ID,
                            shopidss ?: "", Preference.DATAFILE
                        )
                        Preference.put(
                            activity, Preference.CATEGORY,
                            subs ?: "", Preference.DATAFILE
                        )
                        Preference.put(
                            activity, Preference.MAIN_SUB_CATEGORY_ID,
                            child , Preference.DATAFILE
                        )

                        view.findNavController().navigate(
                            R.id.action_sideBarCategory_to_shopItemList)

                    }

                })
            }
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var subcat = itemView.findViewById<TextView?>(R.id.textView319)
        var answer = itemView.findViewById<ConstraintLayout?>(R.id.answer)


    }

}



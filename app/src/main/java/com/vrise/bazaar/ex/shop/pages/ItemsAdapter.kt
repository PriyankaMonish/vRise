package com.vrise.bazaar.ex.shop.pages

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopProductsItem
import com.vrise.bazaar.ex.model.submodels.ProductList
import com.vrise.bazaar.ex.model.submodels.SizeStockPrice
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemsAdapter(
    private val item: List<ProductList?>?,
    val activity: FragmentActivity?,
    val fragment: Fragment?,
    val view: View?,
    val context: Context,
    val viewModel: ItemDetailViewModel,
    val txtCount: TextView,

    ) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(), Filterable {
    var newquantity: Int? = 0
    var productFilterList = ArrayList<ProductList>()



    init {

        productFilterList = item as ArrayList<ProductList>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        txtCount.text = productFilterList?.size.toString() + " items"
        return productFilterList?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (productFilterList != null) {
//            Toast.makeText(activity,shop?.toString(), Toast.LENGTH_SHORT).show()
            holder.txtProductName.text =
                productFilterList[holder.adapterPosition]?.name.toString().split(' ')
                    .joinToString(" ") { it.capitalize() }

            Instances.showBlur(
                activity,
                productFilterList[holder.adapterPosition]?.imgLink.toString(),
                holder.imgProduct
            )
            if (productFilterList[holder.adapterPosition]?.sizeStockPrice!!.size > 1) {
                holder.spnrType.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_down,
                    0
                )
            } else
                holder.spnrType.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            holder.txtProductName.text =
                productFilterList[holder.adapterPosition]?.name.toString().split(' ')
                    .joinToString(" ") { it.capitalize() }
            var sizePosition = 0

            holder.spnrType.text =
                productFilterList[holder.adapterPosition].sizeStockPrice?.get(0)?.sizeName.toString()


            val onSelectListenerInterface: OnSelectListenerInterface =
                object : OnSelectListenerInterface {
                    override fun onSelected(position: Int) {
                        holder.spnrType.text =
                            productFilterList[holder.adapterPosition].sizeStockPrice?.get(position)?.sizeName.toString()
                        val precisions = DecimalFormat("0.00")
                        holder.txtPrice?.text = "Rs." + (String.format(
                            precisions.format(
                                productFilterList[holder.adapterPosition].sizeStockPrice?.get(
                                    position
                                )?.sizePrice
                            )
                        ))

                        sizePosition = position
                    }

                }
            holder.spnrType.setOnClickListener {
                if (productFilterList[holder.adapterPosition].sizeStockPrice!!.size > 1)
                    openQty(
                        productFilterList[holder.adapterPosition].sizeStockPrice,
                        onSelectListenerInterface
                    )
            }

            var quantity = 0
//            holder.nextavailable.text = productFilterList[holder.adapterPosition].nextAvailable
            val precision = DecimalFormat("0.00")




            if (productFilterList?.get(holder.adapterPosition)?.shop_open_status.toString() == "0"){

                holder.txtCount.visibility = View.GONE
                holder.txtPlus.visibility = View.GONE
                holder.txtMinus.visibility = View.GONE
                holder.txtAddToCart.visibility = View.GONE

                holder.next_available.visibility = View.VISIBLE
                holder.next_available?.text =
                    productFilterList?.get(holder.adapterPosition)?.shop_next_open.toString()
                Toast.makeText(activity,  item?.get(holder.adapterPosition)?.shop_next_open.toString(), Toast.LENGTH_SHORT).show()
            }else {

                if (productFilterList[holder.adapterPosition].spotDelivery.toString() != "1" ||
                    productFilterList[holder.adapterPosition].deliveryTimes?.isEmpty() == true
                ) {
                    holder.txtCount.visibility = View.GONE
                    holder.txtPlus.visibility = View.GONE
                    holder.txtMinus.visibility = View.GONE
                    holder.txtAddToCart.visibility = View.GONE

                    holder.next_available.visibility = View.VISIBLE
                    holder.next_available?.text =
                        productFilterList[holder.adapterPosition].nextAvailable.toString()

                }


            }





            holder.txtPrice.text = "Rs." +
                    (String.format(
                        precision.format(
                            productFilterList[holder.adapterPosition].sizeStockPrice?.get(
                                0
                            )?.sizePrice
                        )
                    ))
            holder.imgProduct.setOnClickListener {

//                val myFragment: Fragment = ItemDetailFragment()
                val args = Bundle()
                args.putString(Constants.ID, (productFilterList[holder.adapterPosition].sprdtid))
//                myFragment.setArguments(args)

                it.findNavController().navigate(
                    R.id.action_shopItemList_to_navigation_item_detail, args
                )


//                activity!!.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.nav_host_fragment, myFragment)?.addToBackStack(null)?.detach(
//                        ItemDetailFragment()
//                    )?.hide(ItemDetailFragment())
//                    ?.commit()

            }
            holder.txtAddToCart.setOnClickListener {
                if (holder.txtCount.text != "0") {
                    addToCart(
                        productFilterList[holder.adapterPosition],
                        sizePosition,
                        holder.txtCount.text.toString()
                    )
                } else {
                    Toast.makeText(
                        activity, "Please select the quantity",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            holder.txtPlus.setOnClickListener {


                if (quantity < productFilterList[holder.adapterPosition]?.sizeStockPrice?.get(0)
                        ?.stock!!.toDouble()
                ) {


                    quantity = quantity + 1
                    holder.txtCount.text = quantity.toString()
                } else {

                    Instances.toast(activity, "Stock not available")
                }
            }
            holder.txtMinus.setOnClickListener {

                if (quantity > 0) {

                    quantity = (quantity - 1)
                    holder.txtCount.text = quantity.toString()
                }
            }

        }

    }

    fun addToCart(itemsItem: ProductList?, selectedSize: Int, quantity: String) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModel.addItem(
            Request(
                changeSeller = "0",
                utoken = Preference.get(activity, DATAFILE, Preference.TOKEN),
                ecom_product_id = itemsItem!!.id,
                seller_product_id = itemsItem.sprdtid,
                product_size_id = itemsItem.sizeStockPrice?.get(selectedSize)?.sizeId,
                product_quantity = quantity,
                delivery_slot_id = itemsItem.deliveryTimes!!.get(0).id,
                delivery_date = date,
                delivery_type = "2",
                shipping_method = "0"

            )

        )?.observe(fragment?.viewLifecycleOwner!!, Observer { data ->
            if (data != null) {
                (activity as DashBoardContainer).setCartBadge(data.cartCount ?: 0)
                Toast.makeText(activity, data.display, Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(activity, "Item Out of Stock", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun getFilter(): Filter  {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty() || charSearch.equals(Constants.ALL)) {
                    productFilterList = item as ArrayList<ProductList>
                } else {
                    val resultList = ArrayList<ProductList>()
                    if (item != null) {
                        for (row in item) {
                            if (row?.subcategoryId?.toLowerCase()
                                    ?.contains(constraint.toString().toLowerCase())!!
                            ) {
                                resultList.add(row)
                            }
                        }
                    }
                    productFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productFilterList = results?.values as ArrayList<ProductList>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemVal = item
        val imgProduct = itemVal.findViewById<ImageView?>(R.id.imgProductImage)
        val txtProductName = itemVal.findViewById<TextView?>(R.id.txtProductName)
        val txtPrice = itemVal.findViewById<TextView?>(R.id.txtPrice)
        val next_available = itemVal.findViewById<TextView?>(R.id.textView349)
        val txtPlus = itemVal.findViewById<TextView?>(R.id.txtPlus)
        val txtMinus = itemVal.findViewById<TextView?>(R.id.txtMinus)
        val txtAddToCart = itemVal.findViewById<TextView?>(R.id.txtAddtoCart)
        val txtCount = itemVal.findViewById<TextView?>(R.id.txtCount)
        val container = itemVal.findViewById<ConstraintLayout?>(R.id.container)
        val spnrType = itemVal.findViewById<TextView?>(R.id.spnrTypes)

    }

    private fun openQty(
        dataItem: List<SizeStockPrice>?,
        callBack: OnSelectListenerInterface
    ) {
        if (dataItem.isNullOrEmpty()) {
            Instances.toast(activity, "item not available")
        } else {
            Instances.openListPoPUp(activity,
                dataItem as ArrayList<Any?>,
                null,
                "Options",
                object : Interfaces.ReturnAny {
                    override fun getValue(values: Any?) {
                        if (values is SizeStockPrice) {
                            callBack.onSelected(position = values.selectedPosition!!)
                        }
                    }
                })
        }
    }

    interface OnSelectListenerInterface {
        fun onSelected(position: Int)
    }
}

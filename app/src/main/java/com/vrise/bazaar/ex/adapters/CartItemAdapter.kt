package com.vrise.bazaar.ex.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newcart.ItemsItem
import com.vrise.bazaar.ex.shop.interfaces.CartViewModel
import com.vrise.bazaar.ex.shop.pages.main.CartFragment
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import java.text.DecimalFormat


class CartItemAdapter(
    val activity: FragmentActivity?,
    val fragment: CartFragment,
    val shopDelivery: List<ItemsItem?>,
    val viewModel: CartViewModel
    ) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    var newQty :String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_new_cart_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return shopDelivery?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shopDelivery[holder.adapterPosition] as ItemsItem
        val radius = 15
        Glide.with(activity!!)
            .load(shopDelivery[holder.adapterPosition]?.imgLink)
            .transform(RoundedCorners(radius))
            .thumbnail(0.1f)
            .into(holder.image)
        holder.itemname?.text =
            shopDelivery[holder.adapterPosition]?.name?.split(' ')?.joinToString(" ") { it.capitalize() }
        holder.qty?.text=  shopDelivery[holder.adapterPosition]?.productQuantity
        holder.size?.text = shopDelivery[holder.adapterPosition]?.size

        val precision = DecimalFormat("0.00")
        holder.amount?.text = "Rs" + " " +(String.format(precision.format( shopDelivery[holder.adapterPosition]?.price))).toString()



        holder.plus?.setOnClickListener {
            if (shopDelivery[position]?.productQuantity.toString().toInt() > 1) {
                changeVal(false, item, viewModel,position, holder.qty as TextView)
            } else if (shopDelivery[position]?.productQuantity.toString().toInt() == 1) {
                deleteItem(item, viewModel,position)
            }
        }
        holder.minus?.setOnClickListener {
            changeVal(true, item, viewModel, position,holder.qty as TextView)
        }
        holder.remove?.setOnClickListener {
            deleteItem(item, viewModel, position)
        }
    }

    private fun changeVal(
        type: Boolean, item: ItemsItem, viewModel: CartViewModel, position: Int, textView: TextView   ) {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.update(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                        cartId = item.id,
                        product_quantity = if (type) (textView.text.toString().toInt() + 1).toString()
                        else
                                (textView.text.toString().toInt() - 1).toString()
                    )
                )?.observe(activity!!, Observer {

                    if (it!= null) {

                        Toast.makeText(activity, "Successfully updated Quantity", Toast.LENGTH_SHORT).show()
                        textView.text = it.data?.product_quantity.toString()
                        fragment.getCartStore()

                    }

                    else
                    {
                        Toast.makeText(activity, "Out of Stock", Toast.LENGTH_SHORT).show()

                    }
                })
            } else {
                Instances.toast(activity, activity?.getString(R.string.no_internet))
            }
        }
    }

    private fun deleteItem(
        item: ItemsItem, viewModel: CartViewModel, position: Int
    ) {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.delete(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN), cartId = item.id
                    )
                )?.observe(activity!!, Observer {
                    if (it) {
                        Toast.makeText(activity, "Successfully deleted Cart Item", Toast.LENGTH_SHORT).show()
                        fragment.getCartData()
                    }
                })
            } else {
                Instances.toast(activity, activity?.getString(R.string.no_internet))
            }
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView?>(R.id.imageView70)
        var itemname = itemView.findViewById<TextView?>(R.id.tv_item)
        var size = itemView.findViewById<TextView?>(R.id.tv_size)
        var amount = itemView.findViewById<TextView?>(R.id.tv_amount)
        var cons = itemView.findViewById<ConstraintLayout?>(R.id.cons)
        var plus = itemView.findViewById<TextView?>(R.id.textView74)
        var qty = itemView.findViewById<TextView?>(R.id.textView75)
        var minus = itemView.findViewById<TextView?>(R.id.textView76)
        var remove = itemView.findViewById<ImageView?>(R.id.tv_remove)


    }

}
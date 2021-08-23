package com.vrise.bazaar.ex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.model.submodels.shippingmethods
import com.vrise.bazaar.ex.shop.interfaces.IShippingIdListener
import kotlinx.android.synthetic.main.fragment_cart_new.*
import java.util.logging.Handler

class ShippingAdapter(
    val activity: FragmentActivity?,
    val shopDelivery: List<shippingmethods>,

    ) : RecyclerView.Adapter<ShippingAdapter.ViewHolder>() {
    private var lastSelectedPosition = -1
    lateinit var listener: IShippingIdListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shipping_cart, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return shopDelivery?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        holder.radio.setChecked(lastSelectedPosition == position)

        val radius = 15
        Glide.with(activity!!)
            .load(shopDelivery[holder.adapterPosition].img_link)
            .transform(RoundedCorners(radius))
            .thumbnail(0.1f)
            .into(holder.image)
        holder.itemname?.text = shopDelivery[holder.adapterPosition].description
        holder.radio.setOnClickListener {
            shopDelivery[holder.adapterPosition].id?.let {
                listener.onShippingItemClicked(
                it,
                position
            ) }
            lastSelectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }


        holder.radcons.setOnClickListener {
            shopDelivery[holder.adapterPosition].id?.let { listener.onShippingItemClicked(
                it,
                position
            ) }

            lastSelectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
        if (shopDelivery.size==1 && !holder.radio.isChecked){
            holder.radio.isChecked = true
            android.os.Handler().postDelayed(Runnable {
                shopDelivery[holder.adapterPosition].id?.let {
                    listener.onShippingItemClicked(
                        it,
                        position
                    ) }
                lastSelectedPosition = holder.adapterPosition
            }, 500)
//            notifyDataSetChanged()
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView?>(R.id.shipp_link)
        var itemname = itemView.findViewById<TextView?>(R.id.textVietv_shipping_name)
        var radio = itemView.findViewById<RadioButton?>(R.id.radioButton7)
        var radcons = itemView.findViewById<ConstraintLayout?>(R.id.cons111)



    }

}
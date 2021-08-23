package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.shippingmethods
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_shipping_method.*

class ShippingMethod : Fragment() {
    private lateinit var viewModel: ItemDetailViewModel

    companion object {
        var seller = ""
        var newone = ""
        var prdsize = ""
        var sellerid = ""
        var prdctsize = ""
        var selctedslot = ""
        var todaydate = ""
        var prdctdata = ""
        var shippingId = ""


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_shipping_method, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(ItemDetailViewModel::class.java)

        seller = arguments?.getString(Constants.SELLER, "").toString()
        newone = arguments?.getString(Constants.PRODUCTID, "").toString()
        prdsize = arguments?.getString(Constants.SELLERPRODUCTID, "").toString()
        sellerid = arguments?.getString(Constants.PRODUCTSIZEID, "").toString()
        prdctsize = arguments?.getString(Constants.pRODUCTQTY, "").toString()
        selctedslot = arguments?.getString(Constants.delivery_slot_id, "").toString()
        todaydate = arguments?.getString(Constants.delivery_date, "").toString()
        prdctdata = arguments?.getString(Constants.delivery_type, "").toString()


        getData()


    }


    private fun getData() {

        viewModel.Ships(
            Request(
                changeSeller = seller,
                utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                ecom_product_id = newone,
                seller_product_id = prdsize,
                product_size_id = sellerid,
                product_quantity = prdctsize,
                delivery_slot_id = selctedslot,
                delivery_date = todaydate,
                delivery_type = prdctdata

            )
        )?.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {

                recycler.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                recycler.adapter = OrderdetailAdapter(
                    requireActivity(), viewModel, requireView(), data.shipping_methods

                )
            }
        })

    }

    class OrderdetailAdapter(
        private var activity: FragmentActivity,
        val viewModel: ItemDetailViewModel,
        val view: View,
        var newitem: ArrayList<shippingmethods>?

    ) :
        RecyclerView.Adapter<OrderdetailAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.item_ship,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return newitem?.size!!
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = newitem!![holder.adapterPosition] as shippingmethods

            holder.name.text = newitem!![position].method.toString()
            holder.description.text = newitem!![position].description.toString()

            val radius = 15
            Glide.with(activity)
                .load(newitem?.get(position)!!.img_link)
                .transform(RoundedCorners(radius))
                .thumbnail(0.1f)
                .into(holder.image)
            holder.constrain.setOnClickListener {

                deleteItem(item, viewModel)

            }


        }

        private fun deleteItem(
            item: shippingmethods, viewModel: ItemDetailViewModel
        ) {
            Instances.InternetCheck { internet ->
                if (internet) {
                    viewModel.addItem(
                        Request(
                            changeSeller = seller,
                            utoken = Preference.get(
                                activity,
                                Preference.DATAFILE,
                                Preference.TOKEN
                            ),
                            ecom_product_id = newone,

                            seller_product_id = prdsize,
                            product_size_id = sellerid,
                            product_quantity = prdctsize,
                            delivery_slot_id = selctedslot,
                            delivery_date = todaydate,
                            delivery_type = prdctdata,
                            shipping_method = item.id

                        )
                    )?.observe(activity, Observer { data ->
                        if (data != null) {

                            view?.findNavController()
                                ?.navigate(
                                    R.id.action_navigation_shipping_method_to_navigation_cart
                                )

//
//
                            (activity as DashBoardContainer).setCartBadge(data.cartCount ?: 0)
//
                            toast(activity, "item added to cart")
                        } else {
                            toast(activity, "item delivery pending")
                        }


                    })
                } else {
                    Instances.toast(activity, activity.getString(R.string.no_internet))
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val description: TextView = itemView.findViewById(R.id.textView302)
            val name: TextView = itemView.findViewById(R.id.textView301)
            val image: ImageView = itemView.findViewById(R.id.imageView68)
            val constrain: ConstraintLayout = itemView.findViewById(R.id.shipcons)

        }


    }


}
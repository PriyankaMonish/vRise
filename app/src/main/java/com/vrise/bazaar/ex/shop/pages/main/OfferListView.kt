package com.vrise.bazaar.ex.shop.pages.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopRecommProductItem
import com.vrise.bazaar.ex.model.submodels.RecommProductItemFull
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.FinderViewModel
import com.vrise.bazaar.ex.shop.pages.MostPopularAdapter
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_offer_list_view.*
import kotlinx.android.synthetic.main.fragment_search_shop_detail.*
import kotlinx.android.synthetic.main.fragment_search_shop_detail.view26
import kotlinx.android.synthetic.main.item_progress_sheet.*

class OfferListView : Fragment() {
    private lateinit var viewModel: FinderViewModel

    private var shopId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer_list_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shopId = requireArguments().getString(Constants.ID)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(FinderViewModel::class.java)
        getShops()
    }




    private fun getShops() {

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel?.getShopDetails(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = shopId
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        if ( it.recom_products_full!=null && it.recom_products_full.toString()!="" )
                        {
                            offer_recycle.layoutManager = GridLayoutManager(activity, 3)
                            offer_recycle.adapter = MostPopularAdapter(
                                activity,
                                it.recom_products_full,
                                viewModel
                            )
                        }

                    }
                    })

                }
            }
        }



    class MostPopularAdapter(
        val activity: FragmentActivity?,
        val item: List<RecommProductItemFull?>?,
        val viewModel: FinderViewModel
    ) : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_most_popular_list, parent, false
                )
            )
        }

        override fun getItemCount(): Int {
            return item?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (item != null) {
                Instances.showBlur(
                    activity,
                    item[holder.adapterPosition]?.imgLink ?: "",
                    holder.imageView14
                )
                holder.textView30?.text = item[holder.adapterPosition]?.name ?: ""
                holder.item?.setOnClickListener {

                    if (item?.get(holder.adapterPosition)?.shop_open_status.toString() == "0") {
                        Toast.makeText(activity, item?.get(holder.adapterPosition)?.shop_next_open.toString(), Toast.LENGTH_SHORT).show()
                    }else {
                        it.findNavController().navigate(
                            R.id.action_offerListView_to_navigation_item_detail, bundleOf(
                                Constants.ID to (item[holder.adapterPosition]?.sprdtid ?: "")
                            )
                        )
                    }
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView14 = itemView.findViewById<ImageView?>(R.id.imageView14)
            val item = itemView.findViewById<CardView?>(R.id.cardView22)
            val textView30 = itemView.findViewById<TextView?>(R.id.textView399)
        }

    }
}

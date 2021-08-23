package com.vrise.bazaar.ex.shop.pages

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopData
import com.vrise.bazaar.ex.model.newmodels.ShopRecommProductItem
import com.vrise.bazaar.ex.model.submodels.CategoriesItem
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.FinderViewModel
import com.vrise.bazaar.ex.shop.interfaces.RegistrationViewModel
import com.vrise.bazaar.ex.shop.interfaces.ShopDetailViewModel
import com.vrise.bazaar.ex.shop.pages.shop.ShopDetailFragment
import com.vrise.bazaar.ex.util.*
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.fragment_search_shop_detail.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import kotlinx.android.synthetic.main.shop_detail_fragment.*


class SearchShopDetail : Fragment() {
    private lateinit var viewModel: FinderViewModel
    private lateinit var viewModel1: RegistrationViewModel
    private var shopId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_shop_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        (activity as DashBoardContainer).txtTitle?.text = "VRise"
        shopId = requireArguments().getString(Constants.ID)
        progress.visibility = View.VISIBLE
//        button32.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(FinderViewModel::class.java)
        viewModel1 = ViewModelProviders.of(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(RegistrationViewModel::class.java)
        getShops()
    }



    private fun getShops() {
        progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel?.getShopDetails(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = shopId
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        progress.visibility = View.GONE
                        textView373.setText(it.shopData?.storeName?.split(' ')?.joinToString(" ")
                        { it.capitalize() })

                        var orderText = it.order_model_text.toString()
                            if (it.order_model_text.toString()!="null" &&  it.order_model_text.toString()!="") {
                                imageView35.visibility = View.VISIBLE

                                imageView35.setOnClickListener {
                                    textView391.visibility = View.VISIBLE
                                    textView391.setText(orderText)

                                }
                                textView391.setOnClickListener {
                                    textView391.visibility = View.GONE


                                }



                        }

                        val radius = 15

//                        if (it.tagstatus == "true"){
//                            button32.visibility = View.INVISIBLE
//                        }
                        Glide.with(requireActivity())
                            .load((it.tagicon))
                            .transform(RoundedCorners(radius))
                            .thumbnail(0.1f)
                            .into(imageView83)

                        Preference.put(
                            activity,
                            Preference.STORE_NAME,
                            it.shopData?.storeName.toString(),
                            Preference.DATAFILE
                        )
                        Instances.showBlur(
                            activity,
                            it.shopData?.imgLink.toString(),
                            imageViiew52
                        )
                        Preference.put(
                            activity,
                            Preference.SHOP_ID,
                            it?.shopData?.id.toString(),
                            Preference.DATAFILE
                        )
                        view125.layoutManager = GridLayoutManager(activity, 3)
                        view125.adapter =
                            CategorySearchShopListAdapter(it.categories,activity,it.shopData)

                        if ( it.recom_products!=null && it.recom_products.toString()!="" && it.recom_products.size>0 ){
                            view26.visibility = View.VISIBLE
                            textView398.visibility = View.VISIBLE
                            textView400.visibility = View.VISIBLE
                            view26.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                            view26.adapter = MostPopularAdapter(
                                activity,
                                it.recom_products,
                                viewModel
                            )
                        }else
                        {
                            view26.visibility = View.GONE
                            textView398.visibility = View.GONE
                            textView400.visibility = View.GONE
                        }

                    }

                    imageView83.setOnClickListener {

                        val display =
                            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                                .setCancelable(true).setMessage("Are you sure you want to add as Favourites?")
                                .setNegativeButton("YES") { dialog1, _ ->


                                    viewModel1.addTaag(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                Preference.DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            seller_id = shopId,
                                            sellerref = ""
                                        )
                                    )?.observe(requireActivity(), Observer {

                                        this.onCreate(null)
                                        getShops()
                                    })
                                    dialog1?.dismiss()
                                }
                                .setPositiveButton("NO") { dialog1, _ ->

                                    dialog1?.dismiss()
                                }.create()


                        display.setOnShowListener {
                            display.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.green_
                                )
                            )
                            display.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.green_
                                )
                            )
                        }
                        display.show()


                    }


                    textView400.setOnClickListener {
                        val args = Bundle()
                        args.putString(Constants.ID, shopId)
                        it.findNavController().navigate(R.id.offerListView,args)
                    }

                })

                }

            }
        }
    }



    class CategorySearchShopListAdapter(
        private val shopListItem: List<CategoriesItem?>?,
        private val activity: FragmentActivity?,
        private val shopData: ShopData?
    ) : RecyclerView.Adapter<CategorySearchShopListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.tab2_category,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return shopListItem?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            shopListItem?.get(holder.adapterPosition)?.let {

                Instances.showBlur(
                    activity,
                    it.imagelink.toString(),
                    holder.imageView
                )
                holder.textView.text = it.name?.toString()

                holder.container?.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {

                        Preference.put(
                            activity,
                            Preference.CATEGORY,
                            it.id.toString(),
                            Preference.DATAFILE
                        )
                        Instances.hideKeyboard(activity)

                        val args = Bundle()
                        args.putString(Constants.ID, shopData!!.id)
                        args.putString(Constants.NAME, shopData.storeName)
                        v.findNavController().navigate(
                            R.id.action_searchShopDetail_to_shopDetailFragmentB, args
                        )


                    }
                })
            }
        }

        class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            val itemVal = item
            val imageView = itemVal.findViewById<ImageView>(R.id.imageView14)
            val container = itemVal.findViewById<CardView>(R.id.cards)
            val textView = itemVal.findViewById<TextView>(R.id.textView14)


        }
    }


        class MostPopularAdapter(
            val activity: FragmentActivity?,
            val item: List<ShopRecommProductItem?>?,
            val viewModel: FinderViewModel
        ) : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_most_popular, parent, false
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
                        }else
                        {
                            it.findNavController().navigate(
                                R.id.action_searchShopDetail_to_navigation_item_detail, bundleOf(
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




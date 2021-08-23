package com.vrise.bazaar.ex.shop

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vrise.R
import com.vrise.bazaar.MyApplication
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Filters
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ItemsItem
import com.vrise.bazaar.ex.model.newmodels.ShopProductsItem
import com.vrise.bazaar.ex.model.submodels.*
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.IFilterConstraintListener
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.shop.interfaces.ItemViewModel
import com.vrise.bazaar.ex.shop.pages.main.OfferListFragment
import com.vrise.bazaar.ex.shop.pages.shop.ItemFragment
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.CATEGORY
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.databinding.ShopDetailBFragmentBinding
import kotlinx.android.synthetic.main.bootomsheet2.*
import kotlinx.android.synthetic.main.fragment_search_shop_detail.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.item_sort_main.view.*
import kotlinx.android.synthetic.main.shop_detail_b_fragment.*
import kotlinx.android.synthetic.main.shop_detail_b_fragment.imageView83
import kotlinx.android.synthetic.main.shop_detail_b_fragment.txtLblShopCategory
import kotlinx.android.synthetic.main.shop_detail_fragment.*


class ShopDetailFragmentB : Fragment() {

    private lateinit var viewModel: ShopDetailBViewModel
    private lateinit var viewModel1: ItemDetailViewModel
    private var selectedSize: SizeStockPriceItem? = null
    private lateinit var binding: ShopDetailBFragmentBinding
    private var newsnew: List<Filterdata>? = null
    private var newsub: List<Valuess>? = null
    private var previousFilter: String = ""
    private var selectedFilterPosition = 0
    private var shopListItem: List<ShopSubCategoriesItem?>? = null
    private var data : Data? = null
    private var filter_id = Filters()
    private var shopId: String? = null
    var SPLASH_TIME_OUT = 500
    private var shopName: String? = null

    public var id:String?= null
    public var seller:String?= null
    public var sellerproductid:String?= null


    //    private var filterData = MutableLiveData<Data?>()
    private lateinit var comm: DashBoardContainer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shop_detail_b_fragment, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApp.enableGlobal = 1
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(requireActivity()),
                    requireActivity()
                )
            )
        ).get(ShopDetailBViewModel::class.java)
        viewModel1 = ViewModelProvider(
            this,
            ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(requireActivity()),
                    requireActivity()
                )
            )
        ).get(ItemDetailViewModel::class.java)



//        Toast.makeText(activity, BaseApp.enableGlobal!!, Toast.LENGTH_SHORT).show()
        progress.isVisible = true
        Preference.put(
            activity, Preference.SHOP_ID,
            arguments?.getString(Constants.ID) ?: "", Preference.DATAFILE
        )
        shopId = arguments?.getString(Constants.ID) ?: ""
        shopName = arguments?.getString(Constants.NAME) ?: ""

//        var mApp =  BaseApp()
//        mApp.globalVariable =     shopId =

        BaseApp.globalVariable = shopId


    }

    override fun onResume() {
        super.onResume()
        if (shopListItem != null) {
            if (shopListItem!!.size > 0) {
                shopDetail()
                getDataS(data)
                progress.isVisible = false
            } else
                initControll()
        } else
            initControll()

    }




                private fun shopDetail(){
        binding.view25.adapter = CategoryShopListAdapter(shopListItem,
            requireActivity(),
            object : Interfaces.ReturnAny {
                override fun getValue(values: Any?) {

                    if (values is ShopSubCategoriesItem?) {
                        val args = Bundle()

                        args.putString(Constants.NAME, shopName)

                        findNavController().navigate(
                            R.id.action_shopDetailFragmentB_to_navigation_item_list,
                            args
                        )
//                                        ldfd.setArguments(args)
//                                        activity!!.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                                        activity!!.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, ldfd).detach(
//                                            ItemFragment()
//                                        ).hide(ShopDetailFragmentB()).addToBackStack(null).commit()


                        //                                    it.shopProducts?.size?.let {
                        //                                        binding.view26.requestFocus()
                        //                                        binding.view26.scrollToPosition(0)
                        //                                    }
                        //
                        //                                    binding.view36.text = values?.subcategory?.name
//                                            activity!!.supportFragmentManager.beginTransaction()
//
//
//                                                .replace(R.id.nav_host_fragment, ItemFragment()).addToBackStack(null)
//                                                .commit()

                        //                                    binding.view26.adapter = ItemList(
                        //                                        values?.items,
                        //                                        it.shopData,
                        //                                        object : Interfaces.ReturnAny {
                        //                                            override fun getValue(values: Any?) {
                        //                                                if (values is ItemsItem?) view?.findNavController()
                        //                                                    ?.navigate(
                        //                                                        R.id.action_shopDetailFragmentB_to_navigation_item_detail,
                        //                                                        bundleOf(
                        //                                                            Constants.ID to (values?.sprdtid ?: "")
                        //                                                        )
                        //                                                    )
                        //                                            }
                        //                                        },
                        //                                        requireActivity()
                        //                                    )
                    }

                }
            })

    }
    private fun initControll() {

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.applyFilter(
                    Request(
                        utoken = Preference.get(requireActivity(), DATAFILE, TOKEN),
                        shop_id = arguments?.getString(Constants.ID) ?: "",
                        category_id = Preference.get(requireActivity(), DATAFILE, CATEGORY),
                        subcategory_id = "",
                        vegOrNon = "",
                        filter = filter_id
                    )
                )

                viewModel.filterData.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    data = it
                    getDataS(data)
                })

            } else {
                toast(activity, getString(R.string.no_internet))
            }

        }
    }

    fun getDataS(it: Data?){
        binding.textView325.setText(shopName)
        val radius = 15
        Glide.with(requireActivity())
            .load((it?.tagicon))
            .transform(RoundedCorners(radius))
            .thumbnail(0.1f)
            .into(imageView83)


        binding.view25.visibility = View.VISIBLE
        if ( it!= null) {
            no_data.visibility = View.GONE
            txtLblShopCategory.visibility = View.INVISIBLE
            Log.d("Shopcat", it.shopSunCategories?.size.toString())
            if (it.shopSunCategories!!.size != 0) {
                shopListItem = it.shopSunCategories
                shopDetail()
            } else {
                no_data.visibility = View.VISIBLE
                txtLblShopCategory.visibility = View.INVISIBLE
            }
        }


        if (it!!.shopProducts?.size != 0) {
            binding.view36.setTypeface(binding.view36.getTypeface(), Typeface.BOLD);
            binding.view36.text = it!!.shopProducts?.get(0)?.subcategory?.name
//            binding.view26.adapter =
//                ItemList(
//                    it.shopProducts?.get(0)?.items,
//                    it.shopData,
//                    object : Interfaces.ReturnAny {
//                        override fun getValue(values: Any?) {
//                            if (values is ItemsItem?) view?.findNavController()
//                                ?.navigate(
//                                    R.id.action_shopDetailFragmentB_to_navigation_item_detail,
//                                    bundleOf(Constants.ID to (values?.sprdtid ?: ""))
//                                )
//                        }
//                    },
//                    requireActivity()
//                )
//                        binding.view18.text = it.shopData?.storeName ?: ""
            binding.view19.text = it.shopData?.address ?: ""
            binding.view22.text = it.shopData?.rating ?: ""
            binding.view24.text = it.shopData?.distance ?: ""
            binding.view46.text = it.shopData?.packingtime?.let {
                // Instances.changeDateFormat("HH:mm:ss", "mm", it, "0") + "mins"
                it + "mins"
            }


            if (!it.shopData?.licenses.isNullOrBlank()) {
                binding.view47.text = "License No:" + " " + it.shopData?.licenses
            } else {
                binding.view47.text = ""
            }


//                        var handler = Handler()
//                         var timeCounter: Runnable? = null
//                        val imageList: ArrayList<BannerItem> = ArrayList()
//                        if (it.shopData?.imgLink != null) {
//                            var  bannerItem  : BannerItem = BannerItem()
//                            bannerItem.image = it.shopData.imgLink
//                            imageList.add(bannerItem)
//                        }
//
//                        if (imageList != null) {
//                            if (imageList.isNotEmpty()) {
//                                viewFlipper?.adapter = SliderAdapter(activity, imageList)
//                                try {
//                                    if (imageList.size >= 2) {
//                                        var currentIndex = 0
//                                        timeCounter = object : Runnable {
//                                            override fun run() {
//                                                if ((currentIndex + 1) > imageList.size) {
//                                                    currentIndex = 0
//                                                } else {
//                                                    currentIndex++
//                                                }
//                                                viewFlipper?.currentItem = currentIndex
//                                                handler.postDelayed(this, 3 * 1000)
//                                            }
//                                        }
//                                        handler.postDelayed(timeCounter, 3 * 1000)
//                                    }
//                                } catch (e: Exception) {
//                                    e.printStackTrace()
//                                }
//                                tab.setupWithViewPager(viewFlipper)
//                            }
//                        }
//
            Instances.showBlur(
                requireContext(), it.shopData?.imgLink.toString(), binding.imageView52
            )
            progress.isVisible = false
        }

        else
            progress.isVisible = false
//                    toast(activity, "No result found")

    }


    class FilterMain(
        private val data: List<Filterdata>,
        private val items: List<Valuess>,
        private var activity: Fragment

    ) : RecyclerView.Adapter<FilterMain.ViewHolder>() {
        lateinit var listener: IFilterConstraintListener
        private var highlightItem = 0

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val order_id = itemView.findViewById<Button?>(R.id.buttontext)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_sortleft,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return data.size
        }


        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.order_id.setOnClickListener {}


            holder.order_id?.text = data.get(position).type_label
            holder.order_id?.isSelected = data[position].isChecked
            holder.order_id!!.setOnClickListener {
                for (newcheckeditem in data) {
                    newcheckeditem.isChecked = false
                }
                data[position].isChecked = true
                listener.onFilterItemClicked(data.get(position).type_label!!, position)
                holder.order_id.isSelected = holder.order_id.isSelected

                notifyDataSetChanged()
            }

        }
    }


}

class Filtersub(private val activity: FragmentActivity?, val data: List<Valuess>) :
    RecyclerView.Adapter<Filtersub.ViewHolder>() {
    private var filters = ArrayList<String>()
    var filterString = " "

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val order_id = itemView.findViewById<CheckBox?>(R.id.checkBox8)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sort_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var newid: String? = null
        holder.order_id?.text = data.get(position).name
        newid = data.get(position).id
        holder.order_id!!.isChecked = data[position].isSelected


        holder.itemView.checkBox8.setOnCheckedChangeListener { buttonView, isChecked ->
            data[position].isSelected = isChecked

            if (isChecked) {
                filters.add(newid!!)
            } else {
                filters.remove(newid)
            }
            filterString = filters.joinToString(",")
        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.itemView.checkBox8.setOnCheckedChangeListener(null);
        super.onViewRecycled(holder)
    }

}



package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.navigation.NavigationView
import com.vrise.R
import com.vrise.bazaar.ex.adapters.CategorySideAdapter
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.mainmodels.Response
import com.vrise.bazaar.ex.model.submodels.BillCategory
import com.vrise.bazaar.ex.model.submodels.ShopCategory
import com.vrise.bazaar.ex.model.submodels.Shoppss
import com.vrise.bazaar.ex.model.submodels.Taglist
import com.vrise.bazaar.ex.model.temp.AddressItem
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.DashBoardViewModel
import com.vrise.bazaar.ex.shop.interfaces.RegistrationViewModel
import com.vrise.bazaar.ex.shop.pages.SearchShopDetail
import com.vrise.bazaar.ex.shop.pages.shop.ItemFragment
import com.vrise.bazaar.ex.shop.pages.shop.ShopsFragment
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Instances.printAny
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.NOTIFYCOUNT
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.ex.util.custom.SliderAdapter
import com.vrise.databinding.DashBoardFragmentBinding
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.android.synthetic.main.address_list_fragment.*
import kotlinx.android.synthetic.main.dash_board_fragment.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.item_store.view.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import kotlinx.android.synthetic.main.nav_container.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE"
)
class DashBoardFragment : Fragment() {
    private lateinit var viewModel: DashBoardViewModel
    lateinit var binding: DashBoardFragmentBinding
    var navigation: NavigationView? = null
    var SPLASH_TIME_OUT = 500

    private var viewModel1: RegistrationViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dash_board_fragment, container, false)

        Instances.hideKeyboard(activity)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       BaseApp.enableGlobal = 2
//      navigationView.getMenu().findItem(R.id.your_item_id).setEnabled(false);
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView1009.text = "VRise"
        include4?.visibility = View.VISIBLE
        Instances.hideKeyboard(activity)
        Preference.clearOne(activity, DATAFILE, Preference.CATEGORY)
        viewModel1 = ViewModelProviders.of(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(RegistrationViewModel::class.java)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(DashBoardViewModel::class.java)
        recyclerView1.setFocusable(false);

       viewFlipper.requestFocus();
        Handler().postDelayed(Runnable {
            getDashs()
            getAddressData()
        }, SPLASH_TIME_OUT.toLong())


        constraintLayout40.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_homeFragment_to_tabFragment

            )
        }
    }

    @SuppressLint("SetTextI18n")
    public fun getDashs() {

        include4.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getDashboardItems(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        include4.visibility = View.GONE

                        if (it.userdata != null) {
                            (activity as DashBoardContainer).setUserProfile(
                                it.userdata.name.toString(),
                                it.userdata.mobile.toString()
                            )
                            Preference.put(
                                activity, Preference.NAME, it.userdata.name.toString(), DATAFILE
                            )
                            Preference.put(
                                activity, Preference.IMAGE, it.userdata.image.toString(), DATAFILE
                            )
                        }
                        if (it.addressStatus == "1") {
                            if (it.defaultAddress != null) {
                                Preference.put(
                                    activity,
                                    Preference.LOC,
                                    it.defaultAddress.address2.toString(),
                                    DATAFILE
                                )
                            }
                        }
                        Preference.put(activity, Preference.MAXRAD, it.maxRadius, DATAFILE)
                    }
                    Preference.put(
                        activity,
                        NOTIFYCOUNT,
                        (it?.notfiCount ?: 0).toString(),
                        DATAFILE
                    )

                    if (activity is DashBoardContainer) {
                        (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                        (activity as DashBoardContainer).setNotificationBadge()
                    }

                    recyclerView.adapter = FavoriteShopAdapter(
                        activity, viewModel, it?.billCategories
                    )


                    recyclerView11.adapter = FavouritesAdapter(activity,viewModel,viewModel1!!,it?.taglist?.data?.shops,this)

                    val shopCategoryItems = ArrayList<ShopCategory?>()
                    for (i in 0 until (it?.shopCategories?.size ?: 0)) {
                        if (it?.shopCategories?.get(i) != null) {
                            shopCategoryItems.add(it.shopCategories[i])
                        }
                    }
                    if (!shopCategoryItems.isNullOrEmpty()) {
                        textView2.text = "Shop By Category"
                        if (shopCategoryItems.size < 4) {
                            for (i in shopCategoryItems.size until 4) {
                                shopCategoryItems.add(
                                    ShopCategory(
                                        "", "", "", "", "", "", "", "", "", "", "", 0, "", null
                                    )
                                )
                            }
                        } else if (shopCategoryItems.size >= 4) {
                            shopCategoryItems.add(
                                2, ShopCategory(
                                    "", "", "", "", "", "", "", "", "", "", "", 0, "", null
                                )
                            )
                        }
                    } else {
                        textView2.text = ""
                    }

                    printAny(shopCategoryItems)


                    if (it != null) {
                        recyclerView1.adapter =
                            ShopByCategoryAdapter(activity, viewModel, it.shopCategories)

                    }
//                    (recyclerView1.layoutManager as GridLayoutManager).spanSizeLookup =
//                        object : GridLayoutManager.SpanSizeLookup() {
//                            override fun getSpanSize(position: Int): Int {
//                                return if (recyclerView1.adapter?.getItemViewType(position) == 0) 2 else 1
//                            }
//                        }

                    if (it?.banner != null) {
                        if (it.banner.isNotEmpty()) {
                            viewFlipper?.adapter = SliderAdapter(activity, it.banner)
                            try {
                                if (it.banner.size >= 2) {
                                    var currentIndex = 0
                                    timeCounter = object : Runnable {
                                        override fun run() {
                                            if ((currentIndex + 1) > it.banner.size) {
                                                currentIndex = 0
                                            } else {
                                                currentIndex++
                                            }
                                            viewFlipper?.currentItem = currentIndex
                                            handler.postDelayed(this, 3 * 1000)
                                        }
                                    }
                                    handler.postDelayed(timeCounter as Runnable, 3 * 1000)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            tab.setupWithViewPager(viewFlipper)
                        }
                    }

                    Preference.clearOne(activity, DATAFILE, Preference.CATEGORY)
                    include4.visibility = View.GONE

                })
                viewFlipper?.clipToPadding = false
                viewFlipper?.setPadding(30, 0, 30, 0)
                viewFlipper?.pageMargin = 8
                if (viewFlipper?.childCount ?: 0 > 1) {
                    viewFlipper?.currentItem = 1
                }
            } else {
                Instances.toast(activity, getString(R.string.no_internet))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (timeCounter != null) {
                handler.removeCallbacks(timeCounter!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var handler = Handler()
    private var timeCounter: Runnable? = null

    private fun getAddressData() {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getAddress(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN)
                    )
                )?.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        (activity as DashBoardContainer).setSpinnerAddress(it as List<AddressItem>)
                    }
                })
            } else {
                Instances.toast(activity, getString(R.string.no_internet))
                progress?.visibility = View.GONE
            }
        }
    }

    class ShopByCategoryAdapter(
        val activity: FragmentActivity?,
        val viewModel: DashBoardViewModel?,
        val dataItem: List<ShopCategory?>?
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            return if (viewType == 0) {
//                OffersHolder(
//                    LayoutInflater.from(parent.context).inflate(
//                        R.layout.item_inshop, parent, false
//                    )
//                )
//            } else {
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_shop_by_category, parent, false
                    )
                )
//            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 2) {
                0
            } else {
                1
            }
        }

        override fun getItemCount(): Int {
            return dataItem?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ViewHolder) {
                if (dataItem != null) {
                    if (!dataItem[holder.adapterPosition]?.name.isNullOrBlank()) {
                        holder.item?.setBackgroundResource(R.drawable.new_wh)
                        holder.textView4?.text = dataItem[holder.adapterPosition]?.name.toString().split(' ').joinToString(" ") { it.capitalize() }
                        Instances.showBlur(
                            activity,
                            dataItem[holder.adapterPosition]?.imgLink.toString(),
                            holder.imageView4
                        )
                        holder.item?.setOnClickListener(object : ClickListener() {
                            override fun onOneClick(v: View) {
                                Preference.put(
                                    activity,
                                    Preference.CATEGORY,
                                    dataItem[holder.adapterPosition]?.id.toString(),
                                    DATAFILE
                                )
//						activity?.startActivity(
//									Intent(
//										activity,
//										BazaarContainer::class.java
//									)

//                                activity?.supportFragmentManager?.beginTransaction()
//                                    ?.replace(R.id.nav_host_fragment, TabFragment())?.addToBackStack(null)
//                                    ?.commit()
                             v.findNavController().navigate(
                                    R.id.action_homeFragment_to_shopFragment

                                )
                            }
                        })
                    } else {
                        holder.item?.setOnClickListener { }
                        holder.item?.setBackgroundResource(0)
                    }
                }
            } else if (holder is OffersHolder) {
                holder.imageView42?.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {
                        Toast.makeText(
                            activity,
                            "Not applicable without a Confirmed Order ",
                            Toast.LENGTH_LONG
                        ).show()

//						v.findNavController().navigate(R.id.action_homeFragment_to_offerList)
                    }
                })
                holder.imageView44?.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {
                        v.findNavController()
                            .navigate(R.id.action_homeFragment_to_referralFragment2)

                    }
                })
            }
        }

        class OffersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView44 = itemView.findViewById<ImageView?>(R.id.imageView44)
            val imageView42 = itemView.findViewById<ImageView?>(R.id.imageView42)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item = itemView.findViewById<CardView?>(R.id.card12)
            val imageView4 = itemView.findViewById<ImageView?>(R.id.imageView4)
            val textView4 = itemView.findViewById<TextView?>(R.id.textView4)
        }

    }


    class FavoriteShopAdapter(
        val activity: FragmentActivity?,
        val viewModel: DashBoardViewModel?,
        val dataItem: List<BillCategory?>?
    ) : RecyclerView.Adapter<FavoriteShopAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_store, parent, false
                )
            )
        }

        override fun getItemCount(): Int {
//            return dataItem?.size ?: 0
            return 5
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            if (dataItem.isNullOrEmpty()) {
//            } else {
//                Instances.showBlur(
//                    activity,
//                    dataItem[holder.adapterPosition]?.imgLink.toString(),
//                    holder.imageView2
//                )
//                holder.textView3?.text = dataItem[holder.adapterPosition]?.name.toString()
//                holder.item?.setOnClickListener(object : ClickListener() {
//                    override fun onOneClick(v: View) {
//                        Instances.InternetCheck { internet ->
//                            if (internet) {
//                                viewModel?.news(
//                                    Request(
//                                        utoken = Preference.get(activity, DATAFILE, TOKEN)
//                                    )
//                                )?.observe(activity!!, Observer {
//                                    if (it != null) {
//                                        val bundles = Bundle()
//                                        bundles.putString(
//                                            com.vrise.bazaar.newpages.utilities.Constants.TYPE_OF,
//                                            it.action
//                                        )
//                                        bundles.putString(
//                                            Constants.ID,
//                                            Preference.get(activity, DATAFILE, TOKEN)
//                                        )
//                                        when (it.action) {
//                                            com.vrise.bazaar.newpages.utilities.Constants.USER_TYPE -> {
//                                                val intent = Intent(
//                                                    activity,
//                                                    ContainerRegistration::class.java
//                                                )
//                                                intent.putExtras(bundles)
//                                                activity.startActivity(intent)
//                                            }
//                                            com.vrise.bazaar.newpages.utilities.Constants.SDASH -> {
//                                                activity.startActivity(
//                                                    Intent(
//                                                        activity,
//                                                        SubContainer::class.java
//                                                    )
//                                                )
//                                            }
///*
//											com.ibrbazaar.bazaar.newpages.utilities.Constants.ADASH -> {
//												activity.startActivity(Intent(activity, AgentContainer::class.java))
//											}
//											com.ibrbazaar.bazaar.newpages.utilities.Constants.EXECUTIVECODE -> {
//												val intent = Intent(activity, ContainerRegistration::class.java)
//												intent.putExtras(bundles)
//												activity.startActivity(intent)
//											}
//*/
//                                            com.vrise.bazaar.newpages.utilities.Constants.CONTACTAGENTS -> {
//                                                val intent = Intent(
//                                                    activity,
//                                                    ContainerRegistration::class.java
//                                                )
//                                                intent.putExtras(bundles)
//                                                activity.startActivity(intent)
//                                            }
//                                        }
//                                    }
//                                })
//                            } else {
//                                Instances.toast(activity, activity?.getString(R.string.no_internet))
//                            }
//                        }
//                    }
//                })
//            }
            holder.imgStore.setImageResource(R.drawable.newsone)
            holder.txtRating.text = "4.0"
            holder.txtStoreName.text = "Meet and Eat"
            holder.txtDistance.text = "3.5 km"
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item = itemView.findViewById<ConstraintLayout?>(R.id.container)
            val imgStore = itemView.findViewById<ImageView?>(R.id.imgStore)
            val txtDistance = itemView.findViewById<TextView?>(R.id.txtRatings)
            val txtRating = itemView.findViewById<TextView?>(R.id.txtDistance)
            val txtStoreName = itemView.findViewById<TextView?>(R.id.txtStoreName)
        }

    }
//    public fun gotoShopFragment(id:String){
//        findNavController().navigate(
//            R.id.action_finderFragment2_to_searchShopDetail,
//            bundleOf(
//                Constants.ID to (id)
//            )
//        )
//    }
}

class FavouritesAdapter(val activity: FragmentActivity?,
val viewModel: DashBoardViewModel?,
                        val viewModel1: RegistrationViewModel,
val shop: List<Shoppss?>?,val fragment: DashBoardFragment)
    : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fav_store, parent, false
            )
        )
    }


    override fun getItemCount(): Int {
        return shop?.size ?: 0
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val item = itemView.findViewById<CardView?>(R.id.cardView22)
        val imgStore = itemView.findViewById<ImageView?>(R.id.imgStordde)
//        val txtDistance = itemView.findViewById<TextView?>(R.id.txtRatings)
        val txtRating = itemView.findViewById<TextView?>(R.id.txtDistance)
        val txtStoreName = itemView.findViewById<TextView?>(R.id.txtStoreNames)
        val cont = itemView.findViewById<CardView?>(R.id.cardView22)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val radius = 25
        Glide.with(activity!!)
            .load(shop?.get(holder.adapterPosition)?.imgLink)
            .transform(RoundedCorners(radius))
            .thumbnail(0.1f)
            .into(holder.imgStore)
//        holder.txtRating.text = shop?.get(holder.adapterPosition)?.distance?.toDouble()?.let {
//            Math.round(it).toString() }
        holder.txtStoreName.text = shop?.get(holder.adapterPosition)?.storeName
//        holder.txtDistance.text =shop?.get(holder.adapterPosition)?.rating
//
//        Instances.showBlur(
//            activity,
//            shop!![holder.adapterPosition]?.imgLink.toString(),
//            holder.imgStore
//        )


        holder.cont.setOnClickListener {
            val myFragment: Fragment = SearchShopDetail()
            val args = Bundle()
            args.putString(Constants.ID, shop?.get(holder.adapterPosition)?.id ?: "")
            args.putString(Constants.NAME, shop?.get(holder.adapterPosition)?.storeName ?: "")
//
//            myFragment.setArguments(args)
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.nav_host_fragment, myFragment)?.addToBackStack(null)
//                ?.commit()

            it.findNavController().navigate(
                R.id.action_homeFragment_to_searchShopDetail,args)


        }



        holder.itemView.imgFavorite.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {

                val display =
                    androidx.appcompat.app.AlertDialog.Builder(activity!!)
                        .setCancelable(true).setMessage("Are you sure you want to remove from Favourites?")
                        .setNegativeButton("YES") { dialog1, _ ->


                            viewModel1.addTaag(
                                Request(
                                    utoken = Preference.get(
                                        activity,
                                        Preference.DATAFILE,
                                        Preference.TOKEN
                                    ),
                                    seller_id =  shop!![holder.adapterPosition]?.id,
                                    sellerref = ""
                                )
                            )?.observe(activity!!, Observer {
                                    fragment.onCreate(null)

                                fragment.getDashs()
//                                v?.findNavController()?.navigate(
//                                    R.id.action_homeFragment_to_shopFragment
//                                )


                            })





                            dialog1?.dismiss()
                        }
                        .setPositiveButton("NO") { dialog1, _ ->

                            dialog1?.dismiss()
                        }.create()


                display.setOnShowListener {
                    display.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        ContextCompat.getColor(
                            activity!!,
                            R.color.green_
                        )
                    )
                    display.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                        ContextCompat.getColor(
                            activity,
                            R.color.green_
                        )
                    )
                }
                display.show()














            }
        })
        }


}


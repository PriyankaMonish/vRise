package com.vrise.bazaar.ex.shop.pages.shop


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopsItem
import com.vrise.bazaar.ex.model.submodels.Data
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.RegistrationViewModel
import com.vrise.bazaar.ex.shop.interfaces.ShopsViewModel
import com.vrise.bazaar.ex.shop.pages.main.TabFragment
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.InternetCheck
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.CARTCOUNT
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.MAXRAD
import com.vrise.bazaar.ex.util.Preference.STORE
import com.vrise.databinding.ItemStoreBinding
import com.vrise.databinding.ShopFragmentBinding
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.activity_container_shop.container
import kotlinx.android.synthetic.main.add_delivery_address.*
import kotlinx.android.synthetic.main.bootomsheet.*
import kotlinx.android.synthetic.main.change_radius_layout.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.item_store.view.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import kotlinx.android.synthetic.main.no_item_fragment.data_availability
import kotlinx.android.synthetic.main.no_shop_fragment.*
import kotlinx.android.synthetic.main.shop_fragment.*
import java.util.*
import kotlin.math.abs
import kotlin.text.toInt as toInt1


class ShopsFragment : Fragment() {
    private var viewModel: ShopsViewModel? = null
    private var viewModel1: RegistrationViewModel? = null
    lateinit var binding: ShopFragmentBinding

    var sorting = ""
    private var mBottomSheetBehavior1: BottomSheetBehavior<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.shop_fragment, container, false)
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        (activity as DashBoardContainer).txtTitle?.text = "VRise"

        return binding.root
    }

    override fun onResume() {
        initView()
//        onCreate(null)
        val storeRadius =
            if (Preference.get(activity, DATAFILE, STORE).isNullOrBlank()) "0" else Preference.get(
                activity, DATAFILE, STORE
            )
       getShops(storeRadius?.toInt1() ?: 0)
        if (Preference.get(context, DATAFILE, Preference.LOC).isNullOrBlank()) {
            openDeliveryAddressPopUp()
        }
        super.onResume()
    }

    private fun initView() {
        progress.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView1009.text = "Vrise"


        try {
            appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    //Collapsed
                    binding.textView106?.expand()
                } else {
                    //Expanded
                    binding.textView106?.collapse()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.textView106?.setContent("")
        // binding.textView254.setText("More Shops")
        binding.textView106?.expand()

        activity?.let { activity ->
            ContextCompat.getDrawable(activity, R.drawable.ic_location)?.let {
                binding.textView106?.setIconActionButton(
                    it
                )
            }
        }
        binding.textView254!!.visibility = View.GONE
        binding.textView106!!.visibility = View.GONE
        binding.imageView69!!.visibility = View.GONE
        viewModel1 = ViewModelProviders.of(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(RegistrationViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(
            ShopsViewModel::class.java
        )


        val storeRadius =
            if (Preference.get(activity, DATAFILE, STORE).isNullOrBlank()) "0" else Preference.get(
                activity, DATAFILE, STORE
            )
        getShops(storeRadius?.toInt1() ?: 0)
    }

     fun getShops(store_radius: Int) {
        progress.visibility = View.VISIBLE
        InternetCheck { internet ->
            if (internet) {
                viewModel?.shoList(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, Preference.TOKEN),
                        category_id = Preference.get(activity, DATAFILE, Preference.CATEGORY),
                        store_radius = store_radius.toString(),
                        sorting = if (sorting == "rating") "rating" else if (sorting == "packing_time")
                            "packing_time" else if (sorting == "distance") "distance" else " "
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this@ShopsFragment, Observer {
                    if (it == null) {

                        imageView69.setOnClickListener {
                            if (mBottomSheetBehavior1!!.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                                mBottomSheetBehavior1!!.setState(BottomSheetBehavior.STATE_EXPANDED);

                            } else {
                                mBottomSheetBehavior1!!.setState(BottomSheetBehavior.STATE_COLLAPSED);

                            }
                        }
                        textView9.text = ""
//						textView8.text = ""
                        (activity as DashBoardContainer).setCartBadge(
                            if (Preference.get(activity, DATAFILE, CARTCOUNT).isNullOrBlank()) {
                                0
                            } else {
                                Preference.get(activity, DATAFILE, CARTCOUNT)?.toInt1() ?: 0
                            }
                        )
                        data_availability.visibility = View.VISIBLE
                        textView258?.text =
                            if (Preference.get(activity, DATAFILE, STORE).isNullOrBlank()) {
                                "No Shops Available in this area"
                            } else {
                                if (Preference.get(
                                        activity, DATAFILE, STORE
                                    ) == "0" || Preference.get(activity, DATAFILE, STORE) == "null"
                                ) {
                                    "No Shops Available in this area"
                                } else {
                                    "No Shops available within ${
                                        Preference.get(
                                            activity, DATAFILE, STORE
                                        )
                                    } kms"
                                }
                            }
                        button26.setOnClickListener { view ->
                            openRadius(it)
                        }
                    } else {
                        button26.setOnClickListener { }
//						textView8.text = "Recommended Items"
                        textView9.text = "Stores Nearby"
                        (activity as DashBoardContainer).setCartBadge(it.cartCount ?: 0)
                        data_availability.visibility = View.GONE
                    }

                    activity?.let { activity ->
                        //Todo unservicable shops design
//						binding.pager.adapter = ShopsAdapter(
//							activity, viewModel, it?.recommShop
//						)
//						if (it?.recommShop.isNullOrEmpty()) {
//							binding.tab.visibility = View.GONE
//						} else {
//							if ((it?.recommShop?.size ?: 0) > 1) {
//								binding.tab.visibility = View.VISIBLE
//							} else {
//								binding.tab.visibility = View.GONE
//							}
//						}
                        binding.textView106!!.setOnClickListener { views ->
                            openRadius(it)
                        }


                        binding.imageView69!!.setOnClickListener {
                            val dialog = BottomSheetDialog(requireContext())
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                            dialog.setCancelable(true)
                            dialog.window?.setLayout(
                                WindowManager.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.WRAP_CONTENT
                            )
                            dialog.setContentView(R.layout.bootomsheet)
                            dialog.show()


                            dialog.radioGroup5.setOnCheckedChangeListener {
                                /*stay down*/ group, checkedId ->
                                try {
//				if (amount > 0) {
                                    sorting = when (checkedId) {
                                        R.id.rating -> {
                                            "rating"
                                        }
                                        R.id.distance -> {
                                            "distance"
                                        }
                                        R.id.packingtime -> {
                                            "packing_time"
                                        }

                                        else -> {
                                            ""
                                        }
                                    }
//				} else {
//					radioButton4.isChecked = false
//					radioButton6.isChecked = false
//					radioButton2.isChecked = false
//					radioButton3.isChecked = false
//					paymentType = ""
//				}
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }

                            dialog.imageView67.setOnClickListener {
                                dialog.dismiss()
                            }

                            dialog.textView310.setOnClickListener {
                                dialog.radioGroup5.clearCheck()

                            }

                            dialog.button27.setOnClickListener {
                                dialog.button27.setBackgroundColor(
                                    this.resources.getColor(R.color.colorAccent)
                                );

                                dialog.radioGroup5.visibility = View.VISIBLE

                            }


                            dialog.button28.setOnClickListener(object : View.OnClickListener {
                                override fun onClick(view: View?) {
                                    viewModel?.shoList(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            category_id = Preference.get(
                                                activity,
                                                DATAFILE,
                                                Preference.CATEGORY
                                            ),
                                            store_radius = store_radius.toString(),
                                            sorting = if (sorting == "rating") "rating" else if (sorting == "packing_time")
                                                "packing_time" else "distance"
                                        )
                                    )?.observe(
                                        viewLifecycleOwnerLiveData.value ?: this@ShopsFragment,
                                        Observer {
                                            getShops(store_radius)
                                        })

                                    dialog.dismiss()
                                }
                            })
                        }
                    }

                    binding.textView106!!.setContent(
                        "Shops in ${
                            it?.storeRadius ?: Preference.get(
                                activity, DATAFILE, STORE
                            ) ?: 0
                        } Kms"
                    )
                    (activity as DashBoardContainer).setNotificationBadge()

                    binding.recyclerView3.setLayoutManager(GridLayoutManager(context, 2))
                    binding.recyclerView3.adapter = MoreShopsAdapter(
                        activity,this, viewModel, it?.shops,viewModel1!!
                    )

                    progress.visibility = View.GONE
                })

            } else {
                binding.textView9.text = ""
                button26?.setOnClickListener {
                    toast(container, getString(R.string.no_internet))
                }
                binding.textView106!!.setOnClickListener {
                    toast(container, getString(R.string.no_internet))
                }
                toast(container, getString(R.string.no_internet))
                progress.visibility = View.GONE
            }
        }
    }

    private fun openRadius(it: Data?) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.change_radius_layout)
        val maxValue = it?.maxRadius?.toInt1() ?: if (Preference.get(
                activity, DATAFILE, MAXRAD
            ).toString().isBlank() || Preference.get(
                activity, DATAFILE, MAXRAD
            ) == "0" || Preference.get(activity, DATAFILE, MAXRAD) == "null"
        ) {
            if (Preference.get(activity, DATAFILE, STORE).isNullOrEmpty()) {
                0
            } else {
                if (Preference.get(activity, DATAFILE, STORE).toString() == "null") {
                    0
                } else {
                    if (Preference.get(activity, DATAFILE, STORE).toString().isDigitsOnly()) {
                        Preference.get(activity, DATAFILE, STORE).toString().toInt1()
                    } else {
                        0
                    }
                }
            }
        } else {
            if (Preference.get(activity, DATAFILE, MAXRAD).toString().isDigitsOnly()) {
                Preference.get(activity, DATAFILE, MAXRAD).toString().toInt1()
            } else {
                0
            }
        }
        val stepSize = 5
        val values = arrayOfNulls<String>(maxValue + stepSize + 1)
        for (i in 0 until (maxValue + stepSize) step stepSize) {
            values[i] = i.toString()
        }
        val newVal = values.filterNotNull().toTypedArray()
        dialog.numberPicker.maxValue = newVal.size - 1
        dialog.numberPicker.displayedValues = newVal
        try {
            dialog.numberPicker.value = ((it?.storeRadius?.toInt1()) ?: (Preference.get(
                activity, DATAFILE, STORE
            ))?.toInt1() ?: stepSize) / stepSize
        } catch (e: Exception) {
            dialog.numberPicker.value = 0
            e.printStackTrace()
        }
        dialog.button23.setOnClickListener {
            dialog.dismiss()
        }
        dialog.button22.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                Preference.put(
                    activity, STORE, (dialog.numberPicker.value * stepSize).toString(), DATAFILE
                )
                binding.textView106!!.setContent("Shops in ${(dialog.numberPicker.value * stepSize)} Kms")

                getShops(
                    try {
                        (dialog.numberPicker.value * 5).toString().toInt1()
                    } catch (e: Exception) {
                        0
                    }
                )
                dialog.dismiss()
            }
        })
        dialog.show()
    }

    class MoreShopsAdapter(
        val activity: FragmentActivity?,
        val frag :ShopsFragment,
        val viewModel: ShopsViewModel?,
        val dataItem: List<ShopsItem?>?,
        val viewModel1:RegistrationViewModel
    ) : RecyclerView.Adapter<MoreShopsAdapter.ViewHolder>() {
        @SuppressLint("NewApi")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//			R.layout.item_store,
            val moreShopsBinding =
                ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            moreShopsBinding.imgStore.setClipToOutline(true);
            moreShopsBinding.txtRatings.visibility = View.VISIBLE

            moreShopsBinding.root.post {
                moreShopsBinding.root.layoutParams.height = parent.width / 2
                moreShopsBinding.root.requestLayout()
            }
            return ViewHolder(moreShopsBinding)
        }

        override fun getItemCount(): Int {
            return dataItem?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (dataItem != null) {


                holder.bind(activity, dataItem[holder.adapterPosition],frag)

                holder.itemView.imgFavorite.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {
                        viewModel1.addTaag(
                            Request(
                                utoken = Preference.get(
                                    activity,
                                    Preference.DATAFILE,
                                    Preference.TOKEN
                                ),
                                seller_id = dataItem[holder.adapterPosition]?.id,
                                sellerref = ""
                            )
                        )?.observe(activity!!, Observer {
                            frag.onCreate(null)
                            val storeRadius =
                                if (Preference.get(activity, DATAFILE, STORE).isNullOrBlank()) "0" else Preference.get(
                                    activity, DATAFILE, STORE
                                )
                            frag.getShops(storeRadius?.toInt1() ?: 0)
                        })
                    }
                })

                val radius = 15
                Glide.with(activity!!)
                    .load(dataItem[holder.adapterPosition]?.tagicon.toString())
                    .transform(RoundedCorners(radius))
                    .thumbnail(0.1f)
                    .into(holder.itemView.imgFavorite)

                Glide.with(activity!!)
                    .load(dataItem[holder.adapterPosition]?.imgLink.toString())
                    .transform(RoundedCorners(radius))
                    .thumbnail(0.1f)
                    .into(holder.itemView.imgStore)

                holder.itemView.container.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        val display =
                            androidx.appcompat.app.AlertDialog.Builder(activity)
                                .setCancelable(true).setMessage("Are you sure you want to add as Favourites?")
                                .setNegativeButton("YES") { dialog1, _ ->


                                    viewModel1.addTaag(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                Preference.DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            seller_id = dataItem[holder.adapterPosition]?.id,
                                            sellerref = ""
                                        )
                                    )?.observe(activity, Observer {

                                        frag.onCreate(null)
                                        val storeRadius =
                                            if (Preference.get(activity, DATAFILE, STORE).isNullOrBlank()) "0" else Preference.get(
                                                activity, DATAFILE, STORE
                                            )
                                        frag.getShops(storeRadius?.toInt1() ?: 0)

                                    })

                                    dialog1?.dismiss()
                                }
                                .setPositiveButton("NO") { dialog1, _ ->

                                    dialog1?.dismiss()
                                }.create()


                        display.setOnShowListener {
                            display.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                                ContextCompat.getColor(
                                    activity,
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


                        return true
                    }
                })

            }
        }

        class ViewHolder(binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root) {
            private var shopsBinding: ItemStoreBinding = binding

            @SuppressLint("SetTextI18n")
            fun bind(activity: FragmentActivity?, item: ShopsItem?,frag: ShopsFragment) {
                item?.let {
                    shopsBinding.dataItems = item

                    var name = item.storeName
//					shopsBinding.root.findViewById<TextView>(R.id.textView268).text = ""
                    if (item.service_status == "1") {
                        if (((item.open_status == "0") || (item.open_status == "1"))) {
                            shopsBinding.root.findViewById<ConstraintLayout?>(R.id.container)
                                .setOnClickListener(object : ClickListener() {
                                    override fun onOneClick(v: View) {
                                        if (item.storeTemp != "1") {
                                                if (frag.parentFragment is TabFragment) {
                                                Log.d("Tab FRAGMENT", "TRUE")

                                                    val tabs = frag.parentFragment as TabFragment
                                                    tabs.gotoShopFragment(item.id!!, item.storeName!!)
                                                }else {
                                                    Log.d("Tab FRAGMENT", "FALSE")
                                                    v.findNavController().navigate(
                                                        R.id.action_shopFragment_to_searchShopDetail2,
                                                        bundleOf(
                                                            Constants.ID to (item.id),
                                                            Constants.NAME to (item.storeName)
                                                        )
                                                    )
                                                }
                                            }

                                         else {

                                            if (frag.parentFragment is TabFragment) {
                                                Log.d("Tab FRAGMENT", "TRUE")

                                                val tabs = frag.parentFragment as TabFragment
                                                tabs.gotoShopFragment(item.id!!, item.storeName!!)
                                            }else {
                                                v.findNavController().navigate(
                                                    R.id.action_shopFragment_to_searchShopDetail2,
                                                    bundleOf(
                                                        Constants.ID to (item.id),
                                                        Constants.NAME to (item.storeName)
                                                    )
                                                )
                                            }
                                            }

                                                }

                                })
                        }

                    }
                }
                activity?.let { shopsBinding.context = activity }
                shopsBinding.executePendingBindings()
            }
        }


    }

    private fun openDeliveryAddressPopUp() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_delivery_address)
        dialog.button15.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {

                findNavController().navigate(R.id.addressListFragment)

                dialog.dismiss()
            }
        })
        dialog.button19.setOnClickListener {
            dialog.dismiss()
            activity?.onBackPressed()
        }
        dialog.show()
    }



}


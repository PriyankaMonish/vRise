package com.vrise.bazaar.ex.shop.pages.shop

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.*
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.shop.pages.main.ShippingMethod
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Instances.openListPoPUp
import com.vrise.bazaar.ex.util.Instances.showBlur
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference.CARTCOUNT
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.bottom_schedule_spot.*
import kotlinx.android.synthetic.main.fragment_request_order.*
import kotlinx.android.synthetic.main.item_detail_fragment.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.item_schedule_items.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ItemDetailFragment : Fragment() {
    private lateinit var viewModel: ItemDetailViewModel
    private var selectedSize: SizeStockPriceItem? = null
    private var strs: Array<String?>? = null
    private var shippingId: String? = null

    var result: List<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rorot = inflater.inflate(R.layout.item_detail_fragment, container, false)
        return rorot


    }

    override fun onResume() {
        super.onResume()
        try {
            floatingActionButton8.count =
                if (Preference.get(activity, DATAFILE, CARTCOUNT).toString().isDigitsOnly()) {
                    Preference.get(activity, DATAFILE, CARTCOUNT).toString().toInt()
                } else {
                    0
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(ItemDetailViewModel::class.java)

        floatingActionButton8.count = try {
            Preference.get(activity, DATAFILE, CARTCOUNT)?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }

        button6.visibility = View.GONE
        (activity as DashBoardContainer).textView1009.text = "Vrise"

        getData()

        floatingActionButton8.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                v.findNavController()
                    .navigate(R.id.action_navigation_item_detail_to_navigation_cart)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                var spot: DeliveryTimesItem? = null
                viewModel.getItem(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                        sprdtid = arguments?.getString(Constants.ID) ?: ""
                    )
                )?.observe(viewLifecycleOwner, Observer {


                    if (it != null) {
                        textView29.text = it.name
                        textView163.text = it.storeName

                        Instances.showBlur(activity, it.imgLink!!, imageView38)

                        textView37.text = "About"
                        if (it.deliveryNote.isNullOrBlank()) {
                            textView37.text = ""
                        }
                        textView38.text = it.deliveryNote.toString().trim()
                        if (it != null) {
                            if (it.spotDelivery == 1) {
                                if (!it.deliveryTimes.isNullOrEmpty()) {
                                    spot =
                                        it.deliveryTimes.filter { it?.spotDelivery.toString() == "1" }[0]
                                    Instances.printAny(spot)
                                }
                            }
                        }

                        imageView16.isChecked = it.favStatus == 1
                        try {
                            imageView16.setOnClickListener(object : ClickListener() {
                                override fun onOneClick(v: View) {
                                    Instances.InternetCheck { internet ->
                                        if (internet) {
                                            viewModel.setFavourite(
                                                Request(
                                                    utoken = Preference.get(
                                                        activity, DATAFILE, TOKEN
                                                    ),
                                                    fav_process = if (imageView16.isChecked) "1" else "2",
                                                    selprdt_id = it.sprdtid
                                                )
                                            )?.observe(viewLifecycleOwner, Observer { s ->
                                                if (!s) {
                                                    imageView16.isChecked = !imageView16.isChecked
                                                }
                                            })
                                            object : CountDownTimer(3000, 10) {
                                                override fun onFinish() {
                                                    imageView16.isEnabled = true
                                                }

                                                override fun onTick(millisUntilFinished: Long) {
                                                    imageView16.isEnabled = false
                                                }
                                            }

                                        } else {
                                            toast(activity, getString(R.string.no_internet))
                                        }
                                    }
                                }
                            })
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        textView221.visibility = View.GONE
                        imageButton.visibility = View.GONE
                        textView226.text = ""
                        textView32.text = ""
                        constraint.setBackgroundResource(0)

                        if (it.availableStatus.toString() != "1") {
                            textView257.visibility = View.VISIBLE
                            button6.visibility = View.GONE
                            button5.visibility = View.GONE
                            imageButton.visibility = View.GONE
                            imageButton.setOnClickListener {}
                            button5.setOnClickListener {}
                            button6.setOnClickListener {}
                        } else {
                            if (it.schedule_status.toString() != "1") {
                                button5.visibility = View.GONE
                            } else {
                                button5.visibility = View.GONE
                                button5.setOnClickListener { view ->
                                    if (it.sizeStockPrice.isNullOrEmpty()) {
                                        toast(activity, "item not available")
                                    } else {
                                        if (selectedSize == null) {
                                            toast(activity, "select an item to order")
                                        } else {
                                            orderSlot(
                                                it.sprdtid, java.text.SimpleDateFormat(
                                                    "dd-MM-yyyy", Locale.ENGLISH
                                                ).format(
                                                    Calendar.getInstance().time
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                            button6.visibility = View.GONE
                            imageButton.visibility = View.GONE
                            textView257.visibility = View.GONE

                            if (it.sizeStockPrice != null) {
                                if (!it.sizeStockPrice.isEmpty()) {
                                    textView226.text = it.sizeStockPrice[0]?.sizeName.toString()
                                    textView32.text = ""
                                    if (it.sizeStockPrice[0]!!.pickup_discount != " ") {
                                        textView298.visibility = View.GONE
                                    } else {
                                        textView298.text =
                                            it.sizeStockPrice[0]?.pickup_discount.toString()
                                    }
                                    val precisions = DecimalFormat("0.00")

     textView221.setText( "₹" + "" + (String.format(precisions.format(it?.sizeStockPrice?.get(0)?.sizePrice))))
                                    selectedSize = it.sizeStockPrice[0]
                                    textView221.visibility = View.VISIBLE
                                    if (it.sizeStockPrice.size > 1) {
                                        constraint.setBackgroundResource(R.drawable.rect_shape_drop_down)
                                        textView226.text = ""
                                        textView32.text = it.sizeStockPrice[0]?.sizeName.toString()
                                        imageButton.visibility = View.VISIBLE
                                    }
                                } else {
                                    textView226.text = ""
                                    textView32.text = "item not available"
                                    toast(activity, "item not available")
                                }
                            }

                            imageButton.setOnClickListener { v ->
                                openQty(it.sizeStockPrice)
                            }



                            if (spot != null) {
                                button6.setOnClickListener { view ->
                                    if (it.sizeStockPrice.isNullOrEmpty()) {
                                        toast(activity, "no stock available for today")
                                    } else {
                                        if (selectedSize == null) {
                                            toast(activity, "select an item to order")
                                        } else {
                                            orderSpot(spot, it)
                                        }
                                    }
                                }
                                button6.visibility = View.VISIBLE
                            }

                        }

                        activity?.let { activity ->
                            when {
                                it.vegNon.toString() == "veg" -> imageView43.setImageDrawable(
                                    ContextCompat.getDrawable(activity, R.drawable.ic_vegetarian)
                                )
                                it.vegNon.toString() == "non" -> imageView43.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        activity, R.drawable.ic_non_vegetarian
                                    )
                                )
                                else -> imageView43.setImageDrawable(
                                    ContextCompat.getDrawable(activity, R.drawable.ic_non_item)
                                )
                            }
                        }
                    }

                    progress.visibility = View.GONE
                })
            } else {
                toast(activity, getString(R.string.no_internet))
                progress?.visibility = View.GONE
            }
        }
    }

    private fun openQty(dataItem: ArrayList<SizeStockPriceItem?>?) {
        if (dataItem.isNullOrEmpty()) {
            toast(activity, "item not available")
        } else {
            openListPoPUp(activity,
                dataItem as ArrayList<Any?>,
                null,
                "Options",
                object : Interfaces.ReturnAny {
                    @SuppressLint("SetTextI18n")
                    override fun getValue(values: Any?) {
                        if (values is com.vrise.bazaar.ex.model.submodels.SizeStockPriceItem) {
                            selectedSize = values
                            textView32.text = values.sizeName
                            textView221.text = " ₹" + values.sizePrice?.toDouble()?.let {
                                Math.round(
                                    it
                                )
                            }
                        }
                    }
                })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun orderSpot(item: DeliveryTimesItem?, productData: Productdata) {
        if (item != null) {
            val dialog = BottomSheetDialog(requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.setContentView(com.vrise.R.layout.bottom_schedule_spot)

            dialog.progress.visibility = View.VISIBLE

            dialog.textView40.text = (item.startTime + ":" + item.endTime).toString()
            var selected = 0
            val stock: Double

            dialog.textView206.text = selected.toString()

            if (selectedSize != null) {
                selected = 1
                dialog.textView206.text = selected.toString()
                stock = selectedSize?.stock?.toDouble() ?: 0.0
                dialog.textView204.text = "Price: ₹" + selectedSize?.sizePrice?.toDouble()?.let {
                    Math.round(
                        it
                    ).toString()
                }

//						(selectedSize?.sizePrice ?: "")

                if ((selectedSize?.stock?.toDouble() ?: 0.0) > 0.0) {
                    dialog.textView205.setOnClickListener { view ->
                        if (selected > 1) {
                            selected--
                        }
                        dialog.textView206.text = selected.toString()
                        dialog.textView204.text = "Price: ₹" + ((selectedSize?.sizePrice?.toDouble()
                            ?: 0.0) * selected).toString()
                    }

                    dialog.textView207.setOnClickListener { view ->
                        if (selected < stock) {
                            selected++
                        } else {
                            toast(activity, "item out of stock")
                        }
                        dialog.textView204.text = "Price: ₹" + ((selectedSize?.sizePrice?.toDouble()
                            ?: 0.0) * selected).toString()
                        dialog.textView206.text = selected.toString()
                    }
                } else {
                    selected = 0
                    dialog.textView206.text = selected.toString()
                    dialog.textView205.setOnClickListener {
                        toast(activity, "item not available")
                    }
                    dialog.textView207.setOnClickListener {
                        toast(activity, "item not available")
                    }
                    toast(activity, "item out of stock")
                }

            } else {
                selected = 0
                stock = 0.0
                dialog.textView206.text = selected.toString()
                dialog.textView204.text = "Price: ₹0.0"
                dialog.textView205.setOnClickListener {
                    toast(activity, "item not available")
                }
                dialog.textView207.setOnClickListener {
                    toast(activity, "item not available")
                }
            }

            dialog.button7.setOnClickListener(object : ClickListener() {
                override fun onOneClick(v: View) {
                    selected = 0
                    dialog.dismiss()
                }
            })

            dialog.button4.setOnClickListener(object : ClickListener() {
                override fun onOneClick(v: View) {
                    Instances.printAny(selectedSize)
                    Instances.printAny(selected)
                    if (selectedSize != null && selected > 0) {
                        addItem(
                            "0",
                            SimpleDateFormat(
                                "dd-MM-yyyy", Locale.ENGLISH
                            ).format(Calendar.getInstance().time),
                            productData,
                            item,
                            selectedSize,
                            selected,
                            dialog
                        )
                    } else {
                        toast(activity, "select an item")
                    }
                }
            })

            dialog.progress.visibility = View.GONE

            dialog.show()
        }
    }

    private fun addItem(
        seller: String,
        today: String?,
        productData: Any?,
        selectedSlot: DeliveryTimesItem?,
        selectedSize: SizeStockPriceItem?,
        items: Int,
        dialog: BottomSheetDialog
    ) {
        dialog.progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                if (productData is Productdata? || productData is ProductItemsList?) {
                    if (productData is Productdata) {

                        var newone = if (productData is Productdata?) productData?.id.toString()
                        else if (productData is ProductItemsList?)
                            productData?.id.toString() else ""
                        var sellerproductid =
                            if (productData is Productdata?) productData?.sprdtid.toString()
                            else if (productData is ProductItemsList?) productData?.sprdtid.toString() else ""
                        if (productData.pickup == "1") {

                            viewModel.addItem(
                                Request(
                                    changeSeller = seller,
                                    utoken = Preference.get(activity, DATAFILE, TOKEN),
                                    ecom_product_id = newone,

                                    seller_product_id = sellerproductid,
                                    product_size_id = selectedSize?.sizeId ?: "",
                                    product_quantity = items.toString(),
                                    delivery_slot_id = selectedSlot?.id ?: "",
                                    delivery_date = today.toString(),
                                    delivery_type = "2",
                                    shipping_method = "0"
                                )
                            )?.observe(viewLifecycleOwner, Observer { data ->
                                if (data != null) {
                                    if (data.shopChange == 1) {
                                        val display =
                                            androidx.appcompat.app.AlertDialog.Builder(
                                                requireActivity()
                                            )
                                                .setCancelable(true).setTitle("Replace cart item?")
                                                .setMessage(data.display.toString())
                                                .setPositiveButton("YES") { dialog1, _ ->
                                                    addItem(
                                                        "1",
                                                        today,
                                                        productData,
                                                        selectedSlot,
                                                        selectedSize,
                                                        items,
                                                        dialog
                                                    )
                                                    dialog1?.dismiss()
                                                }.setNegativeButton("NO") { dialog1, _ ->
                                                    dialog.progress.visibility = View.GONE
                                                    dialog1?.dismiss()
                                                }.create()
                                        display.setOnShowListener {
                                            display.getButton(AlertDialog.BUTTON_POSITIVE)
                                                .setTextColor(
                                                    ContextCompat.getColor(
                                                        requireActivity(), R.color.green_drawer
                                                    )
                                                )
                                            display.getButton(AlertDialog.BUTTON_NEGATIVE)
                                                .setTextColor(
                                                    ContextCompat.getColor(
                                                        requireActivity(), R.color.green_drawer
                                                    )
                                                )
                                        }
                                        display.show()

                                        (activity as BazaarContainer).setCartBadge(
                                            data.cartCount ?: 0
                                        )
                                        floatingActionButton8.count = data.cartCount ?: 0
//									toast(activity, "item added to cart")

                                    }
                                    dialog.progress.visibility = View.GONE
                                    dialog.dismiss()
                                    (activity as DashBoardContainer).setCartBadge(
                                        data.cartCount ?: 0
                                    )
//                                    floatingActionButton8.count = data.cartCount ?: 0
                                    toast(activity, "item added to cart")

                                }

                            })
                        } else {
                            if (productData is Productdata) {
                                shippingId = productData.seller_shipping_method.toString()
                            }

                            viewModel.addItem(
                                Request(
                                    changeSeller = seller,
                                    utoken = Preference.get(activity, DATAFILE, TOKEN),
                                    ecom_product_id = newone,

                                    seller_product_id = sellerproductid,
                                    product_size_id = selectedSize?.sizeId ?: "",
                                    product_quantity = items.toString(),
                                    delivery_slot_id = selectedSlot?.id ?: "",
                                    delivery_date = today.toString(),
                                    shipping_method = shippingId,
                                    delivery_type = if (productData is Productdata?) {
                                        //Spot
                                        if (productData?.dailyProduct == 0.toString()) {
                                            "1"
                                        } else {

                                            "2"
                                        }
                                    } else if (productData is ProductItemsList?) {
                                        //Slot
                                        if (productData?.dailyProduct == 0.toString()) {
                                            "1"
                                        } else {
                                            if (productData?.deliveryTimes.isNullOrEmpty()) {
                                                "1"
                                            } else {
                                                "3"
                                            }
                                        }
                                    } else {
                                        ""
                                    }
                                )
                            )?.observe(viewLifecycleOwner, Observer { data ->
                                if (data != null) {
                                    dialog.progress.visibility = View.GONE
                                    dialog.dismiss()

                                    if (data.shopChange == 1) {
                                        val display =
                                            androidx.appcompat.app.AlertDialog.Builder(requireActivity()!!)
                                                .setCancelable(true).setTitle("Replace cart item?")
                                                .setMessage(data.display.toString())
                                                .setPositiveButton("YES") { dialog1, _ ->
                                                    addItem(
                                                        "1",
                                                        today,
                                                        productData,
                                                        selectedSlot,
                                                        selectedSize,
                                                        items,
                                                        dialog
                                                    )
                                                    dialog1?.dismiss()
                                                }.setNegativeButton("NO") { dialog1, _ ->
                                                    dialog.progress.visibility = View.GONE
                                                    dialog1?.dismiss()
                                                }.create()
                                        display.setOnShowListener {
                                            display.getButton(AlertDialog.BUTTON_POSITIVE)
                                                .setTextColor(
                                                    ContextCompat.getColor(
                                                        requireActivity()!!, R.color.colorPrimary
                                                    )
                                                )
                                            display.getButton(AlertDialog.BUTTON_NEGATIVE)
                                                .setTextColor(
                                                    ContextCompat.getColor(
                                                        requireActivity(), R.color.colorPrimary
                                                    )
                                                )
                                        }
                                        display.show()

                                        (activity as BazaarContainer).setCartBadge(
                                            data.cartCount ?: 0
                                        )
                                        floatingActionButton8.count = data.cartCount ?: 0
//									toast(activity, "item added to cart")

                                    }
                                    (activity as DashBoardContainer).setCartBadge(data.cartCount ?: 0)
                                    floatingActionButton8.count = data.cartCount ?: 0
                                    toast(activity, "item added to cart")

                                }
                            })
                        }


                    } else {
                        toast(activity, "item cannot be added to cart")
                        dialog.progress.visibility = View.GONE
                    }
                }
            } else {
                toast(activity, getString(R.string.no_internet))
                dialog.progress?.visibility = View.GONE
            }
        }
    }


    private fun slotItem(
        seller: String,
        today: String?,
        productData: Any?,
        selectedSlot: DeliveryTimesItem?,
        selectedSize: SizeStockPriceItem?,
        items: Int,
        dialog: BottomSheetDialog
    ) {
        dialog.progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                if (productData is Productdata? || productData is ProductItemsList?) {

                    dialog.progress.visibility = View.GONE
                    var newone = if (productData is Productdata?) productData?.id.toString()
                    else if (productData is ProductItemsList?)
                        productData?.id.toString() else ""
                    var sellershippingmethod =
                        if (productData is Productdata?) productData?.seller_shipping_method.toString() else ""
                    var sellerproductid =
                        if (productData is Productdata?) productData?.sprdtid.toString()
                        else if (productData is ProductItemsList?) productData?.sprdtid.toString() else ""

                    viewModel.addItem(
                        Request(
                            changeSeller = seller,
                            utoken = Preference.get(activity, DATAFILE, TOKEN),
                            ecom_product_id = newone,

                            seller_product_id = sellerproductid,
                            product_size_id = selectedSize?.sizeId ?: "",
                            product_quantity = items.toString(),
                            delivery_slot_id = selectedSlot?.id ?: "",
                            delivery_date = today.toString(),
                            shipping_method = sellershippingmethod,
                            delivery_type = if (productData is Productdata?) {
                                //Spot
                                if (productData?.dailyProduct == 0.toString()) {
                                    "1"
                                } else {

                                    "2"
                                }
                            } else if (productData is ProductItemsList?) {
                                //Slot
                                if (productData?.dailyProduct == 0.toString()) {
                                    "1"
                                } else {
                                    if (productData?.deliveryTimes.isNullOrEmpty()) {
                                        "1"
                                    } else {
                                        "3"
                                    }
                                }
                            } else {
                                ""
                            }
                        )
                    )?.observe(viewLifecycleOwner, Observer { data ->
                        if (data != null) {
                            dialog.progress.visibility = View.GONE
                            dialog.dismiss()
                            (activity as BazaarContainer).setCartBadge(data.cartCount ?: 0)
                            floatingActionButton8.count = data.cartCount ?: 0
                            toast(activity, "item added to cart")


                        }

                    })

                } else {
                    toast(activity, "item cannot be added to cart")
                    dialog.progress.visibility = View.GONE
                }


            } else {
                toast(activity, getString(R.string.no_internet))
                dialog.progress?.visibility = View.GONE
            }


        }

    }


    private fun orderSlot(sprdtid: String?, date: String?) {
        if (sprdtid != null) {
            val dialog = BottomSheetDialog(requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.setContentView(R.layout.item_schedule_items)
            // dialog.chipGroup.visibility = View.GONE
            getSlots(dialog, date.toString(), sprdtid)
            dialog.show()
        }
    }

    private fun setSlotData(products: ProductItemsList?, date: String, dialog: BottomSheetDialog) {
        dialog.progress.visibility = View.VISIBLE
        var selected = 0
        var selectedSlot: DeliveryTimesItem? = null
        val stock: Double

        dialog.textView202.text = selected.toString()
        dialog.textView164.text = "Price: ₹0.0"
        dialog.textView39.text = date
        dialog.textView224.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                dialog.textView39.text = SimpleDateFormat(
                    "dd-MM-yyyy", Locale.ENGLISH
                ).format(Calendar.getInstance().time)
                getSlots(
                    dialog, SimpleDateFormat(
                        "dd-MM-yyyy", Locale.ENGLISH
                    ).format(Calendar.getInstance().time).toString(), products?.sprdtid.toString()
                )
            }
        })
        dialog.textView225.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                val cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_YEAR, 1)
                dialog.textView39.text = SimpleDateFormat("dd-MM-yyyy").format(cal.time)
                getSlots(
                    dialog,
                    SimpleDateFormat("dd-MM-yyyy").format(cal.time).toString(),
                    products?.sprdtid.toString()
                )
            }
        })
        dialog.textView39.setOnClickListener {
            Instances.selectFutureDateDMY(
                activity,
                dialog.textView39.text.toString(),
                object : Interfaces.ReturnAny {
                    override fun getValue(values: Any?) {
                        dialog.textView39.text = values.toString()
                        getSlots(dialog, values.toString(), products?.sprdtid.toString())
                    }
                })
        }

        if (products?.deliveryTimes.isNullOrEmpty()) {
            toast(activity, "no delivery for this date. please change date")
        } else {
            if (products?.deliveryTimes?.filter { it?.spotDelivery != "1" }.isNullOrEmpty()) {
                toast(activity, "no delivery for this date. please change date")
            }
        }

        if (!products?.deliveryTimes.isNullOrEmpty()) {
            // dialog.chipGroup.visibility = View.VISIBLE
        }

        if (products?.dailyProduct != null && products.dailyProduct == "1") {
            // dialog.chipGroup.visibility = View.VISIBLE
            dialog.chipGroup.removeAllViews()
            addChips(dialog.chipGroup, object : ArrayList<DeliveryTimesItem>() {
                init {
                    for (i in 0 until (products.deliveryTimes?.size ?: 0)) {
                        products.deliveryTimes?.get(i)?.let {
                            if (it.spotDelivery != "1") {
                                add(it)
                            }
                        }
                    }
                }
            } as ArrayList<Any>, object : Interfaces.ReturnAnyWithKey {
                override fun getValue(key: String, value: Any) {
                    if (key == "slot") {
                        if (value is String) {
                            selectedSlot = null
                        } else if (value is DeliveryTimesItem) {
                            selectedSlot = value
                        }
                    }
                }
            })
        } else {
            selectedSlot = null
            dialog.chipGroup.removeAllViews()
        }

        if (selectedSize == null) {
            stock = 0.0
            selected = 0
            dialog.textView202.text = selected.toString()
            dialog.textView164.text = "Price: ₹0.0"
            dialog.textView165.setOnClickListener {
                toast(activity, "item not available")
            }
            dialog.textView203.setOnClickListener {
                toast(activity, "item not available")
            }
        } else {
            selected = 1
            dialog.textView202.text = selected.toString()
            stock = selectedSize?.stock?.toDouble() ?: 0.0
            dialog.textView164.text = "Price: ₹" + (selectedSize?.sizePrice ?: "")

            if ((selectedSize?.stock?.toDouble() ?: 0.0) > 0.0) {
                dialog.textView165.setOnClickListener { view ->
                    if (selected > 1) {
                        selected--
                    }
                    dialog.textView164.text = "Price: ₹" + ((selectedSize?.sizePrice?.toDouble()
                        ?: 0.0) * selected).toString()
                    dialog.textView202.text = selected.toString()
                }

                dialog.textView203.setOnClickListener { view ->
                    if (selected < stock) {
                        selected++
                    } else {
                        toast(activity, "item out of stock")
                    }
                    dialog.textView164.text = "Price: ₹" + ((selectedSize?.sizePrice?.toDouble()
                        ?: 0.0) * selected).toString()
                    dialog.textView202.text = selected.toString()
                }

            } else {
                selected = 0
                dialog.textView202.text = selected.toString()
                toast(activity, "item out of stock")
                dialog.textView164.text = "Price: ₹0.0"
                dialog.textView165.setOnClickListener {
                    toast(activity, "select an item")
                }
                dialog.textView203.setOnClickListener {
                    toast(activity, "select an item")
                }
            }
        }

        dialog.button2.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                Instances.printAny(dialog.textView39.text)
                Instances.printAny(selectedSlot)
                Instances.printAny(selectedSize)
                Instances.printAny(selected)
                if (stock > 0) {
                    if (dialog.textView39.text.toString().isNotBlank()) {
                        if (selectedSize != null && selected > 0) {
                            if (selectedSlot != null) {
                                slotItem(
                                    "0",
                                    dialog.textView39.text.toString(),
                                    products,
                                    selectedSlot,
                                    selectedSize,
                                    selected,
                                    dialog
                                )
                            } else {
                                toast(activity, "select a time slot")
                            }
                        } else {
                            toast(activity, "select an item")
                        }
                    } else {
                        toast(activity, "select a date")
                    }
                } else {
                    toast(activity, "item out of stock")
                }

            }
        })

        dialog.button3.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                dialog.textView39.text = ""
                selectedSlot = null
                selected = 0
                dialog.dismiss()
            }
        })
        dialog.progress.visibility = View.GONE
    }

    private fun getSlots(dialog: BottomSheetDialog, date: String, sprdtid: String?) {
        dialog.progress.visibility = View.VISIBLE
        val calender = Calendar.getInstance()
        val today = calender.time
        calender.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calender.time

        when (date) {
            SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(today) -> {
                dialog.textView224.isVisible = false
                dialog.textView225.visibility = View.VISIBLE
            }
            SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(tomorrow) -> {
                dialog.textView224.isVisible = true
                dialog.textView225.visibility = View.INVISIBLE
            }
            else -> {
                dialog.textView224.isVisible = true
                dialog.textView225.visibility = View.INVISIBLE
            }
        }

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getSlotsData(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                        sprdtid = sprdtid,
                        deldate = date
                    )
                )?.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        setSlotData(it, date, dialog)
                    }
                    dialog.progress.visibility = View.GONE
                })
            } else {
                toast(activity, getString(R.string.no_internet))
                dialog.progress?.visibility = View.GONE
            }
        }
    }

    private fun addChips(
        chipGroup: ChipGroup, itemChipList: ArrayList<Any>, observer: Interfaces.ReturnAnyWithKey
    ) {
        if (itemChipList != null) {
            if (itemChipList.isNotEmpty()) {
                if (itemChipList[0] is DeliveryTimesItem) {
                    for (i in 0 until itemChipList.size) {
                        chipGroup.addView(getChip(chipGroup, itemChipList[i]))
                    }

                    chipGroup.setOnCheckedChangeListener { group, checkedId ->
                        if (checkedId != View.NO_ID) {
                            val result = (itemChipList as ArrayList<DeliveryTimesItem>).filter {
                                it.id == chipGroup.findViewById<Chip>(checkedId).id.toString()
                            }[0]
                            observer.getValue("slot", result)
                        } else {
                            observer.getValue("slot", "0")
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun getChip(chipGroup: ChipGroup, item: Any): View? {
        val chip = Chip(chipGroup.context)
        if (item is DeliveryTimesItem) {
            chip.text = Instances.changeDateFormat(
                "hh:mm:ss a", "hh:mm a", (item.startTime).toString()
            ) + " - " + Instances.changeDateFormat("hh:mm:ss a", "hh:mm a", item.endTime.toString())
            chip.id = item.id?.toInt() ?: 0
        } else if (item is SizeStockPriceItem) {
            chip.text = item.sizeName + " - " + "₹ " + item.sizePrice?.toDouble().toString()
            chip.id = item.sizeId?.toInt() ?: 0
        }
        chip.textSize = 9F
        chip.setChipDrawable(
            ChipDrawable.createFromResource(
                requireContext(),
                R.xml.scheduled_chips
            )
        )
        chip.isCloseIconVisible = false
        chip.isCheckedIconVisible = false
        Handler().postDelayed({
            chip.isClickable = true
            chip.isCheckable = true
        }, 1000)
        return chip
    }
             }




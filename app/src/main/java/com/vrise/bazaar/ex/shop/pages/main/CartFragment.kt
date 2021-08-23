package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrise.R
import com.vrise.bazaar.ex.adapters.CartItemAdapter
import com.vrise.bazaar.ex.adapters.ShippingAdapter
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.CartIDList
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newcart.*
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.CartViewModel
import com.vrise.bazaar.ex.shop.interfaces.IShippingIdListener
import com.vrise.bazaar.ex.shop.interfaces.OrdersViewModel
import com.vrise.bazaar.ex.shop.pages.shop.AddressListFragment
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN

import com.vrise.databinding.FragmentCartNewBinding
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.cart_fragment.*
import kotlinx.android.synthetic.main.cart_fragment.progress

import kotlinx.android.synthetic.main.fragment_cart_new.*
import kotlinx.android.synthetic.main.fragment_cart_new.button29
import kotlinx.android.synthetic.main.fragment_cart_new.constraintLayout25
import kotlinx.android.synthetic.main.fragment_cart_new.constraintLayout26

import kotlinx.android.synthetic.main.fragment_cart_new.recyclerView27
import kotlinx.android.synthetic.main.fragment_cart_new.recyclerView28
import kotlinx.android.synthetic.main.fragment_cart_new.textView314
import kotlinx.android.synthetic.main.fragment_cart_new.textView316
import kotlinx.android.synthetic.main.fragment_cart_new.textView324
import kotlinx.android.synthetic.main.fragment_cart_new.textView326

import kotlinx.android.synthetic.main.fragment_cart_new.tv_delivery_charge
import kotlinx.android.synthetic.main.fragment_cart_new.tv_discount
import kotlinx.android.synthetic.main.fragment_cart_new.tv_grand_total

import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.shop_fragment.*
import java.text.DecimalFormat

class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var param: Interfaces.ReturnAny

    var newval = 0.0F
    var newsval = 0.0F
    var peak_time_charge = 0.0
    var deliveryAddress = ""
    var totalAmount = 0.0F
    var grandTotal = 0.0
    var amount = 0.0
    var tempAmount = 0.0
    var walletAmount = 0.0
    var productInfo = ""
    var paymentType = ""
    var discountAmount = ""
    var cashbackAmount = ""
    var couponId = ""
    var maxCod = "0"
    private lateinit var viewModelsd: OrdersViewModel
    var walletStatus = "0"
    var totalCartValue: String? = null
    var shippingmethod = ""
    var newGrand = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cart_fragment, container, false)
        Instances.hideKeyboard(activity)

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApp.enableGlobal = 1
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress.visibility = View.VISIBLE
        textView326.visibility = View.GONE
        tv_discount.visibility = View.GONE
        (activity as DashBoardContainer).textView1009.text = "Cart"

        viewModelsd = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(OrdersViewModel::class.java)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(activity),
                    activity
                )
            )
        ).get(CartViewModel::class.java)
//
        noo_items.visibility = View.GONE
    }

    private var ShippingId: String? = null

    public fun getDataVals() {

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModelsd.getItems(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = "",
                        start_date = "",
                        end_date = ""
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                    if (it != null) {
//                        include4.visibility = View.GONE
                        if (activity is DashBoardContainer) {
                            (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                            (activity as DashBoardContainer).setNotificationBadge()
                        }
                    }
                })
            }
        }
    }
   @SuppressLint("SetTextI18n")
   fun getCartStore(){
       val cart_ids = ArrayList<CartIDList>()
       val delData = ArrayList<ShopDeliveryItem?>()
       var del_d: ShopDeliveryItem

       Instances.InternetCheck { internet ->
           if (internet) {
               viewModel.getCartData(
                   Request(
                       utoken = Preference.get(activity, DATAFILE, Preference.TOKEN)
                   )
               )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer { it ->


                   if (activity is DashBoardContainer) {
                       (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                   } else if (activity is BazaarContainer) {
                       (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                   }

                   if (it.cartCount!=0) {

                       tv_delivery_charge.visibility = View.GONE

                       textView324.visibility = View.GONE
                       constraintLayout26.visibility = View.GONE

                       if (activity is DashBoardContainer) {
                           (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                       } else if (activity is BazaarContainer) {
                           (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                       }

                       recyclerView27.layoutManager =
                           LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                       val adapter = it.Shippingmethods?.let { it1 ->
                           ShippingAdapter(
                               activity,
                               it1
                           )
                       }


                       adapter!!.listener = object : IShippingIdListener {
                           override fun onShippingItemClicked(id: String, position: Int) {
                               ShippingId = id
                               cart_ids.clear()
                               cart_ids.removeAll(cart_ids)
                               delData.clear()
                               delData.removeAll(delData)

                               if (it.shopDelivery.isNullOrEmpty()) {
                               } else {
                                   for (i in 0 until it.shopDelivery.size) {
                                       del_d = ShopDeliveryItem()
                                       del_d.seller = it.shopDelivery[i]?.seller.toString()
                                       if (it.shopDelivery[i]?.spotDelivery != null) {
                                           del_d.spotDelivery = SpotDelivery()


                                           if (ShippingId == "3") {


                                               del_d.spotDelivery?.deliveryCharge = 0
                                           } else {
                                               del_d.spotDelivery?.deliveryCharge =
                                                   it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                           }

                                           if (ShippingId == "3") {
                                               val firstValueHelper =
                                                   it.shopDelivery[i]?.spotDelivery?.grandTotal
                                               val secondValueHelper =
                                                   it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                               newval =
                                                   firstValueHelper?.minus(secondValueHelper!!)!!.toFloat()

                                               del_d.spotDelivery?.grandTotal = newval.toInt()

                                           } else {

                                               val firstsValueHelper =
                                                   it.shopDelivery[i]?.spotDelivery?.grandTotal
                                               val secondsValueHelper =
                                                   it.shopDelivery[i]?.spotDelivery?.packing_charge

                                               if (firstsValueHelper != null) {
                                                   newsval = firstsValueHelper.toFloat()
                                               }

                                               del_d.spotDelivery?.grandTotal = newsval.toInt()
                                           }
                                           del_d.spotDelivery?.totalPrize =
                                               it.shopDelivery[i]?.spotDelivery?.totalPrize
                                           del_d.spotDelivery?.seller_pickup_charge =
                                               it.shopDelivery[i]?.spotDelivery?.seller_pickup_charge
                                           del_d.spotDelivery?.seller_pickup_discount = "0"
//                                               it.shopDelivery[i]?.spotDelivery?.seller_pickup_offer?.seller_discount.toString()


                                           del_d.spotDelivery?.totalCommesion = ""
                                           del_d.spotDelivery?.deliverySlotId =
                                               it.shopDelivery[i]?.spotDelivery?.deliveryData?.id
                                           del_d.spotDelivery?.packing_charge =
                                               it.shopDelivery[i]?.spotDelivery?.packing_charge
                                           if (it.shopDelivery[i]?.spotDelivery?.items.isNullOrEmpty()) {
                                           } else {
                                               del_d.spotDelivery?.itemids = ""
                                               del_d.spotDelivery?.deliveryDate = ""

                                               productInfo += "SpotDelivery("
                                               for (j in 0 until ((it.shopDelivery[i]?.spotDelivery?.items?.size)
                                                   ?: 0)) {
                                                   del_d.spotDelivery?.itemids =
                                                       del_d.spotDelivery?.itemids.toString() + "," + it.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()

                                                   if (j == 0) {
                                                       del_d.spotDelivery?.deliveryDate =
                                                           it.shopDelivery[i]?.spotDelivery?.items!![j]?.deliveryDate.toString()
                                                   }

                                                   cart_ids.add(CartIDList(it.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()))

                                                   productInfo =
                                                       productInfo + it.shopDelivery[i]?.spotDelivery?.items!![j]?.name.toString() + ","
                                               }
                                               productInfo += ") \n"
                                           }


                                       } else {
                                           del_d.spotDelivery = SpotDelivery()
                                       }


                                       if (it.shopDelivery[i]?.slotDelivery.isNullOrEmpty()) {
                                           del_d.slotDelivery = ArrayList<SlotDeliveryItem>()
                                       } else {
                                           del_d.slotDelivery =
                                               java.util.ArrayList<SlotDeliveryItem>()
                                           productInfo += "SlotDelivery("
                                           for (k in 0 until ((it.shopDelivery[i]?.slotDelivery?.size)
                                               ?: 0)) {
                                               (del_d.slotDelivery as java.util.ArrayList<SlotDeliveryItem>).add(
                                                   SlotDeliveryItem(
                                                       deliverySlotIds = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.deliveryData?.deliverySlotId.toString(),
                                                       deliveryCharge = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.deliveryCharge,
                                                       totalCommesion = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.totalCommesion,
                                                       grandTotal = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.grandTotal,
                                                       totalPrize = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.totalPrize,
                                                       packing_charge = it.shopDelivery[i]?.slotDelivery?.get(
                                                           k
                                                       )?.packing_charge
                                                   )
                                               )
                                               del_d.slotDelivery?.get(k)?.itemids = ""
                                               del_d.slotDelivery?.get(k)?.deliveryDate = ""


                                               if (it.shopDelivery[i]?.slotDelivery!![k]?.items.isNullOrEmpty()) {
                                               } else {
                                                   for (l in 0 until ((it.shopDelivery[i]?.slotDelivery!![k]?.items?.size)
                                                       ?: 0)) {
                                                       del_d.slotDelivery?.get(k)?.itemids =
                                                           del_d.slotDelivery?.get(k)?.itemids + "," + it.shopDelivery[i]?.slotDelivery?.get(
                                                               k
                                                           )?.items?.get(l)?.id.toString()

                                                       if (l == 0) {
                                                           del_d.slotDelivery?.get(k)?.deliveryDate =
                                                               it.shopDelivery[i]?.slotDelivery?.get(
                                                                   k
                                                               )?.items?.get(l)?.deliveryDate.toString()
                                                       }

                                                       cart_ids.add(CartIDList(it.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.id.toString()))
                                                       productInfo =
                                                           productInfo + it.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.name.toString() + ","
                                                   }
                                               }
                                           }
                                           productInfo += ") \n"
                                       }
                                       if (it.shopDelivery[i]?.normalDelivery != null) {
                                           if (it.shopDelivery[i]?.normalDelivery?.items.isNullOrEmpty()) {
                                           } else {
                                               del_d.normalDelivery = NormalDelivery()
                                               del_d.normalDelivery?.deliveryCharge = 0.0F
                                               del_d.normalDelivery?.totalCommesion = ""
                                               del_d.normalDelivery?.itemsid = ""
                                               del_d.normalDelivery?.deliveryDate = "0"
                                               del_d.normalDelivery?.grandTotal = "0"
                                               del_d.normalDelivery?.deliverySlotId = "0"
                                               del_d.normalDelivery?.totalPrize = "0"
                                               del_d.normalDelivery?.packing_charge =
                                                   it.shopDelivery[i]?.normalDelivery?.packing_charge
                                                       ?: "0"
//										del_d.seller_pickup_charge=pickupcharge
//										del_d.seller_pickup_discount=pickupdiscount
                                               productInfo += "NormalDelivery("
                                               for (m in 0 until ((it.shopDelivery[i]?.normalDelivery?.items?.size)
                                                   ?: 0)) {
                                                   del_d.normalDelivery?.itemsid =
                                                       del_d.normalDelivery?.itemsid + "," + it.shopDelivery[i]?.normalDelivery?.items?.get(
                                                           m
                                                       )?.id.toString()

                                                   if (m == 0) {
                                                       del_d.normalDelivery?.deliveryDate = ""
                                                   }
                                                   cart_ids.add(CartIDList("0"))
                                                   productInfo =
                                                       productInfo + it.shopDelivery[i]?.normalDelivery?.items!![m]?.name.toString() + ","
                                               }
                                               productInfo += ") \n"
                                           }
                                       } else {
                                           del_d.normalDelivery = NormalDelivery()
                                       }

                                       delData.add(del_d)
                                   }
                               }
                               if (ShippingId == "3") {

                                   tv_delivery_charge.visibility = View.GONE
                                   textView324.visibility = View.GONE
                                   constraintLayout26.visibility = View.GONE

                                   val firstValueHelper =
                                       it.shopDelivery!!.get(0)!!.spotDelivery!!.grandTotal!!
                                   val secondValueHelper =
                                       it.shopDelivery!!.get(0)!!.spotDelivery!!.deliveryCharge!!

                                   var newval = firstValueHelper - secondValueHelper
                                   val precision = DecimalFormat("0.00")
                                   tv_total_amount.setText(
                                       "₹" + (String.format(precision.format(newval)))
                                   )

                                   tv_grand_total.setText(
                                       "₹" + (String.format(
                                           precision.format(newval)

                                       ))
                                   )

                               } else if (ShippingId == "1") {
                                   textView326.visibility = View.GONE
                                   tv_discount.visibility = View.GONE
                                   tv_delivery_charge.visibility = View.VISIBLE
                                   textView324.visibility = View.VISIBLE
                                   val precision = DecimalFormat("0.00")
                                   constraintLayout26.visibility = View.VISIBLE
                                   tv_total_amount.setText(
                                       "₹" + (String.format(
                                           precision.format(it.totalCartvalue)

                                       ))
                                   )
                                   tv_grand_total.setText(
                                       "₹" + (String.format(
                                           precision.format(it.totalCartvalue)

                                       ))
                                   )

                                   tv_delivery_charge.setText(
                                       "₹" + (String.format(
                                           precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))


                               }

                               else {
                                   tv_delivery_charge.visibility = View.GONE
                                   textView324.visibility = View.GONE
                                   constraintLayout26.visibility = View.VISIBLE

                                   val firstValueHelper =
                                       it.shopDelivery!!.get(0)!!.spotDelivery!!.grandTotal!!
                                   val secondValueHelper =
                                       it.shopDelivery!!.get(0)!!.spotDelivery!!.deliveryCharge!!


                                   var newval = firstValueHelper - secondValueHelper
                                   val precision = DecimalFormat("0.00")
                                   tv_total_amount.setText(
                                       "₹" +
                                               (String.format(
                                                   precision.format(newval)

                                               ))
                                   )

                                   tv_grand_total.setText(
                                       "₹" + (String.format(
                                           precision.format(newval)

                                       ))
                                   )


                                   val precisions = DecimalFormat("0.00")
                                   if (it.shopDelivery?.get(0)!!.spotDelivery!!.deliveryCharge?.toDouble() != 0.0) {
                                       it.shopDelivery?.get(0)!!.spotDelivery!!.deliveryCharge?.let { it1 ->
                                           tv_delivery_charge.setText(
                                               "₹" +
                                                       (String.format(
                                                           precisions.format(it1)
                                                       ))
                                           )
                                       }
                                   } else {
                                       constraintLayout25.visibility = android.view.View.GONE
                                   }
                               }
                               if (ShippingId == "3") {
                                   if (it.shipping_method == "" && (it.shipping_method.isNullOrBlank())) {
                                       textView326.visibility = View.GONE
                                       tv_discount.visibility = View.GONE
                                       val firstValueSHelper =
                                           it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal
                                       val secondValueHelper =
                                           it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge

                                       grandTotal = firstValueSHelper?.minus(secondValueHelper!!)!!.toDouble()
                                       newGrand = grandTotal
                                       val precision = DecimalFormat("0.00")
                                       val firstGrand = newGrand
                                       val discount = 0.0
//                                           it.shopDelivery?.get(0)?.spotDelivery?.seller_pickup_offer?.seller_discount?.toDouble()

//                                       tv_discount.text =
//
//
//                                           "₹" + (String.format(
//                                               precision.format(  it.shopDelivery?.get(0)?.spotDelivery?.seller_pickup_offer?.seller_discount)
//                                           ))


                                       grandTotal = firstGrand.minus(discount!!).toDouble()

                                       tv_grand_total.setText(
                                           "₹" + (String.format(
                                               precision.format(grandTotal)
                                           ))
                                       )
                                       tv_total_amount.setText(
                                           "₹" + (String.format(
                                               precision.format(grandTotal)
                                           ))
                                       )
                                   }


                               } else {

                                   textView326.visibility = View.GONE
                                   tv_discount.visibility = View.GONE
                                   tv_delivery_charge.visibility = View.VISIBLE
                                   textView324.visibility = View.VISIBLE
                                   val precision = DecimalFormat("0.00")
                                   constraintLayout26.visibility = View.VISIBLE
                                   tv_total_amount.setText(
                                       "₹" + (String.format(
                                           precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal)

                                       ))
                                   )
                                   tv_grand_total.setText(
                                       "₹" + (String.format(
                                           precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal)

                                       ))
                                   )



                                   tv_delivery_charge.setText(
                                       "₹" + (String.format(
                                           precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))


                                   grandTotal =
                                       (it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal?.toDouble()!!)






                               }
                               walletAmount = it.walletBalance.toDouble()
                               amount =
                                   (it.shopDelivery?.get(0)!!.spotDelivery?.grandTotal?.toDouble()!!)
                               deliveryAddress = it.default_address_id
                               totalCartValue = it.totalCartvalue.toString()
                               maxCod = it.max_cod
                               totalAmount =
                                   it.shopDelivery?.get(0)!!.spotDelivery?.totalPrize?.toFloat()!!
//                               discountAmount =
//                                   it.shopDelivery!!.get(0)!!.spotDelivery?.seller_pickup_offer?.seller_discount.toString()


                           }
                       }

                       recyclerView27.adapter = adapter

                       recyclerView28.layoutManager = LinearLayoutManager(
                           activity,
                           LinearLayoutManager.VERTICAL,
                           false
                       )
                       var adapters = CartItemAdapter(
                           activity, this,
                           it.shopDelivery!!.get(0)!!.spotDelivery!!.items!! as ArrayList<ItemsItem?>,
                           viewModel
                       )
                       recyclerView28.adapter = adapters

                       tv_address.setText(
                           it.defaultAddress?.id + it.defaultAddress?.address + "," +
                                   it.defaultAddress?.address2 + "," + it.defaultAddress?.district + "," +
                                   it.defaultAddress?.state + " ," +
                                   it.defaultAddress?.pincode
                       )
                       val precisions = DecimalFormat("0.00")
                       if (it.shopDelivery.get(0)!!.spotDelivery?.packing_charge != 0) {
                           textView316.setText(
                               "₹" + (String.format(
                                   precisions.format(
                                       it.shopDelivery.get(0)!!.spotDelivery?.packing_charge?.toInt()
                                   )
                               ))
                           )
                       } else {
                           textView314.visibility = View.GONE
                           textView316.visibility = View.GONE
                       }
                       textView320.setOnClickListener {

                           val display =
                               androidx.appcompat.app.AlertDialog.Builder(requireContext())
                                   .setCancelable(true).setMessage(
                                       "Products added in the cart will be cleared while changing the address," +
                                               " Do you want to continue?"
                                   )
                                   .setNegativeButton("YES") { dialog1, _ ->

                                       findNavController().navigate(
                                           R.id.action_navigation_cart_to_addressListFragment
                                       )


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
                           display.show() }
                       tv_mrp.setText(
                           ("₹" + (String.format(
                               precisions.format(it.shopDelivery.get(0)!!.spotDelivery?.totalPrize)
                                   .toString()
                           )))
                       )


                       if (it.shopDelivery.get(0)!!.spotDelivery!!.deliveryCharge?.toDouble() != 0.0) {
                           it.shopDelivery.get(0)!!.spotDelivery!!.deliveryCharge?.let { it1 ->

                               tv_delivery_charge.setText(
                                   "₹" + (String.format(
                                       precisions.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))
                           }
                       } else {
                           constraintLayout25.visibility = android.view.View.GONE
                       }
//                        if (it.shopDelivery.get(0)!!.spotDelivery!!.seller_pickup_discount != "0") {
//                            tv_discount.setText(it.shopDelivery.get(0)!!.spotDelivery!!.seller_pickup_discount)
//                        } else {
//                            textView326.visibility = View.GONE
//                            tv_discount.visibility = View.GONE
//                        }
                       val precision = DecimalFormat("0.00")
                       tv_total_amount.setText(
                           "₹" +
                                   (String.format(
                                       precision.format(
                                           (it.shopDelivery.get(
                                               0
                                           )!!.spotDelivery!!.totalPrize)?.plus(
                                               (it.shopDelivery.get(
                                                   0
                                               )!!.spotDelivery!!.packing_charge!!)
                                           )
                                       )
                                   ))

                       )
                       tv_grand_total.setText(
                           ("₹" +
                                   (String.format(
                                       precision.format(
                                           it.shopDelivery.get(
                                               0
                                           )!!.spotDelivery!!.grandTotal!!
                                       )
                                   )

                                           ))
                       )

                       button29.setOnClickListener(object : ClickListener() {
                           override fun onOneClick(v: View) {
                               if (ShippingId == "" ||
                                   ShippingId == null
                               ) {
                                   progress.visibility = View.GONE
                                   Toast.makeText(
                                       activity,
                                       "Please select a shipping method",
                                       Toast.LENGTH_SHORT
                                   ).show()

                               } else {
                                   viewModel.canplaceorder(
                                       Request(
                                           utoken = Preference.get(
                                               requireActivity(),
                                               DATAFILE,
                                               TOKEN
                                           ),
                                           cart_ids = cart_ids
                                       )
                                   )?.observe(
                                       viewLifecycleOwnerLiveData.value ?: this@CartFragment,
                                       Observer {

                                           if (it != null) {


                                               if (it.order_model.toString() == "1") {
                                                   val bundle = Bundle()
                                                   bundle.putString(
                                                       Constants.SellerId,
                                                       it.seller_id
                                                   )
                                                   viewModel.sale(
                                                       Request(
                                                           utoken = Preference.get(
                                                               activity,
                                                               DATAFILE,
                                                               Preference.TOKEN
                                                           ),
                                                           cart_ids = cart_ids,
                                                           total_amount = totalAmount,
                                                           discountAmount = discountAmount,
                                                           cashbackAmount = cashbackAmount,
                                                           coupon_id = couponId,
                                                           grand_total = grandTotal.toString(),
                                                           wallet_status = walletStatus,
                                                           cod_status = "1",
                                                           online_status = "0",
                                                           payment_status = if (amount > 0) {
                                                               "pending"
                                                           } else {
                                                               "paid"
                                                           },
                                                           online_amount = "0",
                                                           wallet_amount = walletAmount.toString(),
                                                           cod_amount = grandTotal.toString(),
                                                           online_data = "",
                                                           shops = delData,
                                                           peak_time_charge = peak_time_charge.toString(),
                                                           delivery_address_id = deliveryAddress,
                                                           shipping_method = ShippingId

                                                       )
                                                   )?.observe(viewLifecycleOwner, Observer {
                                                       if (it != null) {
                                                           v.findNavController().navigate(
                                                               R.id.sellerConfirmation,
                                                               bundle
                                                           )

                                                       }
                                                   })
                                               } else {
                                                   val bundle = Bundle()
                                                   bundle.putString(Constants.Shipping, ShippingId)

                                                   v.findNavController().navigate(
                                                       R.id.cashOnDeliveryFragment,
                                                       bundle
                                                   )


                                               }
                                           }
                                       })
                               }
                           }
                       })
                   } else {

                       noo_items.visibility = View.VISIBLE
                       if (activity is DashBoardContainer) {
                           (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                       } else if (activity is BazaarContainer) {
                           (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                       }

                       noo_items.visibility = View.VISIBLE
//                        getDataVals()

                       cartshop.setOnClickListener {
                           activity?.onBackPressed()
                       }
                   }
               })
           }
       }
   }
    @SuppressLint("SetTextI18n")
     fun getCartData() {
        val cart_ids = ArrayList<CartIDList>()
        val delData = ArrayList<ShopDeliveryItem?>()
        var del_d: ShopDeliveryItem
        progress?.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getCartData(
                    Request(
                        utoken = Preference.get(activity, DATAFILE, Preference.TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer { it ->


                    if (activity is DashBoardContainer) {
                        (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                    } else if (activity is BazaarContainer) {
                        (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                    }

                    if (it.cartCount!=0) {
                        progress?.visibility = View.GONE
                        tv_delivery_charge.visibility = View.GONE

                        if (it.shopDelivery!=null){
                            textView404.text = it.shopDelivery?.get(0)?.sellerData?.storeName.toString()


                        }
                        textView324.visibility = View.GONE
                        constraintLayout26.visibility = View.GONE

                        if (activity is DashBoardContainer) {
                            (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                        } else if (activity is BazaarContainer) {
                            (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                        }

                        recyclerView27.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        val adapter = it.Shippingmethods?.let { it1 ->
                            ShippingAdapter(
                                activity,
                                it1
                            )
                        }


                        adapter!!.listener = object : IShippingIdListener {
                            override fun onShippingItemClicked(id: String, position: Int) {
                                ShippingId = id
                                cart_ids.clear()
                                cart_ids.removeAll(cart_ids)
                                delData.clear()
                                delData.removeAll(delData)

                                if (it.shopDelivery.isNullOrEmpty()) {
                                } else {
                                    for (i in 0 until it.shopDelivery.size) {
                                        del_d = ShopDeliveryItem()
                                        del_d.seller = it.shopDelivery[i]?.seller.toString()
                                        if (it.shopDelivery[i]?.spotDelivery != null) {
                                            del_d.spotDelivery = SpotDelivery()


                                            if (ShippingId == "3") {


                                                del_d.spotDelivery?.deliveryCharge = 0
                                            } else {
                                                del_d.spotDelivery?.deliveryCharge =
                                                    it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                            }

                                            if (ShippingId == "3") {
                                                val firstValueHelper =
                                                    it.shopDelivery[i]?.spotDelivery?.grandTotal
                                                val secondValueHelper =
                                                    it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                                newval =
                                                    firstValueHelper?.minus(secondValueHelper!!)!!.toFloat()

                                                del_d.spotDelivery?.grandTotal = newval.toInt()

                                            } else {

                                                val firstsValueHelper =
                                                    it.shopDelivery[i]?.spotDelivery?.grandTotal
                                                val secondsValueHelper =
                                                    it.shopDelivery[i]?.spotDelivery?.packing_charge

                                                if (firstsValueHelper != null) {
                                                    newsval = firstsValueHelper.toFloat()
                                                }

                                                del_d.spotDelivery?.grandTotal = newsval.toInt()
                                            }
                                            del_d.spotDelivery?.totalPrize =
                                                it.shopDelivery[i]?.spotDelivery?.totalPrize
                                            del_d.spotDelivery?.seller_pickup_charge =
                                                it.shopDelivery[i]?.spotDelivery?.seller_pickup_charge
                                            del_d.spotDelivery?.seller_pickup_discount = "0"
//                                                it.shopDelivery[i]?.spotDelivery?.seller_pickup_offer?.seller_discount.toString()


                                            del_d.spotDelivery?.totalCommesion = ""
                                            del_d.spotDelivery?.deliverySlotId =
                                                it.shopDelivery[i]?.spotDelivery?.deliveryData?.id
                                            del_d.spotDelivery?.packing_charge =
                                                it.shopDelivery[i]?.spotDelivery?.packing_charge
                                            if (it.shopDelivery[i]?.spotDelivery?.items.isNullOrEmpty()) {
                                            } else {
                                                del_d.spotDelivery?.itemids = ""
                                                del_d.spotDelivery?.deliveryDate = ""

                                                productInfo += "SpotDelivery("
                                                for (j in 0 until ((it.shopDelivery[i]?.spotDelivery?.items?.size)
                                                    ?: 0)) {
                                                    del_d.spotDelivery?.itemids =
                                                        del_d.spotDelivery?.itemids.toString() + "," + it.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()

                                                    if (j == 0) {
                                                        del_d.spotDelivery?.deliveryDate =
                                                            it.shopDelivery[i]?.spotDelivery?.items!![j]?.deliveryDate.toString()
                                                    }

                                                    cart_ids.add(CartIDList(it.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()))

                                                    productInfo =
                                                        productInfo + it.shopDelivery[i]?.spotDelivery?.items!![j]?.name.toString() + ","
                                                }
                                                productInfo += ") \n"
                                            }


                                        } else {
                                            del_d.spotDelivery = SpotDelivery()
                                        }


                                        if (it.shopDelivery[i]?.slotDelivery.isNullOrEmpty()) {
                                            del_d.slotDelivery = ArrayList<SlotDeliveryItem>()
                                        } else {
                                            del_d.slotDelivery =
                                                java.util.ArrayList<SlotDeliveryItem>()
                                            productInfo += "SlotDelivery("
                                            for (k in 0 until ((it.shopDelivery[i]?.slotDelivery?.size)
                                                ?: 0)) {
                                                (del_d.slotDelivery as java.util.ArrayList<SlotDeliveryItem>).add(
                                                    SlotDeliveryItem(
                                                        deliverySlotIds = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.deliveryData?.deliverySlotId.toString(),
                                                        deliveryCharge = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.deliveryCharge,
                                                        totalCommesion = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.totalCommesion,
                                                        grandTotal = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.grandTotal,
                                                        totalPrize = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.totalPrize,
                                                        packing_charge = it.shopDelivery[i]?.slotDelivery?.get(
                                                            k
                                                        )?.packing_charge
                                                    )
                                                )
                                                del_d.slotDelivery?.get(k)?.itemids = ""
                                                del_d.slotDelivery?.get(k)?.deliveryDate = ""


                                                if (it.shopDelivery[i]?.slotDelivery!![k]?.items.isNullOrEmpty()) {
                                                } else {
                                                    for (l in 0 until ((it.shopDelivery[i]?.slotDelivery!![k]?.items?.size)
                                                        ?: 0)) {
                                                        del_d.slotDelivery?.get(k)?.itemids =
                                                            del_d.slotDelivery?.get(k)?.itemids + "," + it.shopDelivery[i]?.slotDelivery?.get(
                                                                k
                                                            )?.items?.get(l)?.id.toString()

                                                        if (l == 0) {
                                                            del_d.slotDelivery?.get(k)?.deliveryDate =
                                                                it.shopDelivery[i]?.slotDelivery?.get(
                                                                    k
                                                                )?.items?.get(l)?.deliveryDate.toString()
                                                        }

                                                        cart_ids.add(CartIDList(it.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.id.toString()))
                                                        productInfo =
                                                            productInfo + it.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.name.toString() + ","
                                                    }
                                                }
                                            }
                                            productInfo += ") \n"
                                        }
                                        if (it.shopDelivery[i]?.normalDelivery != null) {
                                            if (it.shopDelivery[i]?.normalDelivery?.items.isNullOrEmpty()) {
                                            } else {
                                                del_d.normalDelivery = NormalDelivery()
                                                del_d.normalDelivery?.deliveryCharge = 0.0F
                                                del_d.normalDelivery?.totalCommesion = ""
                                                del_d.normalDelivery?.itemsid = ""
                                                del_d.normalDelivery?.deliveryDate = "0"
                                                del_d.normalDelivery?.grandTotal = "0"
                                                del_d.normalDelivery?.deliverySlotId = "0"
                                                del_d.normalDelivery?.totalPrize = "0"
                                                del_d.normalDelivery?.packing_charge =
                                                    it.shopDelivery[i]?.normalDelivery?.packing_charge
                                                        ?: "0"
//										del_d.seller_pickup_charge=pickupcharge
//										del_d.seller_pickup_discount=pickupdiscount
                                                productInfo += "NormalDelivery("
                                                for (m in 0 until ((it.shopDelivery[i]?.normalDelivery?.items?.size)
                                                    ?: 0)) {
                                                    del_d.normalDelivery?.itemsid =
                                                        del_d.normalDelivery?.itemsid + "," + it.shopDelivery[i]?.normalDelivery?.items?.get(
                                                            m
                                                        )?.id.toString()

                                                    if (m == 0) {
                                                        del_d.normalDelivery?.deliveryDate = ""
                                                    }
                                                    cart_ids.add(CartIDList("0"))
                                                    productInfo =
                                                        productInfo + it.shopDelivery[i]?.normalDelivery?.items!![m]?.name.toString() + ","
                                                }
                                                productInfo += ") \n"
                                            }
                                        } else {
                                            del_d.normalDelivery = NormalDelivery()
                                        }

                                        delData.add(del_d)
                                    }
                                }
                                if (ShippingId == "3") {

                                    tv_delivery_charge.visibility = View.GONE
                                    textView324.visibility = View.GONE
                                    constraintLayout26.visibility = View.GONE

                                    val firstValueHelper =
                                        it.shopDelivery!!.get(0)!!.spotDelivery!!.grandTotal!!
                                    val secondValueHelper =
                                        it.shopDelivery!!.get(0)!!.spotDelivery!!.deliveryCharge!!

                                    var newval = firstValueHelper - secondValueHelper
                                    val precision = DecimalFormat("0.00")
                                    tv_total_amount.setText(
                                        "₹" + (String.format(precision.format(newval)))
                                    )

                                    tv_grand_total.setText(
                                        "₹" + (String.format(
                                            precision.format(newval)

                                        ))
                                    )

                                } else if (ShippingId == "1") {
                                    textView326.visibility = View.GONE
                                    tv_discount.visibility = View.GONE
                                    tv_delivery_charge.visibility = View.VISIBLE
                                    textView324.visibility = View.VISIBLE
                                    val precision = DecimalFormat("0.00")
                                    constraintLayout26.visibility = View.VISIBLE
                                    tv_total_amount.setText(
                                        "₹" + (String.format(
                                            precision.format(it.totalCartvalue)

                                        ))
                                    )
                                    tv_grand_total.setText(
                                        "₹" + (String.format(
                                            precision.format(it.totalCartvalue)

                                        ))
                                    )

                                    tv_delivery_charge.setText(
                                        "₹" + (String.format(
                                            precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))


                                }

                                else {
                                    tv_delivery_charge.visibility = View.GONE
                                    textView324.visibility = View.GONE
                                    constraintLayout26.visibility = View.VISIBLE

                                    val firstValueHelper =
                                        it.shopDelivery!!.get(0)!!.spotDelivery!!.grandTotal!!
                                    val secondValueHelper =
                                        it.shopDelivery!!.get(0)!!.spotDelivery!!.deliveryCharge!!


                                    var newval = firstValueHelper - secondValueHelper
                                    val precision = DecimalFormat("0.00")
                                    tv_total_amount.setText(
                                        "₹" +
                                                (String.format(
                                                    precision.format(newval)

                                                ))
                                    )

                                    tv_grand_total.setText(
                                        "₹" + (String.format(
                                            precision.format(newval)

                                        ))
                                    )


                                    val precisions = DecimalFormat("0.00")
                                    if (it.shopDelivery?.get(0)!!.spotDelivery!!.deliveryCharge?.toDouble() != 0.0) {
                                        it.shopDelivery?.get(0)!!.spotDelivery!!.deliveryCharge?.let { it1 ->
                                            tv_delivery_charge.setText(
                                                "₹" +
                                                        (String.format(
                                                            precisions.format(it1)
                                                        ))
                                            )
                                        }
                                    } else {
                                        constraintLayout25.visibility = android.view.View.GONE
                                    }
                                }
                                if (ShippingId == "3") {
                                    if (it.shipping_method == "" && (it.shipping_method.isNullOrBlank())) {
                                        textView326.visibility = View.GONE
                                        tv_discount.visibility = View.GONE
                                        val firstValueSHelper =
                                            it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal
                                        val secondValueHelper =
                                            it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge

                                        grandTotal = firstValueSHelper?.minus(secondValueHelper!!)!!.toDouble()
                                        newGrand = grandTotal
                                        val precision = DecimalFormat("0.00")
                                        val firstGrand = newGrand
                                        val discount = 0.0
//                                            it.shopDelivery?.get(0)?.spotDelivery?.seller_pickup_offer?.seller_discount?.toDouble()

//                                        tv_discount.text =
//
// "₹" + (String.format(precision.format(  it.shopDelivery?.get(0)?.spotDelivery?.seller_pickup_offer?.seller_discount)
//                                            ))


                                        grandTotal = firstGrand.minus(discount!!).toDouble()

                                        tv_grand_total.setText(
                                            "₹" + (String.format(
                                                precision.format(grandTotal)
                                            ))
                                        )
                                        tv_total_amount.setText(
                                            "₹" + (String.format(
                                                precision.format(grandTotal)
                                            ))
                                        )
                                    }


                                } else {

                                    textView326.visibility = View.GONE
                                    tv_discount.visibility = View.GONE
                                    tv_delivery_charge.visibility = View.VISIBLE
                                    textView324.visibility = View.VISIBLE
                                    val precision = DecimalFormat("0.00")
                                    constraintLayout26.visibility = View.VISIBLE
                                    tv_total_amount.setText(
                                        "₹" + (String.format(
                                            precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal)

                                        ))
                                    )
                                    tv_grand_total.setText(
                                        "₹" + (String.format(
                                            precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal)

                                        ))
                                    )



                                    tv_delivery_charge.setText(
                                        "₹" + (String.format(
                                            precision.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))


                                    grandTotal =
                                        (it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal?.toDouble()!!)






                                }
                                walletAmount = it.walletBalance.toDouble()
                                amount =
                                    (it.shopDelivery?.get(0)!!.spotDelivery?.grandTotal?.toDouble()!!)
                                deliveryAddress = it.default_address_id
                                totalCartValue = it.totalCartvalue.toString()
                                maxCod = it.max_cod
                                totalAmount =
                                    it.shopDelivery?.get(0)!!.spotDelivery?.totalPrize?.toFloat()!!
//                                discountAmount =
//                                    it.shopDelivery!!.get(0)!!.spotDelivery?.seller_pickup_offer?.seller_discount.toString()


                            }
                        }

                        recyclerView27.adapter = adapter

                        recyclerView28.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        var adapters = CartItemAdapter(
                            activity, this,
                            it.shopDelivery!!.get(0)!!.spotDelivery!!.items!! as ArrayList<ItemsItem?>,
                            viewModel
                        )
                        recyclerView28.adapter = adapters

                        tv_address.setText(
                            it.defaultAddress?.address + "," +
                                    it.defaultAddress?.address2 + "," + it.defaultAddress?.district + "," +
                                    it.defaultAddress?.state + " ," +
                                    it.defaultAddress?.pincode
                        )
                        val precisions = DecimalFormat("0.00")
                        if (it.shopDelivery.get(0)!!.spotDelivery?.packing_charge != 0) {
                            textView316.setText(
                                "₹" + (String.format(
                                    precisions.format(
                                        it.shopDelivery.get(0)!!.spotDelivery?.packing_charge?.toInt()
                                    )
                                ))
                            )
                        } else {
                            textView314.visibility = View.GONE
                            textView316.visibility = View.GONE
                        }
                        textView320.setOnClickListener {

                            val display =
                                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                                    .setCancelable(true).setMessage(
                                        "Products added in the cart will be cleared while changing the address," +
                                                " Do you want to continue?"
                                    )
                                    .setNegativeButton("YES") { dialog1, _ ->

                                        findNavController().navigate(
                                            R.id.action_navigation_cart_to_addressListFragment
                                        )


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
                            display.show() }
                        tv_mrp.setText(
                            ("₹" + (String.format(
                                precisions.format(it.shopDelivery.get(0)!!.spotDelivery?.totalPrize)
                                    .toString()
                            )))
                        )


                        if (it.shopDelivery.get(0)!!.spotDelivery!!.deliveryCharge?.toDouble() != 0.0) {
                            it.shopDelivery.get(0)!!.spotDelivery!!.deliveryCharge?.let { it1 ->

                                tv_delivery_charge.setText(
                                    "₹" + (String.format(
                                        precisions.format(it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge))))
                            }
                        } else {
                            constraintLayout25.visibility = android.view.View.GONE
                        }
//                        if (it.shopDelivery.get(0)!!.spotDelivery!!.seller_pickup_discount != "0") {
//                            tv_discount.setText(it.shopDelivery.get(0)!!.spotDelivery!!.seller_pickup_discount)
//                        } else {
//                            textView326.visibility = View.GONE
//                            tv_discount.visibility = View.GONE
//                        }
                        val precision = DecimalFormat("0.00")
                        tv_total_amount.setText(
                            "₹" +
                                    (String.format(
                                        precision.format(
                                            (it.shopDelivery.get(
                                                0
                                            )!!.spotDelivery!!.totalPrize)?.plus(
                                                (it.shopDelivery.get(
                                                    0
                                                )!!.spotDelivery!!.packing_charge!!)
                                            )
                                        )
                                    ))

                        )
                        tv_grand_total.setText(
                            ("₹" +
                                    (String.format(
                                        precision.format(
                                            it.shopDelivery.get(
                                                0
                                            )!!.spotDelivery!!.grandTotal!!
                                        )
                                    )

                                            ))
                        )

                        button29.setOnClickListener(object : ClickListener() {
                            override fun onOneClick(v: View) {
                                if (ShippingId == "" ||
                                    ShippingId == null
                                ) {
                                    progress.visibility = View.GONE
                                    Toast.makeText(
                                        activity,
                                        "Please select a shipping method",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {
                                    viewModel.canplaceorder(
                                        Request(
                                            utoken = Preference.get(
                                                requireActivity(),
                                                DATAFILE,
                                                TOKEN
                                            ),
                                            cart_ids = cart_ids
                                        )
                                    )?.observe(
                                        viewLifecycleOwnerLiveData.value ?: this@CartFragment,
                                        Observer {

                                            if (it != null) {


                                                if (it.order_model.toString() == "1") {
                                                    val bundle = Bundle()
                                                    bundle.putString(
                                                        Constants.SellerId,
                                                        it.seller_id
                                                    )
                                                    viewModel.sale(
                                                        Request(
                                                            utoken = Preference.get(
                                                                activity,
                                                                DATAFILE,
                                                                Preference.TOKEN
                                                            ),
                                                            cart_ids = cart_ids,
                                                            total_amount = totalAmount,
                                                            discountAmount = discountAmount,
                                                            cashbackAmount = cashbackAmount,
                                                            coupon_id = couponId,
                                                            grand_total = grandTotal.toString(),
                                                            wallet_status = walletStatus,
                                                            cod_status = "1",
                                                            online_status = "0",
                                                            payment_status = if (amount > 0) {
                                                                "pending"
                                                            } else {
                                                                "paid"
                                                            },
                                                            online_amount = "0",
                                                            wallet_amount = walletAmount.toString(),
                                                            cod_amount = grandTotal.toString(),
                                                            online_data = "",
                                                            shops = delData,
                                                            peak_time_charge = peak_time_charge.toString(),
                                                            delivery_address_id = deliveryAddress,
                                                            shipping_method = ShippingId

                                                        )
                                                    )?.observe(viewLifecycleOwner, Observer {
                                                        if (it != null) {
                                                            v.findNavController().navigate(
                                                                R.id.sellerConfirmation,
                                                                bundle
                                                            )

                                                        }
                                                    })
                                                } else {
                                                    val bundle = Bundle()
                                                    bundle.putString(Constants.Shipping, ShippingId)

                                                    v.findNavController().navigate(
                                                        R.id.cashOnDeliveryFragment,
                                                        bundle
                                                    )


                                                }
                                            }
                                        })
                                }
                            }
                        })
                    } else {

                        noo_items.visibility = View.VISIBLE
                        if (activity is DashBoardContainer) {
                            (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                        } else if (activity is BazaarContainer) {
                            (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                        }

                        noo_items.visibility = View.VISIBLE
//                        getDataVals()
                        progress.visibility = View.GONE
                        cartshop.setOnClickListener {
                            activity?.onBackPressed()
                        }
                    }
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getCartData()
    }

}
package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.payu.india.Payu.PayuConstants
import com.vrise.R
import com.vrise.bazaar.ex.ShoppingSuccess
import com.vrise.bazaar.ex.model.mainmodels.CartIDList
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newcart.NormalDelivery
import com.vrise.bazaar.ex.model.newcart.ShopDeliveryItem
import com.vrise.bazaar.ex.model.newcart.SlotDeliveryItem
import com.vrise.bazaar.ex.model.newcart.SpotDelivery
import com.vrise.bazaar.ex.shop.ShopDetailFragmentB
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OutViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Constants.Shipping
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.fragment_cart_new.*
import kotlinx.android.synthetic.main.fragment_cash_on_delivery.*
import kotlinx.android.synthetic.main.fragment_cash_on_delivery.textView357
import java.security.Key
import java.text.DecimalFormat

class CashOnDeliveryFragment : Fragment() {
    private lateinit var viewModel: OutViewModel
    var deliveryAddress = ""
    var totalAmount = 0.0
    var grandTotal = 0.0F
    var amount = 0.0
    var tempAmount = 0.0
    var walletAmount = 0.0

    var newval = 0.0F
    var newsval = 0
    var productInfo = ""
    var paymentType = ""
    var discountAmount = ""
    var cashbackAmount = ""
    var couponId = ""
    var maxCod = "0"
    var walletStatus = "0"
    private var subscriber = "0"
    var peak_time_charge = 0.0
    var online = ""
    var shippingmethod = ""
    var pickupcharge = ""
    var pickupdiscount = ""
    var OrderModel:String? = null
    var totalCartValue: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(OutViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_on_delivery, container, false)
    }
    override fun onResume() {
        super.onResume()
    }

    @SuppressLint("SetTextI18n")
    private fun getCartData() {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getcartData(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN)
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {
                        if (activity is DashBoardContainer) {
                            (activity as DashBoardContainer).setCartBadge(it?.cartCount ?: 0)
                        } else if (activity is BazaarContainer) {
                            (activity as BazaarContainer).setCartBadge(it?.cartCount ?: 0)
                        }
                        include4.visibility =View.GONE
                        OrderModel = it.order_model.toString()

                        val cart_ids = ArrayList<CartIDList>()
                        val delData = ArrayList<ShopDeliveryItem?>()
                        var del_d: ShopDeliveryItem
                        if (it.shopDelivery.isNullOrEmpty()) {
                        } else {
                            for (i in 0 until it.shopDelivery.size) {
                                del_d = ShopDeliveryItem()
                                del_d.seller = it.shopDelivery[i]?.seller.toString()

                                if (it.shopDelivery[i]?.spotDelivery != null) {
                                    del_d.spotDelivery = SpotDelivery()

                                        if (shippingmethod == "3") {
                                            del_d.spotDelivery?.deliveryCharge = 0
                                        } else {
                                            del_d.spotDelivery?.deliveryCharge = it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                        }


                                    if (shippingmethod == "3") {
                                        val firstValueHelper =
                                            it.shopDelivery[i]?.spotDelivery?.grandTotal
                                        val secondValueHelper =
                                            it.shopDelivery[i]?.spotDelivery?.deliveryCharge

                                         newval = firstValueHelper?.minus(secondValueHelper!!)!!.toFloat()

                                        del_d.spotDelivery?.grandTotal = newval.toInt()
                                    }else{

                                        val firstsValueHelper =
                                            it.shopDelivery[i]?.spotDelivery?.grandTotal
                                        val secondsValueHelper =
                                            it.shopDelivery[i]?.spotDelivery?.packing_charge

                                        if (firstsValueHelper != null) {
                                            newsval = firstsValueHelper
                                        }

                                        del_d.spotDelivery?.grandTotal = newsval
                                    }

                                    del_d.spotDelivery?.totalPrize =
                                        it.shopDelivery[i]?.spotDelivery?.totalPrize
                                    del_d.spotDelivery?.seller_pickup_charge =
                                        it.shopDelivery[i]?.spotDelivery?.seller_pickup_charge
                                    del_d.spotDelivery?.seller_pickup_discount ="0"
//                                        it.shopDelivery[i]?.spotDelivery?.seller_pickup_discount

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
                                    del_d.slotDelivery = java.util.ArrayList<SlotDeliveryItem>()
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
                        maxCod = it.max_cod
//                        discountAmount = it.shopDelivery!!.get(0)!!.spotDelivery?.seller_pickup_discount.toString()


                            if (shippingmethod == "3") {
                                val firstValueSHelper =
                                    it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal
                                val secondValueHelper =
                                    it.shopDelivery!!.get(0)!!.spotDelivery?.deliveryCharge

                                grandTotal = firstValueSHelper?.minus(secondValueHelper!!)!!.toFloat()

                                val newGrand = grandTotal
                              val discount = 0.0
//                                    it.shopDelivery?.get(0)?.spotDelivery?.seller_pickup_offer?.seller_discount?.toDouble()

                                grandTotal = newGrand.minus(discount!!).toFloat()

                            }else{

                                grandTotal =  (it.shopDelivery!!.get(0)!!.spotDelivery?.grandTotal?.toFloat()!!)
                            }

                        walletAmount = it.walletBalance.toDouble()
                        amount = (it.shopDelivery.get(0)!!.spotDelivery?.grandTotal?.toDouble()!!)
                        deliveryAddress = it.default_address_id
                        totalCartValue = it.totalCartvalue.toString()
                        maxCod = it.max_cod
//                        discountAmount = it.shopDelivery!!.get(0)!!.spotDelivery?.seller_pickup_discount.toString()
                        cod.setOnClickListener {
                            imageView82.visibility = View.GONE
                            imageView77.visibility = View.VISIBLE

                            include4.visibility =View.VISIBLE

                            if (activity is BazaarContainer) {
                                if (shippingmethod == "") {
                                    include4.visibility = View.GONE
                                    Toast.makeText(
                                        activity,
                                        "Please select a shipping method",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {
                                    include4.visibility = View.VISIBLE
                                    viewModel.sale(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            cart_ids = cart_ids,
                                            total_amount = grandTotal,
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
                                            shipping_method = shippingmethod

                                        )
                                    )?.observe(viewLifecycleOwner, Observer {
                                        if (it != null) {
                                            include4.visibility = View.GONE
                                            Toast.makeText(
                                                activity,
                                                "You have successfully completed the order",
                                                Toast.LENGTH_SHORT
                                            ).show()



                                            view?.findNavController()
                                                ?.navigate(R.id.myOrderFragment)
//                                            (requireActivity().findViewById<View>(R.id.navigation_new) as BottomNavigationView).selectedItemId =
//                                                R.id.myOrderFragment
                                            getCartData()
                                        }
                                    })
                                }

                            }
                            else  if (activity is DashBoardContainer) {

                                if (shippingmethod == "") {
                                    include4.visibility =View.GONE
                                    Toast.makeText(activity, "Please select a shipping method", Toast.LENGTH_SHORT).show()

                                }
                                else {
                                    include4.visibility =View.VISIBLE
                                    viewModel.sale(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            cart_ids = cart_ids ,
                                            total_amount = grandTotal,
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
                                            shipping_method = shippingmethod

                                        )
                                    )?.observe(viewLifecycleOwner, Observer {
                                        if (it != null) {
                                            include4.visibility = View.GONE
                                            Toast.makeText(
                                                activity,
                                                "You have successfully completed the order",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            view?.findNavController()
                                                ?.navigate(R.id.myOrderFragment)


                                            getCartData()
                                        }
                                    })
                                }
                            }
                        }


                        constraintLayout33.setOnClickListener {
                            imageView182.visibility = View.GONE
                            imageView177.visibility = View.VISIBLE

                            include4.visibility =View.VISIBLE

                            if (activity is DashBoardContainer) {
                                if (shippingmethod == "") {
                                    include4.visibility = View.GONE
                                    Toast.makeText(
                                        activity,
                                        "Please select a shipping method",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {
                                    include4.visibility = View.VISIBLE
                                    viewModel.sale(
                                        Request(
                                            utoken = Preference.get(
                                                activity,
                                                DATAFILE,
                                                Preference.TOKEN
                                            ),
                                            cart_ids = cart_ids,
                                            total_amount = grandTotal,
                                            discountAmount = discountAmount,
                                            cashbackAmount = cashbackAmount,
                                            coupon_id = couponId,
                                            grand_total = grandTotal.toString(),
                                            wallet_status = walletStatus,
                                            cod_status = "0",
                                            online_status = "1",
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
                                            shipping_method = shippingmethod

                                        )
                                    )?.observe(viewLifecycleOwner, Observer {
                                        if (it != null) {
                                            if (internet) {
                                                val bundle = Bundle()
                                                bundle.putString(
                                                    PayuConstants.AMOUNT,
                                                    amount.toString()
                                                )
                                                bundle.putString(
                                                    PayuConstants.PRODUCT_INFO,
                                                    productInfo
                                                )
                                                bundle.putString(PayuConstants.FIRST_NAME, "Priya")
                                                bundle.putString(PayuConstants.LASTNAME, "Monish")
                                                bundle.putSerializable(
                                                    "request", Request(
                                                        utoken = Preference.get(
                                                            activity,
                                                            DATAFILE,
                                                            Preference.TOKEN
                                                        ),
                                                        cart_ids = cart_ids,
                                                        total_amount = it.data?.total_amount,
                                                        discountAmount = discountAmount,
                                                        cashbackAmount = cashbackAmount,
                                                        coupon_id = couponId,
                                                        grand_total = grandTotal.toString(),
                                                        wallet_status = walletStatus,
                                                        cod_status = "0",
                                                        online_status = "1",
                                                        payment_status = if (amount > 0) {
                                                            "pending"
                                                        } else {
                                                            "paid"
                                                        },
                                                        online_amount = if (amount > 0) {
                                                            amount.toString()
                                                        } else {
                                                            "0"
                                                        },
                                                        wallet_amount = walletAmount.toString(),
                                                        cod_amount = "0",
                                                        online_data = "",
                                                        peak_time_charge = peak_time_charge.toString(),
                                                        shops = delData,
                                                        delivery_address_id = deliveryAddress

                                                    )
                                                )
                                                bundle.putString("payment_type", "online")
                                                bundle.putString(Shipping, shippingmethod)
                                                val transection: FragmentTransaction =
                                                    requireFragmentManager().beginTransaction()

                                                val mfragment = ShoppingSuccess()

                                                mfragment.setArguments(bundle)

                                                if (activity is BazaarContainer) {
                                                    transection.replace(
                                                        R.id.nav_host_fragment,
                                                        mfragment
                                                    ).detach(ShippingMethod())
                                                        .remove(ShippingMethod())
                                                        .addToBackStack(null)
                                                    transection.commit()
                                                } else if (activity is DashBoardContainer) {
                                                    transection.replace(
                                                        R.id.nav_host_fragment,
                                                        mfragment
                                                    ).addToBackStack(null)
                                                    transection.commit()

                                                }
                                            } else {
                                                Instances.toast(
                                                    activity,
                                                    getString(R.string.no_internet)
                                                )
                                            }


//                                            include4.visibility = View.GONE
//                                            Toast.makeText(
//                                                activity,
//                                                "You have successfully completed the order",
//                                                Toast.LENGTH_SHORT
//                                            ).show()
//
//
//
//                                            view?.findNavController()
//                                                ?.navigate(R.id.myOrderFragment)
////                                            (requireActivity().findViewById<View>(R.id.navigation_new) as BottomNavigationView).selectedItemId =
////                                                R.id.myOrderFragment
//                                            getCartData()
                                        }
                                    })
                                }

                            }
//                            else  if (activity is BazaarContainer) {
//                                if (internet) {
//                                    val bundle = Bundle()
//                                    bundle.putString(
//                                        PayuConstants.AMOUNT,
//                                        amount.toString()
//                                    )
//                                    bundle.putString(
//                                        PayuConstants.PRODUCT_INFO,
//                                        productInfo
//                                    )
//                                    bundle.putString(PayuConstants.FIRST_NAME, "")
//                                    bundle.putString(PayuConstants.LASTNAME, "")
//                                    bundle.putSerializable(
//                                        "request", Request(
//                                            utoken = Preference.get(
//                                                activity,
//                                                DATAFILE,
//                                                Preference.TOKEN
//                                            ),
//                                            cart_ids = cart_ids,
//                                            total_amount = it.data?.total_amount,
//                                            discountAmount = discountAmount,
//                                            cashbackAmount = cashbackAmount,
//                                            coupon_id = couponId,
//                                            grand_total = grandTotal.toString(),
//                                            wallet_status = walletStatus,
//                                            cod_status = "0",
//                                            online_status = "1",
//                                            payment_status = if (amount > 0) {
//                                                "pending"
//                                            } else {
//                                                "paid"
//                                            },
//                                            online_amount = if (amount > 0) {
//                                                amount.toString()
//                                            } else {
//                                                "0"
//                                            },
//                                            wallet_amount = walletAmount.toString(),
//                                            cod_amount = "0",
//                                            online_data = "",
//                                            peak_time_charge = peak_time_charge.toString(),
//                                            shops = delData,
//                                            delivery_address_id = deliveryAddress
//
//                                        )
//                                    )
//                                    bundle.putString("payment_type", paymentType)
//                                    bundle.putString(Shipping, shippingmethod)
//                                    val transection: FragmentTransaction =
//                                        requireFragmentManager().beginTransaction()
//
//                                    val mfragment = ShoppingSuccess()
//
//                                    mfragment.setArguments(bundle)
//
//                                    if (activity is BazaarContainer) {
//                                        transection.replace(
//                                            R.id.nav_host_fragment,
//                                            mfragment
//                                        ).detach(ShippingMethod())
//                                            .remove(ShippingMethod())
//                                            .addToBackStack(null)
//                                        transection.commit()
//                                    } else if (activity is DashBoardContainer) {
//                                        transection.replace(
//                                            R.id.nav_host_fragment,
//                                            mfragment
//                                        )
//                                            .detach(
//                                                ShippingMethod()
//                                            ).addToBackStack(null)
//                                        transection.commit()
//
//                                    }
//                                } else {
//                                    Instances.toast(
//                                        activity,
//                                        getString(R.string.no_internet)
//                                    )
//                                }
////                                if (shippingmethod == "") {
////                                    include4.visibility =View.GONE
////                                    Toast.makeText(activity, "Please select a shipping method", Toast.LENGTH_SHORT).show()
////
////                                }
////                                else {
////                                    include4.visibility =View.VISIBLE
////                                    viewModel.sale(
////                                        Request(
////                                            utoken = Preference.get(
////                                                activity,
////                                                DATAFILE,
////                                                Preference.TOKEN
////                                            ),
////                                            cart_ids = cart_ids ,
////                                            total_amount = grandTotal,
////                                            discountAmount = discountAmount,
////                                            cashbackAmount = cashbackAmount,
////                                            coupon_id = couponId,
////                                            grand_total = grandTotal.toString(),
////                                            wallet_status = walletStatus,
////                                            cod_status = "1",
////                                            online_status = "0",
////                                            payment_status = if (amount > 0) {
////                                                "pending"
////                                            } else {
////                                                "paid"
////                                            },
////                                            online_amount = "0",
////                                            wallet_amount = walletAmount.toString(),
////                                            cod_amount = grandTotal.toString(),
////                                            online_data = "",
////                                            shops = delData,
////                                            peak_time_charge = peak_time_charge.toString(),
////                                            delivery_address_id = deliveryAddress,
////                                            shipping_method = shippingmethod
////
////                                        )
////                                    )?.observe(viewLifecycleOwner, Observer {
////                                        if (it != null) {
////                                            include4.visibility = View.GONE
////                                            Toast.makeText(
////                                                activity,
////                                                "You have successfully completed the order",
////                                                Toast.LENGTH_SHORT
////                                            ).show()
////                                            view?.findNavController()
////                                                ?.navigate(R.id.myOrderFragment)
////
////
////                                            getCartData()
////                                        }
////                                    })
////                                }
//                            }
                        }
                        val precision = DecimalFormat("0.00")
                        textView357.setText("Rs." +
                                (String.format(precision.format( grandTotal))))
//                        continued.setOnClickListener {
//                            include4.visibility =View.VISIBLE
//
//                            if (activity is BazaarContainer) {
//                                if (shippingmethod == "") {
//                                        include4.visibility = View.GONE
//                                        Toast.makeText(
//                                            activity,
//                                            "Please select a shipping method",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//
//                                    } else {
//                                        include4.visibility = View.VISIBLE
//                                        viewModel.sale(
//                                            Request(
//                                                utoken = Preference.get(
//                                                    activity,
//                                                    DATAFILE,
//                                                    Preference.TOKEN
//                                                ),
//                                                cart_ids = cart_ids,
//                                                total_amount = grandTotal,
//                                                discountAmount = discountAmount,
//                                                cashbackAmount = cashbackAmount,
//                                                coupon_id = couponId,
//                                                grand_total = grandTotal.toString(),
//                                                wallet_status = walletStatus,
//                                                cod_status = "1",
//                                                online_status = "0",
//                                                payment_status = if (amount > 0) {
//                                                    "pending"
//                                                } else {
//                                                    "paid"
//                                                },
//                                                online_amount = "0",
//                                                wallet_amount = walletAmount.toString(),
//                                                cod_amount = grandTotal.toString(),
//                                                online_data = "",
//                                                shops = delData,
//                                                peak_time_charge = peak_time_charge.toString(),
//                                                delivery_address_id = deliveryAddress,
//                                                shipping_method = shippingmethod
//
//                                            )
//                                        )?.observe(viewLifecycleOwner, Observer {
//                                            if (it != null) {
//                                                include4.visibility = View.GONE
//                                                Toast.makeText(
//                                                    activity,
//                                                    "You have successfully completed the order",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//
//
//
//                                                view?.findNavController()
//                                                    ?.navigate(R.id.myOrderFragment)
////                                            (requireActivity().findViewById<View>(R.id.navigation_new) as BottomNavigationView).selectedItemId =
////                                                R.id.myOrderFragment
//                                                getCartData()
//                                            }
//                                        })
//                                    }
//
//                            }
//                            else  if (activity is DashBoardContainer) {
//
//                                    if (shippingmethod == "") {
//                                        include4.visibility =View.GONE
//                                        Toast.makeText(activity, "Please select a shipping method", Toast.LENGTH_SHORT).show()
//
//                                    }
//                                    else {
//                                        include4.visibility =View.VISIBLE
//                                        viewModel.sale(
//                                            Request(
//                                                utoken = Preference.get(
//                                                    activity,
//                                                    DATAFILE,
//                                                    Preference.TOKEN
//                                                ),
//                                                cart_ids = cart_ids ,
//                                                total_amount = grandTotal,
//                                                discountAmount = discountAmount,
//                                                cashbackAmount = cashbackAmount,
//                                                coupon_id = couponId,
//                                                grand_total = grandTotal.toString(),
//                                                wallet_status = walletStatus,
//                                                cod_status = "1",
//                                                online_status = "0",
//                                                payment_status = if (amount > 0) {
//                                                    "pending"
//                                                } else {
//                                                    "paid"
//                                                },
//                                                online_amount = "0",
//                                                wallet_amount = walletAmount.toString(),
//                                                cod_amount = grandTotal.toString(),
//                                                online_data = "",
//                                                shops = delData,
//                                                peak_time_charge = peak_time_charge.toString(),
//                                                delivery_address_id = deliveryAddress,
//                                                shipping_method = shippingmethod
//
//                                            )
//                                        )?.observe(viewLifecycleOwner, Observer {
//                                            if (it != null) {
//                                                include4.visibility = View.GONE
//                                                Toast.makeText(
//                                                    activity,
//                                                    "You have successfully completed the order",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                                view?.findNavController()
//                                                    ?.navigate(R.id.myOrderFragment)
//
//
//                                                getCartData()
//                                            }
//                                        })
//                                    }
//                            }
//                        }

                    }

                })
            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if(it.containsKey("Shipping")){
                shippingmethod = requireArguments().getString("Shipping").toString()
            }
        }



//        if (requireArguments().getString(Shipping, "").contains(Shipping)){
//            shippingmethod = requireArguments().getString(Shipping, "").toString()
//        }

        getCartData()
        include4.visibility =View.VISIBLE
    }


}

package com.vrise.bazaar.ex.shop.pages.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vrise.R
import com.vrise.bazaar.ex.ShoppingSuccess
import com.vrise.bazaar.ex.model.mainmodels.CartIDList
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newcart.NormalDelivery
import com.vrise.bazaar.ex.model.newcart.ShopDeliveryItem
import com.vrise.bazaar.ex.model.newcart.SlotDeliveryItem
import com.vrise.bazaar.ex.model.newcart.SpotDelivery
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OutViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Constants.AMO
import com.vrise.bazaar.ex.util.Constants.GRAND

import com.vrise.bazaar.ex.util.Constants.PEAK
import com.vrise.bazaar.ex.util.Constants.Shipping
import com.vrise.bazaar.ex.util.Constants.VALUE
import com.vrise.bazaar.ex.util.Constants.cod
import com.vrise.bazaar.ex.util.Constants.sellercharge
import com.vrise.bazaar.ex.util.Constants.sellerdiscount
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.payu.india.Payu.PayuConstants
import com.vrise.bazaar.ex.shop.ShopDetailFragmentB
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.out_fragment.*
import java.math.RoundingMode

class OutFragment : Fragment() {

    private lateinit var viewModel: OutViewModel
    var deliveryAddress = ""
    var totalAmount = 0.0
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
    var walletStatus = "0"
    private var subscriber = "0"
    var peak_time_charge = 0.0
    var online = ""
    var shippingmethod = ""
    var pickupcharge = ""
    var pickupdiscount = ""


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
        onViewStateRestored(savedInstanceState)
        return inflater.inflate(R.layout.out_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress.visibility = View.VISIBLE

        val value = requireArguments().getString(VALUE)
        editText1.setText(value)
        if (editText1.text.toString().isNotEmpty()) {
            textView96.visibility = View.VISIBLE
            textView196.visibility = View.GONE
        } else {
            textView96.visibility = View.GONE
            textView196.visibility = View.VISIBLE
        }

        viewModel = ViewModelProvider(
			this,
			ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
		).get(OutViewModel::class.java)


        editText1.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {
				if (editText1.toString().isNotEmpty()) {
					textView96.visibility = View.VISIBLE
					textView196.visibility = View.GONE
				}

			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				if (s.toString().isNotEmpty()) {
					textView96.visibility = View.VISIBLE
					textView196.visibility = View.GONE
				} else {
					textView96.visibility = View.GONE
					textView196.visibility = View.VISIBLE
				}
			}
		})
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        val strtext = bundle.getString(PEAK)

        if (strtext.isNullOrBlank() && strtext != "null") {
            peak_time_charge = 0.0
        } else {
            peak_time_charge = requireArguments().getString(PEAK, "0.0").toString().toDouble()
        }

        online = requireArguments().getString(cod, "").toString()
        shippingmethod = requireArguments().getString(Shipping, "").toString()

        pickupcharge = requireArguments().getString(sellercharge.toString(), "")
        pickupdiscount = requireArguments().getString(sellerdiscount, "").toString()
        if (shippingmethod == "1") {

            radioButton4.visibility = View.VISIBLE

        } else if (shippingmethod == "3")
            radioButton4.visibility = View.GONE

        getCartData()
    }

    private fun getCartData() {

        val bundle = this.arguments
        progress.visibility = View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getcartData(
					Request(
						utoken = Preference.get(activity, DATAFILE, TOKEN)
					)
				)?.observe(viewLifecycleOwner, Observer { cartItems ->
					val cart_ids = ArrayList<CartIDList>()
					val delData = ArrayList<ShopDeliveryItem?>()
					var del_d: ShopDeliveryItem

					maxCod = cartItems.max_cod

					if (bundle?.getString(PEAK).isNullOrBlank() &&
						bundle?.getString(PEAK) != "null"
					) {
						peak_time_charge = 0.0
					} else {
						peak_time_charge = bundle?.getString(PEAK, "0.0").toString().toDouble()
					}
					if (cartItems != null) {
						totalAmount = cartItems.totalCartvalue.toDouble() + peak_time_charge

						textView88.text = totalAmount.toString()

						checkBox.text = "Usable Balance " + cartItems.walletBalance
						deliveryAddress = cartItems.default_address_id

						if (cartItems.walletBalance.toDouble() > 0) {
							checkBox.visibility = View.VISIBLE
						} else {
							checkBox.visibility = View.GONE
						}

						try {
							radioButton4.isEnabled =
								cartItems.totalCartvalue.toDouble() <= cartItems.max_cod.toDouble()
							grandTotal = cartItems.totalCartvalue.toDouble() + peak_time_charge
							amount = cartItems.totalCartvalue.toDouble() + peak_time_charge
							textView214.text = amount.toString()

							val args = Bundle()
							args.putString(AMO, amount.toString())
							args.putString(GRAND, grandTotal.toString())

						} catch (e: Exception) {
							e.printStackTrace()
						}
						tempAmount = grandTotal

						if (cartItems.shopDelivery.isNullOrEmpty()) {
						} else {
							for (i in 0 until cartItems.shopDelivery.size) {
								del_d = ShopDeliveryItem()
								del_d.seller = cartItems.shopDelivery[i]?.seller.toString()

								if (cartItems.shopDelivery[i]?.spotDelivery != null) {
									del_d.spotDelivery = SpotDelivery()
									del_d.spotDelivery?.deliveryCharge =
										cartItems.shopDelivery[i]?.spotDelivery?.deliveryCharge
									del_d.spotDelivery?.grandTotal =
										cartItems.shopDelivery[i]?.spotDelivery?.grandTotal
									del_d.spotDelivery?.totalPrize =
										cartItems.shopDelivery[i]?.spotDelivery?.totalPrize
									del_d.spotDelivery?.seller_pickup_charge = pickupcharge
									del_d.spotDelivery?.seller_pickup_discount = pickupdiscount

									del_d.spotDelivery?.totalCommesion = ""
									del_d.spotDelivery?.deliverySlotId =
										cartItems.shopDelivery[i]?.spotDelivery?.deliveryData?.id
									del_d.spotDelivery?.packing_charge =
										cartItems.shopDelivery[i]?.spotDelivery?.packing_charge
									if (cartItems.shopDelivery[i]?.spotDelivery?.items.isNullOrEmpty()) {
									} else {
										del_d.spotDelivery?.itemids = ""
										del_d.spotDelivery?.deliveryDate = ""

										productInfo += "SpotDelivery("
										for (j in 0 until ((cartItems.shopDelivery[i]?.spotDelivery?.items?.size)
											?: 0)) {
											del_d.spotDelivery?.itemids =
												del_d.spotDelivery?.itemids.toString() + "," + cartItems.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()

											if (j == 0) {
												del_d.spotDelivery?.deliveryDate =
													cartItems.shopDelivery[i]?.spotDelivery?.items!![j]?.deliveryDate.toString()
											}

											cart_ids.add(CartIDList(cartItems.shopDelivery[i]?.spotDelivery?.items!![j]?.id.toString()))

											productInfo =
												productInfo + cartItems.shopDelivery[i]?.spotDelivery?.items!![j]?.name.toString() + ","
										}
										productInfo += ") \n"
									}
								} else {
									del_d.spotDelivery = SpotDelivery()
								}

								if (cartItems.shopDelivery[i]?.slotDelivery.isNullOrEmpty()) {
									del_d.slotDelivery = ArrayList<SlotDeliveryItem>()
								} else {
									del_d.slotDelivery = java.util.ArrayList<SlotDeliveryItem>()
									productInfo += "SlotDelivery("
									for (k in 0 until ((cartItems.shopDelivery[i]?.slotDelivery?.size)
										?: 0)) {
										(del_d.slotDelivery as java.util.ArrayList<SlotDeliveryItem>).add(
											SlotDeliveryItem(
												deliverySlotIds = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.deliveryData?.deliverySlotId.toString(),
												deliveryCharge = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.deliveryCharge,
												totalCommesion = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.totalCommesion,
												grandTotal = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.grandTotal,
												totalPrize = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.totalPrize,
												packing_charge = cartItems.shopDelivery[i]?.slotDelivery?.get(
													k
												)?.packing_charge
											)
										)
										del_d.slotDelivery?.get(k)?.itemids = ""
										del_d.slotDelivery?.get(k)?.deliveryDate = ""

										if (cartItems.shopDelivery[i]?.slotDelivery!![k]?.items.isNullOrEmpty()) {
										} else {
											for (l in 0 until ((cartItems.shopDelivery[i]?.slotDelivery!![k]?.items?.size)
												?: 0)) {
												del_d.slotDelivery?.get(k)?.itemids =
													del_d.slotDelivery?.get(k)?.itemids + "," + cartItems.shopDelivery[i]?.slotDelivery?.get(
														k
													)?.items?.get(l)?.id.toString()

												if (l == 0) {
													del_d.slotDelivery?.get(k)?.deliveryDate =
														cartItems.shopDelivery[i]?.slotDelivery?.get(
															k
														)?.items?.get(l)?.deliveryDate.toString()
												}

												cart_ids.add(CartIDList(cartItems.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.id.toString()))
												productInfo =
													productInfo + cartItems.shopDelivery[i]?.slotDelivery!![k]?.items!![l]?.name.toString() + ","
											}
										}
									}
									productInfo += ") \n"
								}

								if (cartItems.shopDelivery[i]?.normalDelivery != null) {
									if (cartItems.shopDelivery[i]?.normalDelivery?.items.isNullOrEmpty()) {
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
											cartItems.shopDelivery[i]?.normalDelivery?.packing_charge
												?: "0"
//										del_d.seller_pickup_charge=pickupcharge
//										del_d.seller_pickup_discount=pickupdiscount
										productInfo += "NormalDelivery("
										for (m in 0 until ((cartItems.shopDelivery[i]?.normalDelivery?.items?.size)
											?: 0)) {
											del_d.normalDelivery?.itemsid =
												del_d.normalDelivery?.itemsid + "," + cartItems.shopDelivery[i]?.normalDelivery?.items?.get(
													m
												)?.id.toString()

											if (m == 0) {
												del_d.normalDelivery?.deliveryDate = ""
											}
											cart_ids.add(CartIDList("0"))
											productInfo =
												productInfo + cartItems.shopDelivery[i]?.normalDelivery?.items!![m]?.name.toString() + ","
										}
										productInfo += ") \n"
									}
								} else {
									del_d.normalDelivery = NormalDelivery()
								}

								delData.add(del_d)
							}
						}

						checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
							if (cartItems != null) {
								if (isChecked) {
									walletStatus = "1"
									amount = tempAmount - cartItems.walletBalance.toDouble()
									walletAmount = if (amount > 0) {
										cartItems.walletBalance.toDouble()
									} else {
										tempAmount
									}
								} else {
									amount = tempAmount
									walletStatus = "0"
									walletAmount = 0.0
								}

								textView214.text = if (amount > 0) {
									amount.toBigDecimal().setScale(2, RoundingMode.CEILING)
										.toDouble().toString()
								} else {
									0.toString()
								}

								try {
									radioGroup.clearCheck()
								} catch (e: Exception) {
									e.printStackTrace()
								}
								paymentType = ""

							} else {
								buttonView.isChecked = false
							}
						}

						radioGroup.setOnCheckedChangeListener {
							/*stay down*/ group, checkedId ->
							try {
								if (amount > 0) {
									paymentType = when (checkedId) {
										R.id.radioButton2 -> {
											"online"
										}
										R.id.radioButton6 -> {
											"upi"
										}
										R.id.radioButton3 -> {
											"gpay"
										}
										R.id.radioButton4 -> {
											"cod"
										}
										else -> {
											""
										}
									}
								} else {
									radioButton4.isChecked = false
									radioButton6.isChecked = false
									radioButton2.isChecked = false
									radioButton3.isChecked = false
									paymentType = ""
								}
							} catch (e: Exception) {
								e.printStackTrace()
							}
						}


						button13.setOnClickListener(object : ClickListener() {
							override fun onOneClick(v: View) {
								progress.visibility = View.VISIBLE
								if (cartItems != null) {
									Instances.printAny(
										Request(
											utoken = Preference.get(activity, DATAFILE, TOKEN),
											cart_ids = cart_ids,
											total_amount = cartItems.totalCartvalue.toFloat(),
											discountAmount = discountAmount,
											cashbackAmount = cashbackAmount,
											coupon_id = couponId,
											grand_total = grandTotal.toString(),
											wallet_status = walletStatus,
											cod_status = if (paymentType == "cod") "1" else if (paymentType == "online") "0" else "0",
											online_status = if (paymentType == "cod") "0" else if (paymentType == "online") "1" else "0",
											payment_status = if (paymentType == "online") {
												"pending"
											} else {
												if (amount > 0) "pending"
												else "paid"
											},
											online_amount = if (paymentType == "online") {
												if (amount > 0) amount.toString() else "0"
											} else {
												"0"
											},
											wallet_amount = walletAmount.toString(),
											cod_amount = if (paymentType == "online" || paymentType == "gPay") {
												"0"
											} else {
												if (amount > 0) amount.toString() else "0"

											},
											online_data = "",
											peak_time_charge = peak_time_charge.toString(),
											shops = delData,
											delivery_address_id = deliveryAddress


										)
									)
									if (paymentType == "upi" || paymentType == "online" || paymentType == "gpay") {
										Instances.InternetCheck { internet ->
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
												bundle.putString(PayuConstants.FIRST_NAME, "")
												bundle.putString(PayuConstants.LASTNAME, "")
												bundle.putSerializable(
													"request", Request(
														utoken = Preference.get(
															activity,
															DATAFILE,
															TOKEN
														),
														cart_ids = cart_ids,
														total_amount = cartItems.totalCartvalue.toFloat(),
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
												bundle.putString("payment_type", paymentType)
												bundle.putString(Shipping, shippingmethod)
												val transection: FragmentTransaction =
													fragmentManager!!.beginTransaction()

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
													)
														.detach(
															ShippingMethod()
														).addToBackStack(null)
													transection.commit()

												}
											} else {
												toast(activity, getString(R.string.no_internet))
											}
										}
										//todo
									} else if (paymentType == "cod") {
										Instances.InternetCheck { internet ->
											if (internet) {
												viewModel.sale(
													Request(
														utoken = Preference.get(
															activity, DATAFILE, TOKEN
														),
														cart_ids = cart_ids,
														total_amount = cartItems.totalCartvalue.toFloat(),
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
														cod_amount = if (amount > 0) {
															amount.toString()
														} else {
															"0"
														},
														online_data = "",
														shops = delData,
														peak_time_charge = peak_time_charge.toString(),
														delivery_address_id = deliveryAddress,
														shipping_method = shippingmethod

													)
												)?.observe(viewLifecycleOwner, Observer {
													if (it!=null) {
														progress.visibility = View.GONE
														val transection: FragmentTransaction =
															fragmentManager!!.beginTransaction()
														val bundle = Bundle()
														val mfragment = MyOrderFragment()
														bundle.putString(
															Constants.SECOND,
															"Your Payment was successfully completed"
														)
														mfragment.setArguments(bundle)

														if (activity is BazaarContainer) {
															transection.replace(
																R.id.nav_host_fragment,
																mfragment
															).detach(
																ShopDetailFragmentB()
															).isAddToBackStackAllowed()

															transection.commit()

														} else if (activity is DashBoardContainer) {
//
															transection.replace(
																R.id.nav_host_fragment,
																mfragment
															).detach(
																ShopDetailFragmentB()
															).isAddToBackStackAllowed()
															transection.commit()

														}

													}
												})
											} else {
												toast(activity, getString(R.string.no_internet))
												progress?.visibility = View.GONE
											}
										}
									}
									else {
										if (amount > 0) {
											toast(activity, "Choose a payment type")
											progress.visibility = View.GONE
										} else {

											progress.visibility = View.GONE


											Instances.InternetCheck { internet ->
												if (internet) {
													viewModel.sale(
														Request(
															utoken = Preference.get(
																activity, DATAFILE, TOKEN
															),
															cart_ids = cart_ids,
															total_amount = cartItems.totalCartvalue,
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
															cod_amount = if (amount > 0) {
																amount.toString()
															} else {
																"0"
															},
															online_data = "",
															shops = delData,
															peak_time_charge = peak_time_charge.toString(),
															delivery_address_id = deliveryAddress,
															shipping_method = shippingmethod

														)
													)?.observe(viewLifecycleOwner, Observer {
														if (it!=null) {
															progress.visibility = View.GONE
															val transection: FragmentTransaction =
																fragmentManager!!.beginTransaction()
															val bundle = Bundle()
															val mfragment = MyOrderFragment()
															bundle.putString(
																Constants.SECOND,
																"Your Payment was successfully completed"
															)
															mfragment.setArguments(bundle)

															if (activity is BazaarContainer) {
																transection.replace(
																	R.id.nav_host_fragment,
																	mfragment
																).detach(
																	ShippingMethod()
																).isAddToBackStackAllowed()

																transection.commit()

															} else if (activity is DashBoardContainer) {
//
																transection.replace(
																	R.id.nav_host_fragment,
																	mfragment
																).detach(
																	ShippingMethod()
																).isAddToBackStackAllowed()
																transection.commit()

															}

														}
													})
												} else {
													toast(activity, getString(R.string.no_internet))
													progress?.visibility = View.GONE
												}
											}
										}
									}
								} else {
									toast(activity, "Cannot process payment")
									progress.visibility = View.GONE
								}
							}
						})

						textView96.setOnClickListener(object : ClickListener() {
							override fun onOneClick(v: View) {

								Instances.InternetCheck { internet ->
									if (internet) {
										editText1.clearFocus()
										textView215.visibility = View.VISIBLE
										textView215.requestFocus()
										if (walletStatus != "0") {
											if (checkBox.isChecked) {
												checkBox.isChecked = false
											}
										}

										viewModel.checkCoupon(
											Request(
												utoken = Preference.get(activity, DATAFILE, TOKEN),
												codecoupon = editText1.text.toString(),
												total = amount.toString()
											)
										)?.observe(viewLifecycleOwner, Observer { its ->
											if (its != null) {
												amount = its.discountTotal
												discountAmount = its.discountAmount.toString()
												couponId = its.couponId.toString()

												tempAmount = amount

												textView214.text = if (amount > 0) {
													amount.toBigDecimal()
														.setScale(2, RoundingMode.CEILING)
														.toDouble().toString()
												} else {
													0.toString()
												}
												if (its.discountAmount.toInt() <= 0) {
													if (its.cashbackAmount.toInt() > 0) {
														cashbackAmount =
															its.cashbackAmount.toString()
														textView97.text =
															"Cashback credited (₹$cashbackAmount)"
													} else {
														textView97.text = "Coupon Applied"
													}
												} else {
													textView97.text =
														"Coupon Applied (₹$discountAmount)"
													textView196.visibility = View.VISIBLE
													textView96.visibility = View.GONE
												}
												textView97.visibility = View.VISIBLE
												textView215.visibility = View.GONE
												textView215.clearFocus()
											} else {
												editText1.setText("")
												textView196.visibility = View.VISIBLE
												textView96.visibility = View.GONE
												textView97.visibility = View.GONE
												textView215.visibility = View.GONE
												textView215.clearFocus()
											}
										})
									} else {
										toast(activity, getString(R.string.no_internet))
									}
								}


							}
						})

						textView214.addTextChangedListener(object : TextWatcher {
							override fun afterTextChanged(s: Editable?) {

							}

							override fun beforeTextChanged(
								s: CharSequence?, start: Int, count: Int, after: Int
							) {

							}

							override fun onTextChanged(
								s: CharSequence?, start: Int, before: Int, count: Int
							) {
								if (amount == 0.0 || (s.toString().replace(
										"null", ""
									).trim() == "0" || s.toString().replace(
										"null", ""
									).trim() == "0.0")
								) {
									button13.text = "Continue"
								} else {
									button13.text = "Pay Now"
								}

								radioButton4.isEnabled = amount <= maxCod.toInt()
								if (!radioButton4.isEnabled) {
									if (paymentType == "cod") {
										paymentType = ""
									}
								}
							}
						})
					}

					linearLayout7.isVisible = cartItems?.defaultAddress != null
					textView264.text =
						"${cartItems?.defaultAddress?.state}, ${cartItems?.defaultAddress?.pincode}"
					textView263.text = cartItems?.defaultAddress?.postOffice
					textView262.text =
						"${cartItems?.defaultAddress?.landmark} , ${cartItems?.defaultAddress?.district}"
					textView261.text = cartItems?.defaultAddress?.address2
					textView260.text = cartItems?.defaultAddress?.address
					textView256.text = cartItems?.defaultAddress?.address_type
					progress?.visibility = View.GONE
				})
            } else {
                toast(activity, getString(R.string.no_internet))
                progress?.visibility = View.GONE
            }

        }

        textView196.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				textView196.isEnabled()
				textView196.visibility = View.GONE
				val transection: FragmentTransaction = fragmentManager!!.beginTransaction()
				val mfragment = OfferListFragment()
				val bundle = Bundle()

				bundle.putString(AMO, amount.toString())
				bundle.putString(GRAND, grandTotal.toString())
				bundle.putString(Constants.PEAK, peak_time_charge.toString())
				mfragment.setArguments(bundle) //data being send to SecondFragment
				transection.replace(R.id.nav_host_fragment, mfragment).detach(
					ShippingMethod()
				).addToBackStack(null)
				transection.commit()
//				activity!!.supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_host_fragment, OfferListFragment()).attach(OutFragment()).detach(OutFragment())
//                    .remove(OutFragment()).commit()


			}

		})
    }


}

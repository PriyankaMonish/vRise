package com.vrise.bazaar.ex

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OutViewModel
import com.vrise.bazaar.ex.shop.pages.main.OrdersFragment
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.printAny
import com.vrise.bazaar.ex.util.Instances.serviceApi
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.newpages.top.PayUResult
import com.payu.india.Model.PaymentParams
import com.payu.india.Model.PayuConfig
import com.payu.india.Model.PayuHashes
import com.payu.india.Payu.Payu
import com.payu.india.Payu.PayuConstants
import com.payu.payuui.Activity.PayUBaseActivity
import com.vrise.bazaar.ex.shop.pages.main.MyOrderFragment
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class ShoppingSuccess : Fragment() {

	private var merchantKey: String? = null
	private var userCredentials: String? = null
	private var mPaymentParams: PaymentParams? = null
	private var payuConfig: PayuConfig? = null
	private var environment: Int = PayuConstants.STAGING_ENV
	private var surl: String? = null
	private var furl: String? = null
	lateinit var viewModel: OutViewModel
	private var paymentType = ""
	private var shipment = ""
	private val TEZ_REQUEST_CODE = 123
	private val GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
	private val UPI_PAYMENT = 122

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_shop_success, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel = ViewModelProvider(
			this, ViewModelFactory(
				RepoLive.getInstance(
					serviceApi(
						activity
					), activity
				)
			)
		).get(OutViewModel::class.java)

		paymentType = arguments?.getString("payment_type", "").toString()
          shipment=arguments?.getString(Constants.Shipping, "").toString()
		Instances.InternetCheck { internet ->
			if (internet) {
				(activity?.application as BaseApp).getAppEnvironment()?.let {
					environment = it.gatewayEnviornment()
					merchantKey = it.merchantPay()
					surl = it.surl()
					furl = it.furl()
				}

				if (paymentType == "online") {
					initializePayu()
				} else if (paymentType == "gpay") {
					try {
						initializeGPay()
					} catch (e: Exception) {
						e.printStackTrace()
					}
				} else if (paymentType == "upi") {
					initializeUpi()
				} else {
					toast(activity, getString(R.string.could_not_receive_data))
					activity?.onBackPressed()
				}
			} else {
				toast(activity, getString(R.string.no_internet))
				activity?.onBackPressed()
			}
		}
	}

	private fun initializeUpi() {
		if (environment == PayuConstants.STAGING_ENV) {
			val uri = Uri.parse("upi://pay").buildUpon()
				.appendQueryParameter("pa", "bharatpe09891161964@yesbankltd") //"test@axisbank"
				.appendQueryParameter(
					"tr",
					(Random().nextInt() + System.currentTimeMillis()).toString()
				)
				.appendQueryParameter("pn", "IBR ADVERTISERS") //Test Merchant
				.appendQueryParameter("tn", "product_info") //test transaction note
				.appendQueryParameter("am", "amount")
				.appendQueryParameter("cu", "INR")
				.build()
			val upiPayIntent = Intent(Intent.ACTION_VIEW)
			upiPayIntent.data = uri
			val chooser = Intent.createChooser(upiPayIntent, "Pay with")
			if (null != chooser.resolveActivity(requireActivity().packageManager)) {
				startActivityForResult(chooser, UPI_PAYMENT)
			} else {
				toast(requireActivity(), "No UPI app found, please install one to continue")
			}
		}
		}

	private fun initializeGPay() {
		if (environment == PayuConstants.STAGING_ENV) {

			try {
				val uri = Uri.Builder()
					.scheme("upi")
					.authority("pay")
					.appendQueryParameter("pa", "bharatpe09891161964@yesbankltd") //msmonish4u@okicici Payee VPA  "test@axisbank"  //if the account number is 123 and IFSC code is ABC456, then try sending UPI id as 123@ABC456.ifsc.npci
					.appendQueryParameter("pn", "IBR ADVERTISERS") //Payee name ""
					/*.appendQueryParameter("mc", "1234") // "Payee merchant code"*/
					.appendQueryParameter(
						"tr",
						(Random().nextInt() + System.currentTimeMillis()).toString()
					) //Transaction reference ID *
					.appendQueryParameter("tn", "product_info") //Transaction note
					.appendQueryParameter("am", "amount")//Transaction amount in decimal format.
					.appendQueryParameter("cu", "INR") //Currency code
					/*.appendQueryParameter("url", "https://ibrbazaar.com/test")*/ //https://test.merchant.website
					.build()
				val intent = Intent(Intent.ACTION_VIEW)
				intent.data = uri
				intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME)
				startActivityForResult(intent, TEZ_REQUEST_CODE)
			} catch (e: ActivityNotFoundException) {
				toast(
					requireActivity(),
					"Install Google Pay(Tez) app and login to your account before using this payment option."
				)
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}

		else {

		}
	}

	private fun initializePayu() {
		Payu.setInstance(activity)
		var amount = "0.0"
		var productInfo = ""
		var name = ""
		var email = ""

		arguments?.let {
			amount = it.getString(PayuConstants.AMOUNT, "")
			productInfo = it.getString(PayuConstants.PRODUCT_INFO, "")
			name = it.getString(PayuConstants.FIRST_NAME, "")
			email = "joe@vriseconsulting.com"
		}
		var txnId = Random().nextInt()
		if (txnId < 0) {
			txnId -= txnId
		}

		userCredentials = "$merchantKey:$email"
		mPaymentParams = PaymentParams()

		mPaymentParams?.key = merchantKey
		mPaymentParams?.amount = amount
		mPaymentParams?.productInfo = productInfo
		mPaymentParams?.firstName = name
		mPaymentParams?.email = "joe@vriseconsulting.com"
		mPaymentParams?.phone = ""

		mPaymentParams?.txnId = (txnId + System.currentTimeMillis()).toString()
		mPaymentParams?.surl = surl
		mPaymentParams?.furl = furl
		mPaymentParams?.udf1 = Preference.get(activity, DATAFILE, TOKEN)
		mPaymentParams?.udf2 = ""
		mPaymentParams?.udf3 = ""
		mPaymentParams?.udf4 = ""
		mPaymentParams?.udf5 = ""
		mPaymentParams?.userCredentials = userCredentials

		payuConfig = PayuConfig()
		payuConfig?.environment = environment //PayuConstants.PRODUCTION_ENV
		if (environment == PayuConstants.STAGING_ENV) {
			generateHashFromServer(mPaymentParams)
		} else {
			generateHash(mPaymentParams)
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == PayuConstants.PAYU_REQUEST_CODE) {
			if (data != null) {
				try {

					val request = arguments?.getSerializable("request") as Request
					val merchant=  data.getStringExtra("result")
					val merchant2  =  data.getStringExtra("payu_response")
					if (merchant2 != null) {
						val json = Gson().toJson(merchant2 + "\n" + merchant)
						val merchants: PayUResult = Gson().fromJson(
							merchant2,
							PayUResult::class.java
						)


						request.payment_status = if ( merchants.status== "success") {
							"paid"
						} else {
							"pending"
						}
						request.online_data = merchant2 + "\n" + merchant
						printAny(request.online_data)

						request.shipping_method=shipment
						request.txnid = merchants.txnid
						printAny(request)
						if (merchants.status?.toLowerCase(Locale.ENGLISH) == "success") {
							viewModel.sale(
								request
							)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
								if (it!=null) {
									saledone("Your Payment was successfully completed")
								} else {
									saledone("Server unavailable. " +
											"If any issue, contact customer care with the transaction " +
											"& product details")
								}
							})
						} else {
							printAny("merchant ${merchants.errorMessage}")
							toast(activity, merchants.errorMessage)
						}
					} else {
						printAny("merchant2  null")
						toast(activity, getString(R.string.could_not_receive_data))
						try {
							saledone(getString(R.string.could_not_receive_data))
						} catch (e: Exception) {
							e.printStackTrace()
						}
					}
				} catch (e: Exception) {
					e.printStackTrace()
				}
			} else {
				printAny("data null")
				try {
					Instances.showSnackbar(activity,
						R.string.could_not_receive_data,
						R.string.ok,
						View.OnClickListener {
							view?.findNavController()?.popBackStack()
						})
					/*". Press back to redirect to checkout page"*/
				} catch (e: Exception) {
					e.printStackTrace()
				}
				toast(activity, getString(R.string.could_not_receive_data))
			}
		}

		else if (requestCode == TEZ_REQUEST_CODE) {

            printAny("txnId" + data?.getStringExtra("txnId"))
            printAny("responseCode" + data?.getStringExtra("responseCode"))
            printAny("ApprovalRefNo" + data?.getStringExtra("ApprovalRefNo"))
            printAny(data?.getStringExtra("Status"))
            printAny("txnRef" + data?.getStringExtra("txnRef"))
            // (SUBMITTED/SUCCESS/FAILURE)
            toast(requireActivity(), data?.getStringExtra("Status"))


            val request = arguments?.getSerializable("request") as Request
            request.payment_status = if (data?.getStringExtra("Status") == "SUCCESS") {
                "paid"
            } else {
                "pending"
            }
            request.txnid = data?.getStringExtra("txnId")
            request.online_data = data?.toString()
			request.shipping_method=shipment

            when(data?.getStringExtra("Status")) {
				"SUCCESS" -> {
					viewModel.sale(
						request
					)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
						if (it!=null) {
							saledone("Your Payment was successfully completed")
						} else {
							saledone("Server unavailable. If any issue, contact customer care with the transaction & product details")
						}
					})
				}
				"SUBMITTED" -> {
					//money debited
					Instances.showSnackbar(
						activity,
						R.string.issue_with_payment_amount_will_be_refunded,
						R.string.ok,
						View.OnClickListener {
							view?.findNavController()?.popBackStack()
						})
				}
				"FAILURE" -> {
					Instances.showSnackbar(
						activity,
						R.string.payment_failre,
						R.string.ok,
						View.OnClickListener {
							view?.findNavController()?.popBackStack()
						})
				}
                else -> {
                    Instances.showSnackbar(
						activity,
						R.string.could_not_receive_data,
						R.string.ok,
						View.OnClickListener {
							view?.findNavController()?.popBackStack()
						})
                }
            }

        }
        else if (requestCode == UPI_PAYMENT) {
			if ((RESULT_OK == resultCode) || (resultCode == 11)) {
				if (data != null) {
					val trxt = data.getStringExtra("response")
					val dataList = ArrayList<String>()
					dataList.add(trxt!!)
					upiPaymentDataOperation(dataList, data.getStringExtra("txnId"))
				} else {
					val dataList = ArrayList<String>()
					dataList.add("nothing")
					upiPaymentDataOperation(dataList, data?.getStringExtra("txnId"))
				}
			} else {
				//when user simply back without payment
				val dataList = ArrayList<String>()
				dataList.add("nothing")
				upiPaymentDataOperation(dataList, data?.getStringExtra("txnId"))
			}
		} else {
			try {
				Instances.showSnackbar(
					activity,
					R.string.could_not_receive_data,
					R.string.ok,
					View.OnClickListener {
						view?.findNavController()?.popBackStack()
					})
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}

	fun upiPaymentDataOperation(data: ArrayList<String>?, txnId: String?) {
		var str = data?.get(0)
		var paymentCancel = ""
		if (str == null) str = "discard"
		var status = ""
		var approvalRefNo = ""
		val response = str.split("&")
		response.forEach { element ->
			val equalStr = element.split("=")
			if (equalStr.size >= 2) {
				if (equalStr[0].toLowerCase(Locale.ENGLISH).equals("Status".toLowerCase(Locale.ENGLISH))) {
					status = equalStr[1].toLowerCase(Locale.ENGLISH)
				} else if (equalStr[0].toLowerCase(Locale.ENGLISH).equals(
						"ApprovalRefNo".toLowerCase(
							Locale.ENGLISH
						)
					) || equalStr[0].toLowerCase(Locale.ENGLISH).equals("txnRef".toLowerCase(Locale.ENGLISH))) {
					approvalRefNo = equalStr[1]
				}
			} else {
				paymentCancel = "Payment cancelled by user."
			}
		}

		when {
			status == "success" -> {
				toast(requireActivity(), "Transaction successful.")
				val request = arguments?.getSerializable("request") as Request
				request.payment_status = if (status == "success") {
					"paid"
				} else {
					"pending"
				}
				request.txnid = txnId
				request.shipping_method=shipment
				request.online_data = "status :$status, approvalRefNo/txnRef :$approvalRefNo, txnId :$txnId"
				viewModel.sale(request)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
					if (it!=null) {
						saledone("Your Payment was successfully completed")
					} else {
						saledone("Server unavailable. If any issue, contact customer care with the transaction & product details")
					}
				})
			}
			"Payment cancelled by user." == paymentCancel -> {
				toast(requireActivity(), "Payment cancelled by user.")
				saledone(status)
			}
			else -> {
				toast(requireActivity(), "Transaction failed.Please try again")
				saledone("Transaction failed.Please try again")
			}
		}
	}

	private fun generateHash(mPaymentParams: PaymentParams?) {
		val postParamsBuffer = StringBuffer()
		postParamsBuffer.append(concatParams(PayuConstants.KEY, mPaymentParams?.key!!))
		postParamsBuffer.append(concatParams(PayuConstants.AMOUNT, mPaymentParams.amount))
		postParamsBuffer.append(concatParams(PayuConstants.TXNID, mPaymentParams.txnId))
		postParamsBuffer.append(
			concatParams(
				PayuConstants.EMAIL, if (null == mPaymentParams.email) "" else mPaymentParams.email
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.PRODUCT_INFO, mPaymentParams.productInfo
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.FIRST_NAME,
				if (null == mPaymentParams.firstName) "" else mPaymentParams.firstName
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.UDF1, if (mPaymentParams.udf1 == null) "" else mPaymentParams.udf1
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.UDF2, if (mPaymentParams.udf2 == null) "" else mPaymentParams.udf2
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.UDF3, if (mPaymentParams.udf3 == null) "" else mPaymentParams.udf3
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.UDF4, if (mPaymentParams.udf4 == null) "" else mPaymentParams.udf4
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.UDF5, if (mPaymentParams.udf5 == null) "" else mPaymentParams.udf5
			)
		)
		postParamsBuffer.append(
			concatParams(
				PayuConstants.USER_CREDENTIALS,
				if (mPaymentParams.userCredentials == null) PayuConstants.DEFAULT else mPaymentParams.userCredentials
			)
		)
		if (null != mPaymentParams.offerKey) postParamsBuffer.append(
			concatParams(
				PayuConstants.OFFER_KEY, mPaymentParams.offerKey
			)
		)
		val postParams =
			if (postParamsBuffer[postParamsBuffer.length - 1] == '&') postParamsBuffer.substring(
				0, postParamsBuffer.length - 1
			).toString() else postParamsBuffer.toString()
		val getHashesFromServerTask = GetHashesFromServerTask(activity, this)
		getHashesFromServerTask.execute(postParams)
	}

	private class GetHashesFromServerTask(
		c: FragmentActivity?,
		val shoppingSuccess: ShoppingSuccess
	) : AsyncTask<String, String, PayuHashes>() {
		private var progressDialog: ProgressDialog? = null
		private val activity = WeakReference<FragmentActivity>(c)

		override fun onPreExecute() {
			super.onPreExecute()
			progressDialog = ProgressDialog(activity.get())
			progressDialog?.setMessage("Please wait...")
			progressDialog?.show()
		}

		override fun doInBackground(vararg postParams: String): PayuHashes {
			val payuHashes = PayuHashes()
			try {
				val url = URL("https://payu.herokuapp.com/get_hash")
				val postParam = postParams[0]
				val postParamsByte = postParam.toByteArray(charset("UTF-8"))
				val conn = url.openConnection() as HttpURLConnection
				conn.requestMethod = "POST"
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
				conn.setRequestProperty("Content-Length", postParamsByte.size.toString())
				conn.doOutput = true
				conn.outputStream.write(postParamsByte)
				val responseInputStream = conn.inputStream
				val responseStringBuffer = StringBuffer()
				val byteContainer = ByteArray(1024)
				var i: Int? = null
				while ({ i = responseInputStream.read(byteContainer); i }() != -1) {
					responseStringBuffer.append(String(byteContainer, 0, i!!))
				}
				val response = JSONObject(responseStringBuffer.toString())
				val payuHashIterator = response.keys()
				while (payuHashIterator.hasNext()) {
					val key = payuHashIterator.next()
					when (key) {
						"payment_hash" -> payuHashes.paymentHash = response.getString(key)
						"vas_for_mobile_sdk_hash" -> payuHashes.vasForMobileSdkHash =
							response.getString(key)
						"payment_related_details_for_mobile_sdk_hash" -> payuHashes.paymentRelatedDetailsForMobileSdkHash =
							response.getString(key)
						"delete_user_card_hash" -> payuHashes.deleteCardHash =
							response.getString(key)
						"get_user_cards_hash" -> payuHashes.storedCardsHash =
							response.getString(key)
						"edit_user_card_hash" -> payuHashes.editCardHash = response.getString(key)
						"save_user_card_hash" -> payuHashes.saveCardHash = response.getString(key)
						"check_offer_status_hash" -> payuHashes.checkOfferStatusHash =
							response.getString(key)
						else -> {
						}
					}
				}

			} catch (e: MalformedURLException) {
				e.printStackTrace()
			} catch (e: ProtocolException) {
				e.printStackTrace()
			} catch (e: IOException) {
				e.printStackTrace()
			} catch (e: JSONException) {
				e.printStackTrace()
			}
			return payuHashes
		}

		override fun onPostExecute(payuHashes: PayuHashes?) {
			super.onPostExecute(payuHashes)
			printAny(payuHashes)
			shoppingSuccess.launchSdkUI(payuHashes)
			progressDialog?.dismiss()
		}

	}

	fun launchSdkUI(payuHashes: PayuHashes?) {
		printAny(payuHashes)
		printAny(payuConfig)
		printAny(mPaymentParams)

		if (payuHashes != null) {
			val intent = Intent(activity, PayUBaseActivity::class.java)
			intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig)
			intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams)
			intent.putExtra(PayuConstants.PAYU_HASHES, payuHashes)
			startActivityForResult(intent, PayuConstants.PAYU_REQUEST_CODE)
		} else {
			printAny("payuHashes null")
			Toast.makeText(activity, getString(R.string.could_not_receive_data), Toast.LENGTH_SHORT)
				.show()
		}
	}

	private fun generateHashFromServer(mPaymentParams: PaymentParams?) {
		if (mPaymentParams != null) {
			val postParamsBuffer = HashMap<String, String>()
			postParamsBuffer[PayuConstants.KEY] = mPaymentParams.key
			postParamsBuffer[PayuConstants.AMOUNT] = mPaymentParams.amount
			postParamsBuffer[PayuConstants.TXNID] = mPaymentParams.txnId
			postParamsBuffer[PayuConstants.EMAIL] = mPaymentParams.email ?: ""
			postParamsBuffer[PayuConstants.PRODUCT_INFO] = mPaymentParams.productInfo
			postParamsBuffer[PayuConstants.FIRST_NAME] = mPaymentParams.firstName ?: ""
			postParamsBuffer[PayuConstants.UDF1] = mPaymentParams.udf1 ?: ""
			postParamsBuffer[PayuConstants.UDF2] = mPaymentParams.udf2 ?: ""
			postParamsBuffer[PayuConstants.UDF3] = mPaymentParams.udf3 ?: ""
			postParamsBuffer[PayuConstants.UDF4] = mPaymentParams.udf4 ?: ""
			postParamsBuffer[PayuConstants.UDF5] = mPaymentParams.udf5 ?: ""
			postParamsBuffer[PayuConstants.USER_CREDENTIALS] =
				mPaymentParams.userCredentials ?: PayuConstants.DEFAULT
			mPaymentParams.offerKey?.let {
				postParamsBuffer[PayuConstants.OFFER_KEY] = it
			}
			printAny(mPaymentParams)
			var progressDialog: ProgressDialog? = null
			progressDialog = ProgressDialog(activity)
			progressDialog.setMessage("Please wait...")
			progressDialog.show()
			var payuHashes: PayuHashes? = null
			try {
				payuHashes = PayuHashes()
				val postParam = postParamsBuffer
				printAny(postParam)

				payuHashes = null
				viewModel.getHashs(postParam)
					?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
						if (it != null) {
							payuHashes = PayuHashes()
							payuHashes?.paymentHash = it.paymentHash
							payuHashes?.vasForMobileSdkHash = it.vasForMobileSdkHash
							payuHashes?.paymentRelatedDetailsForMobileSdkHash =
								it.paymentRelatedDetailsForMobileSdkHash
							payuHashes?.deleteCardHash = it.deleteUserCardHash
							payuHashes?.storedCardsHash = it.getUserCardsHash
							payuHashes?.editCardHash = it.editUserCardHash
							payuHashes?.saveCardHash = it.saveUserCardHash
							payuHashes?.checkOfferStatusHash = it.checkOfferStatusHash

							payuHashes?.paymentRelatedDetailsForMobileSdkHash?.let {
								launchSdkUI(payuHashes)
							}
						}
					})
			} catch (e: MalformedURLException) {
				payuHashes = null
				e.printStackTrace()
			} catch (e: ProtocolException) {
				payuHashes = null
				e.printStackTrace()
			} catch (e: IOException) {
				payuHashes = null
				e.printStackTrace()
			} catch (e: JSONException) {
				payuHashes = null
				e.printStackTrace()
			}

			printAny(payuHashes)

			if (payuHashes == null) {
				printAny("payuHashes null")
			}
			progressDialog.dismiss()

		} else {
			printAny("mPaymentParams null")
			activity?.let { toast(it, "Error") }
		}
	}

	private fun concatParams(key: String, value: String): String {
		return "$key=$value&"
	}

	fun saledone(s: String) {
		val bundle = Bundle()
		bundle.putString(Constants.SECOND, s)
		toast(activity,s)

		if (activity is BazaarContainer) {
		view?.findNavController()?.navigate(R.id.action_shoppingSuccess_to_navigation_my_order, bundle)
		}
		else if (activity is DashBoardContainer) {
			toast(activity,s)
			view?.findNavController()?.navigate(R.id.action_shoppingSuccess_to_navigation_my_order, bundle)

		}
	}

}

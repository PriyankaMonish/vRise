package com.vrise.bazaar.ex.shop.pages

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.PayWalletViewModel
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.serviceApi
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
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.*

class PayWalletFragment : Fragment() {

	private var merchantKey: String? = null
	private var userCredentials: String? = null
	private var mPaymentParams: PaymentParams? = null
	private var payuConfig: PayuConfig? = null
	private var environment: Int = PayuConstants.PRODUCTION_ENV
	private var surl: String? = null
	private var furl: String? = null
	private var amoun = "0.0"
	private lateinit var viewModel: PayWalletViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_pay_wallet, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		viewModel = ViewModelProvider(this, ViewModelFactory(RepoLive.getInstance(serviceApi(activity), activity))).get(PayWalletViewModel::class.java)

		Instances.InternetCheck { internet ->
			if (internet) {
				Payu.setInstance(activity)
				var amount = "0.0"
				val productInfo = "wallet update"
				var name = ""
				var email = "joe@vriseconsulting.com"

				(activity?.application as BaseApp).getAppEnvironment()?.let {
					environment = it.gatewayEnviornment()
					merchantKey = it.merchantPay()
					surl = it.surl()
					furl = it.furl()
				}

				arguments?.let {
					amount = BigDecimal(it.getString(PayuConstants.AMOUNT, "0")).setScale(1, RoundingMode.HALF_EVEN).toString()
					amoun = amount
					email = "wallet@ibrbazaar.com"
					name = Preference.get(activity, Preference.DATAFILE, Preference.NAME).toString()
				}
				var txnId = Random().nextInt()
				if (txnId < 0) {
					txnId -= txnId
				}

				userCredentials = "$merchantKey:$email"
				mPaymentParams = PaymentParams()
				mPaymentParams?.key =
                    merchantKey
				mPaymentParams?.amount = amount
				mPaymentParams?.productInfo = productInfo
				mPaymentParams?.firstName = name
				mPaymentParams?.email = ""
				mPaymentParams?.phone = ""

				mPaymentParams?.txnId = (txnId + System.currentTimeMillis()).toString()
				mPaymentParams?.surl = surl
				mPaymentParams?.furl = furl
				mPaymentParams?.udf1 = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN)
				mPaymentParams?.udf2 = ""
				mPaymentParams?.udf3 = ""
				mPaymentParams?.udf4 = ""
				mPaymentParams?.udf5 = ""
				mPaymentParams?.userCredentials = userCredentials

				payuConfig = PayuConfig()
				payuConfig?.environment = environment

				if (environment == PayuConstants.PRODUCTION_ENV) {
					generateOnlineHash(mPaymentParams)
				} else {
					generateStagingHash(mPaymentParams)
				}

			} else {
				Instances.toast(activity, getString(R.string.no_internet))
			}
		}
	}

	private fun generateStagingHash(mPaymentParams: PaymentParams?) {
		if (mPaymentParams != null) {
			val postParamsBuffer = StringBuffer()
			postParamsBuffer.append(concatParams(PayuConstants.KEY, mPaymentParams.key))
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
					PayuConstants.FIRST_NAME, if (null == mPaymentParams.firstName) "" else mPaymentParams.firstName
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
			val postParams = if (postParamsBuffer[postParamsBuffer.length - 1] == '&') postParamsBuffer.substring(
				0, postParamsBuffer.length - 1
			).toString() else postParamsBuffer.toString()
			GetHashesFromServerTask(activity, this).execute(postParams)
		}
	}

	private class GetHashesFromServerTask(
		c: FragmentActivity?, val payWalletFragment: PayWalletFragment
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
						"vas_for_mobile_sdk_hash" -> payuHashes.vasForMobileSdkHash = response.getString(key)
						"payment_related_details_for_mobile_sdk_hash" -> payuHashes.paymentRelatedDetailsForMobileSdkHash =
							response.getString(key)
						"delete_user_card_hash" -> payuHashes.deleteCardHash = response.getString(key)
						"get_user_cards_hash" -> payuHashes.storedCardsHash = response.getString(key)
						"edit_user_card_hash" -> payuHashes.editCardHash = response.getString(key)
						"save_user_card_hash" -> payuHashes.saveCardHash = response.getString(key)
						"check_offer_status_hash" -> payuHashes.checkOfferStatusHash = response.getString(key)
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
			Instances.printAny(payuHashes)
			payWalletFragment.launchSdkUI(payuHashes)
			progressDialog?.dismiss()
		}

	}

	fun launchSdkUI(payuHashes: PayuHashes?) {
		Instances.printAny(payuHashes)
		Instances.printAny(payuConfig)
		Instances.printAny(mPaymentParams)

		if (payuHashes != null) {
			val intent = Intent(activity, PayUBaseActivity::class.java)
			intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig)
			intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams)
			intent.putExtra(PayuConstants.PAYU_HASHES, payuHashes)
			startActivityForResult(intent, PayuConstants.PAYU_REQUEST_CODE)
		} else {
			Instances.toast(activity, (getString(R.string.could_not_receive_data)))
		}
	}

	private fun concatParams(key: String, value: String): String {
		return "$key=$value&"
	}

	private fun generateOnlineHash(mPaymentParams: PaymentParams?) {
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
			postParamsBuffer[PayuConstants.USER_CREDENTIALS] = mPaymentParams.userCredentials ?: PayuConstants.DEFAULT
			mPaymentParams.offerKey?.let {
				postParamsBuffer[PayuConstants.OFFER_KEY] = it
			}
			Instances.printAny(mPaymentParams)
			var progressDialog: ProgressDialog? = null
			progressDialog = ProgressDialog(activity)
			progressDialog.setMessage("Please wait...")
			progressDialog.show()
			var payuHashes: PayuHashes? = null
			try {
				payuHashes = PayuHashes()
				val postParam = postParamsBuffer
				Instances.printAny(postParam)

				payuHashes = null
				viewModel.getHashs(postParam)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
					if (it != null) {
						payuHashes = PayuHashes()
						payuHashes?.paymentHash = it.paymentHash
						payuHashes?.vasForMobileSdkHash = it.vasForMobileSdkHash
						payuHashes?.paymentRelatedDetailsForMobileSdkHash = it.paymentRelatedDetailsForMobileSdkHash
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

			Instances.printAny(payuHashes)

			if (payuHashes == null) {
				/*Toast.makeText(
					activity, getString(R.string.could_not_receive_data), Toast.LENGTH_SHORT
				).show()*/
			}
			progressDialog.dismiss()

		} else {
			activity?.let { Instances.toast(it, "Error") }
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == PayuConstants.PAYU_REQUEST_CODE) {
			if (data != null) {
				try {
					val merchant: String? = data.getStringExtra("result")
					val merchant2: String? = data.getStringExtra("payu_response")
					if (merchant2 != null) {
						val merchants: PayUResult = Gson().fromJson(merchant2, PayUResult::class.java)
						val request = Request(
							utoken = Preference.get(activity, DATAFILE, TOKEN),
							amount = amoun,
							online_data = merchant2 + "\n" + merchant,
							txnid = merchants.txnid,
							payment_status = if (merchants.status == "success") {
								"paid"
							} else {
								"pending"
							}
						)
						Instances.printAny(request)
						if (merchants.status?.toLowerCase(Locale.ENGLISH) == "success") {
							viewModel.sale(
								request
							)?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
								if (it) {
									Instances.toast(activity, merchants.status)
								} else {
									Instances.snack(
										activity,
										"Server unavailable. If the amount deducted from your bank account (but not showing in your wallet), contact customer care with the transaction details"
									)
								}
							})
						} else {
							Instances.toast(activity, merchants.errorMessage)
						}
					} else {
						Instances.toast(activity, getString(R.string.could_not_receive_data))
					}
				} catch (e: Exception) {
					e.printStackTrace()
				}

				if (activity is BazaarContainer) {
					view?.findNavController()?.navigate(R.id.action_payWalletFragment_to_navigation_wallet2)
				} else if (activity is DashBoardContainer) {
					view?.findNavController()?.navigate(R.id.action_payWalletFragment_to_navigation_wallet)
				}

			} else {
				Instances.toast(activity, getString(R.string.could_not_receive_data))
				if (activity is BazaarContainer) {
					view?.findNavController()?.navigate(R.id.action_payWalletFragment_to_navigation_wallet2)
				} else if (activity is DashBoardContainer) {
					view?.findNavController()?.navigate(R.id.action_payWalletFragment_to_navigation_wallet)
				}
			}


		}
	}
}

package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.subscriber.db.DataContract
import com.vrise.bazaar.newpages.subscriber.db.DataDbHelper
import com.vrise.bazaar.newpages.top.PayUResult
import com.vrise.bazaar.newpages.top.temps.Misc
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.ID
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.payu.india.Model.PaymentParams
import com.payu.india.Model.PayuConfig
import com.payu.india.Model.PayuHashes
import com.payu.india.Payu.Payu
import com.payu.india.Payu.PayuConstants
import com.payu.payuui.Activity.PayUBaseActivity
import kotlinx.android.synthetic.main.app_bar_sub_container.*
import kotlinx.android.synthetic.main.frgament_moneys.*
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

//class SuccessFragment : InitSub() {
//
//    private var payuHashs: PayuHashes? = null
//    private var merchantKey: String? = null
//    private var userCredentials: String? = null
//    private var mPaymentParams: PaymentParams? = null
//    private var payuConfig: PayuConfig? = null
//
//    var progress = 0
//
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    override fun initControl() {
//        Payu.setInstance(requireContext())
//        payuHashs = PayuHashes()
//        getDatas()
//        activity?.page_title?.text = "Payment"
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        try {
//            if (requestCode == PayuConstants.PAYU_REQUEST_CODE) {
//                if (data != null) {
//                    try {
//                        val merchant: String? = data.getStringExtra("result")
//                        val merchant2: String? = data.getStringExtra("payu_response")
//                        if(merchant2 != null){
//                            val merchants: PayUResult = Gson().fromJson(merchant2, PayUResult::class.java)
//                            progressBar20.visibility = View.GONE
//                            closw_id.visibility = View.VISIBLE
//                            if (merchants.status?.toLowerCase() == "success") {
//                                if (activity != null && isAdded) {
//                                    if (HelperMethods.hasConnection(activity)) {
//                                        apiService?.billPayment(
//                                            Request(
//                                                utoken = Preference.getSinglePreference(activity, DATAFILE, ID),
//                                                payment = merchants.amount,
//                                                paymentData = "$merchant2 \n$merchant"
//                                            )
//                                        )?.enqueue(object : CallbackClient(activity, object : Interfaces.Callback {
//                                            override fun additionalData(display: String?, logout: Boolean) {
//
//                                            }
//
//                                            override fun failed(t: Throwable) {
//                                                savetoDataBase(merchants.status, merchant2)
//                                                t.printStackTrace()
//                                            }
//
//                                            override fun success(response: Response?, dataset: Data?, state: Boolean) {
//                                                Misc.println(dataset)
//                                                if (state) {
//                                                    closw_id.setImageDrawable(
//                                                        ContextCompat.getDrawable(
//                                                            activity!!,
//                                                            R.drawable.ic_success_inactive
//                                                        )
//                                                    )
//                                                    textView168.text =
//                            "Payment Successful.. \nAn amount of ${merchants.amount} was debited from your account."
//                                                    textView193.visibility = View.VISIBLE
//                                                } else {
//                                                    savetoDataBase(
//                                                        merchants.status,
//                                                        merchant2
//                                                    )
//                                                }
//                                            }
//                                        }) {})
//                                    } else {
//                                        savetoDataBase(merchants.status, merchant2)
//                                    }
//                                }
//                            } else {
//                                closw_id.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_close_red))
//                                textView168.text = "Transaction Unsuccessful."
//                                textView193.visibility = View.VISIBLE
//                                textView194.visibility = View.VISIBLE
//                                textView194.setOnClickListener {
//                                    closw_id.visibility = View.GONE
//                                    textView194.isClickable = false
//                                    getDatas()
//                                }
//                            }
//                        } else {
//                            textView168.text = getString(R.string.could_not_receive_data)
//                            Toast.makeText(activity, getString(R.string.could_not_receive_data), Toast.LENGTH_LONG).show()
//                        }
//                        textView193.setOnClickListener {
//                            fragmentManager?.popBackStack()
//                        }
//                    } catch (e: java.lang.Exception) {
//                        e.printStackTrace()
//                    }
//                } else {
//                    textView168.text = getString(R.string.could_not_receive_data)
//                    Toast.makeText(activity, getString(R.string.could_not_receive_data), Toast.LENGTH_LONG).show()
//                    fragmentManager?.popBackStack()
//                }
//            }
//        } catch (e: Exception) {
//            textView168.text = getString(R.string.could_not_receive_data)
//            Toast.makeText(activity, getString(R.string.could_not_receive_data), Toast.LENGTH_LONG).show()
//            fragmentManager?.popBackStack()
//            e.printStackTrace()
//        }
//    }
//
//    var dbHelper: DataDbHelper? = null
//    private fun savetoDataBase(title: String, subtitle: String) {
//        try {
//            if (activity != null && isAdded) {
//                dbHelper = DataDbHelper(activity!!)
//                val db = dbHelper?.writableDatabase
//                val values = ContentValues().apply {
//                    put(DataContract.DataEntry.COLUMN_AMOUNT, title)
//                    put(DataContract.DataEntry.COLUMN_DETAILS, subtitle)
//                }
//                db?.insert(DataContract.DataEntry.TABLE_NAME, null, values)
//            }
//        } catch (e: Exception) {
//            dbHelper?.close()
//            e.printStackTrace()
//        }
//    }
//
//    private fun getDatas() {
//        textView168.text = ""
//        textView193.visibility = View.GONE
//        textView194.visibility = View.GONE
//        textView194.isClickable = true
//        progressBar20.visibility = View.VISIBLE
//        val mCountDownTimer = object : CountDownTimer(500, 1) {
//            override fun onTick(millisUntilFinished: Long) {
//            }
//
//            override fun onFinish() {
//                Handler().postDelayed({
//                    progressBar20.progress = 100
//                    navigateToBaseActivity()
//                }, 100)
//            }
//        }
//
//        mCountDownTimer.start()
//
//    }
//
//    fun launchSdkUI(payuHashes: PayuHashes?) {
//        val intent = Intent(activity, PayUBaseActivity::class.java)
//        intent.putExtra(PayuConstants.PAYU_CONFIG, payuConfig)
//        intent.putExtra(PayuConstants.PAYMENT_PARAMS, mPaymentParams)
//        intent.putExtra(PayuConstants.PAYU_HASHES, payuHashes)
//        startActivityForResult(intent, PayuConstants.PAYU_REQUEST_CODE)
//    }
//
//    private fun navigateToBaseActivity() {
//
//        var email = ""
//        var name = ""
//        val phone = ""
//        var amount = ""
//        var productInfo = ""
//        var environment = PayuConstants.STAGING_ENV
//        merchantKey = ""
//        var surl = ""
//        var furl = ""
//
//        (activity?.application as BaseApp).getAppEnvironment()?.let {
//            environment = it.gatewayEnviornment()
//            merchantKey = it.merchantPay()
//            surl = it.basUrl() + "payumoney/payResponse/success"
//            furl = it.basUrl() + "payumoney/payResponse/failure"
//        }
//
//        arguments?.let {
//            amount = it.getString(PayuConstants.AMOUNT, "")
//            productInfo = it.getString(PayuConstants.PRODUCT_INFO, "")
//            name = it.getString(PayuConstants.FIRST_NAME, "")
//            email = "SUB_$name@AGT_${it.getString(PayuConstants.LASTNAME, "")}.paytoall.in"
//        }
//
//        var txnId = Random().nextInt()
//        if (txnId < 0) {
//            txnId -= txnId
//        }
//
//        userCredentials = "$merchantKey:$email"   /*"merchant_key : user_id"*/
//
//        mPaymentParams = PaymentParams()
//
//        mPaymentParams?.apply {
//            this.key = merchantKey
//            this.amount = amount
//            this.productInfo = productInfo
//            this.firstName = name
//            this.email = email
//            this.phone = phone
//            this.txnId = "$txnId${System.currentTimeMillis()}"
//            this.surl = surl //https://payuresponse.firebaseapp.com/success
//            this.furl = furl //https://payuresponse.firebaseapp.com/failure
//            this.notifyURL = ""//it.surl //for lazy pay
//            this.udf1 = Preference.getSinglePreference(activity, Constants.DATAFILE, ID)
//            this.udf2 = ""
//            this.udf3 = ""
//            this.udf4 = ""
//            this.udf5 = ""
//            this.userCredentials = userCredentials//""
//            /*it.offerKey = "cardnumber@8370"*/
//        }
//
//        payuConfig = PayuConfig()
//        payuConfig?.environment = environment
//
//
//        if (environment == PayuConstants.PRODUCTION_ENV) {
//            generateHashFromServer(mPaymentParams, apiService)
//        } else {
//            generateHash(mPaymentParams, apiService)
//        }
//
//    }
//
//    private fun generateHashFromServer(mPaymentParams: PaymentParams?, apiService: ApiService?) {
//        if (mPaymentParams != null) {
//            val postParamsBuffer = HashMap<String, String>()
//            postParamsBuffer[PayuConstants.KEY] = mPaymentParams.key
//            postParamsBuffer[PayuConstants.AMOUNT] = mPaymentParams.amount
//            postParamsBuffer[PayuConstants.TXNID] = mPaymentParams.txnId
//            postParamsBuffer[PayuConstants.EMAIL] = mPaymentParams.email ?: ""
//            postParamsBuffer[PayuConstants.PRODUCT_INFO] = mPaymentParams.productInfo
//            postParamsBuffer[PayuConstants.FIRST_NAME] = mPaymentParams.firstName ?: ""
//            postParamsBuffer[PayuConstants.UDF1] = mPaymentParams.udf1 ?: ""
//            postParamsBuffer[PayuConstants.UDF2] = mPaymentParams.udf2 ?: ""
//            postParamsBuffer[PayuConstants.UDF3] = mPaymentParams.udf3 ?: ""
//            postParamsBuffer[PayuConstants.UDF4] = mPaymentParams.udf4 ?: ""
//            postParamsBuffer[PayuConstants.UDF5] = mPaymentParams.udf5 ?: ""
//            postParamsBuffer[PayuConstants.USER_CREDENTIALS] = mPaymentParams.userCredentials ?: PayuConstants.DEFAULT
//            mPaymentParams.offerKey?.let {
//                postParamsBuffer[PayuConstants.OFFER_KEY] = it
//            }
//            HelperMethods.println(mPaymentParams)
//            /*
//            val getHashesFromServerTask = GetHashesFromTask(activity, textView168, payuConfig, mPaymentParams, apiService, this, payuHashs)
//            getHashesFromServerTask.execute(postParamsBuffer)
//            */
//
//            var progressDialog: ProgressDialog? = null
//            progressDialog = ProgressDialog(activity)
//            progressDialog.setMessage("Please wait...")
//            progressDialog.show()
//            var payuHashes: PayuHashes? = null
//            try {
//                payuHashes = PayuHashes()
//                val postParam = postParamsBuffer
//                HelperMethods.println(postParam)
//                apiService?.getHashes(postParam)?.enqueue(object :
//                    ConsAndroidCallback(activity, this@SuccessFragment, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun failed(t: Throwable) {
//                            payuHashes = null
//                            t.printStackTrace()
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//                                if (data != null) {
//                                    payuHashes?.paymentHash = data.paymentHash
//                                    payuHashes?.vasForMobileSdkHash = data.vasForMobileSdkHash
//                                    payuHashes?.paymentRelatedDetailsForMobileSdkHash =
//                                        data.paymentRelatedDetailsForMobileSdkHash
//                                    payuHashes?.deleteCardHash = data.deleteUserCardHash
//                                    payuHashes?.storedCardsHash = data.getUserCardsHash
//                                    payuHashes?.editCardHash = data.editUserCardHash
//                                    payuHashes?.saveCardHash = data.saveUserCardHash
//                                    payuHashes?.checkOfferStatusHash = data.checkOfferStatusHash
//
//                                    payuHashes?.paymentRelatedDetailsForMobileSdkHash?.let {
//                                        launchSdkUI(payuHashes)
//                                    }
//                                } else {
//                                    payuHashes = null
//                                }
//                            } else {
//                                payuHashes = null
//                            }
//                        }
//                    }) {})
//            } catch (e: MalformedURLException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: ProtocolException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: IOException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: JSONException) {
//                payuHashes = null
//                e.printStackTrace()
//            }
//
//            HelperMethods.println(payuHashes)
//            payuHashs = null
//            if (payuHashes == null) {
//                textView168.text = getString(R.string.could_not_receive_data)
//            }
//            progressDialog.dismiss()
//
//        } else {
//            activity?.let { HelperMethods.toastit(it, "Error") }
//        }
//    }
//
//    private class GetHashesFromTask(
//        activity: FragmentActivity?,
//        textView168: TextView,
//        val payuConfig: PayuConfig?,
//        val mPaymentParams: PaymentParams,
//        val apiService: ApiService?,
//        val fragment: SuccessFragment,
//        var payuHashs: PayuHashes?
//    ) : AsyncTask<HashMap<String, String>, String, PayuHashes>() {
//        val activities = WeakReference<FragmentActivity>(activity)
//        val textView168 = WeakReference<TextView>(textView168)
//        private var progressDialog: ProgressDialog? = null
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            progressDialog = ProgressDialog(activities.get())
//            progressDialog!!.setMessage("Please wait...")
//            progressDialog!!.show()
//        }
//
//        override fun doInBackground(vararg postParams: HashMap<String, String>): PayuHashes? {
//            var payuHashes: PayuHashes? = null
//            try {
//                payuHashes = PayuHashes()
//                val postParam = postParams[0]
//
//                HelperMethods.println(postParam)
//                apiService?.getHashes(postParam)
//                    ?.enqueue(object : ConsAndroidCallback(activities.get(), fragment, object : Interfaces.Callback {
//                        override fun additionalData(display: String?, logout: Boolean) {
//
//                        }
//
//                        override fun failed(t: Throwable) {
//                            payuHashes = null
//                            t.printStackTrace()
//                        }
//
//                        override fun success(response: Response?, data: Data?, state: Boolean) {
//                            if (state) {
//                                if (data != null) {
//                                    payuHashes?.paymentHash = data.paymentHash
//                                    payuHashes?.vasForMobileSdkHash = data.vasForMobileSdkHash
//                                    payuHashes?.paymentRelatedDetailsForMobileSdkHash =
//                                        data.paymentRelatedDetailsForMobileSdkHash
//                                    payuHashes?.deleteCardHash = data.deleteUserCardHash
//                                    payuHashes?.storedCardsHash = data.getUserCardsHash
//                                    payuHashes?.editCardHash = data.editUserCardHash
//                                    payuHashes?.saveCardHash = data.saveUserCardHash
//                                    payuHashes?.checkOfferStatusHash = data.checkOfferStatusHash
//                                } else {
//                                    payuHashes = null
//                                }
//                            } else {
//                                payuHashes = null
//                            }
//                        }
//                    }) {})
//
//            } catch (e: MalformedURLException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: ProtocolException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: IOException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: JSONException) {
//                payuHashes = null
//                e.printStackTrace()
//            }
//
//            return payuHashes
//        }
//
//        override fun onPostExecute(payuHashes: PayuHashes?) {
//            super.onPostExecute(payuHashes)
//
//            HelperMethods.println(payuHashes)
//
//            if (payuHashes != null) {
//                progressDialog?.dismiss()
//                fragment.launchSdkUI(payuHashes)
//            } else {
//                payuHashs = null
//                progressDialog?.dismiss()
//                textView168.get()?.text = activities.get()?.getString(R.string.could_not_receive_data)
//            }
//        }
//    }
//
//    private class GetHashesFromServerTask(
//        activity: FragmentActivity?,
//        textView168: TextView,
//        val payuConfig: PayuConfig?,
//        val mPaymentParams: PaymentParams,
//        val apiService: ApiService?,
//        val fragment: SuccessFragment,
//        button: Button?,
//        var payuHashs: PayuHashes?
//    ) : AsyncTask<String, String, PayuHashes>() {
//        val activities = WeakReference<FragmentActivity>(activity)
//        val textView168 = WeakReference<TextView>(textView168)
//        private var progressDialog: ProgressDialog? = null
//
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            progressDialog = ProgressDialog(activities.get())
//            progressDialog!!.setMessage("Please wait...")
//            progressDialog!!.show()
//        }
//
//        override fun doInBackground(vararg postParams: String): PayuHashes? {
//            var payuHashes: PayuHashes? = null
//            try {
//                payuHashes = PayuHashes()
//                val postParam = postParams[0]
//
//                val url = URL("https://payu.herokuapp.com/get_hash")
//                val postParamsByte = postParam.toByteArray(charset("UTF-8"))
//                val conn = url.openConnection() as HttpURLConnection
//                conn.requestMethod = "POST"
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
//                conn.setRequestProperty("Content-Length", postParamsByte.size.toString())
//                conn.doOutput = true
//
//                conn.outputStream.write(postParamsByte)
//
//                val responseInputStream = conn.inputStream
//                val responseStringBuffer = StringBuffer()
//                val byteContainer = ByteArray(1024)
//
//                var i: Int? = null
//                while ({ i = responseInputStream.read(byteContainer); i }() != -1) {
//                    responseStringBuffer.append(String(byteContainer, 0, i!!))
//                }
//
//                val response = JSONObject(responseStringBuffer.toString())
//
//                val payuHashIterator = response.keys()
//                /*while (payuHashIterator.hasNext()) {
//                    val key = payuHashIterator.next()
//                    when (key) {
//                        "payment_hash" -> payuHashes?.paymentHash = response.getString(key)
//                        "vas_for_mobile_sdk_hash" -> payuHashes?.vasForMobileSdkHash = response.getString(key)
//                        "payment_related_details_for_mobile_sdk_hash" -> payuHashes?.paymentRelatedDetailsForMobileSdkHash = response.getString(key)
//                        "delete_user_card_hash" -> payuHashes?.deleteCardHash = response.getString(key)
//                        "get_user_cards_hash" -> payuHashes?.storedCardsHash = response.getString(key)
//                        "edit_user_card_hash" -> payuHashes?.editCardHash = response.getString(key)
//                        "save_user_card_hash" -> payuHashes?.saveCardHash = response.getString(key)
//                        "check_offer_status_hash" -> payuHashes?.checkOfferStatusHash = response.getString(key)
//                        else -> {
//                        }
//                    }
//                }*/
//                while (payuHashIterator.hasNext()) {
//                    val key = payuHashIterator.next()
//                    when (key) {
//                        //Below three hashes are mandatory for payment flow and needs to be generated at merchant server
//                        /**
//                         * Payment hash is one of the mandatory hashes that needs to be generated from merchant's server side
//                         * Below is formula for generating payment_hash -
//                         *
//                         * sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT)
//                         *
//                         */
//                        "payment_hash" -> payuHashes.paymentHash = response.getString(key)
//
//                        /**
//                         * vas_for_mobile_sdk_hash is one of the mandatory hashes that needs to be generated from merchant's server side
//                         * Below is formula for generating vas_for_mobile_sdk_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be "default"
//                         *
//                         */
//                        "vas_for_mobile_sdk_hash" -> payuHashes.vasForMobileSdkHash = response.getString(key)
//
//                        /**
//                         * payment_related_details_for_mobile_sdk_hash is one of the mandatory hashes that needs to be generated from merchant's server side
//                         * Below is formula for generating payment_related_details_for_mobile_sdk_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
//                         *
//                         */
//                        "payment_related_details_for_mobile_sdk_hash" -> payuHashes.paymentRelatedDetailsForMobileSdkHash = response.getString(key)
//
//                        //Below hashes only needs to be generated if you are using Store card feature
//                        /**
//                         * delete_user_card_hash is used while deleting a stored card.
//                         * Below is formula for generating delete_user_card_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
//                         *
//                         */
//                        "delete_user_card_hash" -> payuHashes.deleteCardHash = response.getString(key)
//
//                        /**
//                         * get_user_cards_hash is used while fetching all the cards corresponding to a user.
//                         * Below is formula for generating get_user_cards_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
//                         *
//                         */
//                        "get_user_cards_hash" -> payuHashes.storedCardsHash = response.getString(key)
//
//                        /**
//                         * edit_user_card_hash is used while editing details of existing stored card.
//                         * Below is formula for generating edit_user_card_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
//                         *
//                         */
//                        "edit_user_card_hash" -> payuHashes.editCardHash = response.getString(key)
//
//                        /**
//                         * save_user_card_hash is used while saving card to the vault
//                         * Below is formula for generating save_user_card_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be user credentials. If you are not using user_credentials then use "default"
//                         *
//                         */
//                        "save_user_card_hash" -> payuHashes.saveCardHash = response.getString(key)
//
//                        //This hash needs to be generated if you are using any offer key
//                        /**
//                         * check_offer_status_hash is used while using check_offer_status api
//                         * Below is formula for generating check_offer_status_hash -
//                         *
//                         * sha512(key|command|var1|salt)
//                         *
//                         * here, var1 will be Offer Key.
//                         *
//                         */
//                        "check_offer_status_hash" -> payuHashes.checkOfferStatusHash = response.getString(key)
//                        else -> {
//                        }
//                    }
//                }
//
//
//            } catch (e: MalformedURLException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: ProtocolException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: IOException) {
//                payuHashes = null
//                e.printStackTrace()
//            } catch (e: JSONException) {
//                payuHashes = null
//                e.printStackTrace()
//            }
//
//            return payuHashes
//        }
//
//        override fun onPostExecute(payuHashes: PayuHashes?) {
//            super.onPostExecute(payuHashes)
//
//            HelperMethods.println(payuHashes)
//
//            if (payuHashes != null) {
//                progressDialog?.dismiss()
//                fragment.launchSdkUI(payuHashes)
//            } else {
//                payuHashs = null
//                progressDialog?.dismiss()
//                textView168.get()?.text = activities.get()?.getString(R.string.could_not_receive_data)
//            }
//
//        }
//    }
//
//    private fun generateHash(mPaymentParams: PaymentParams?, apiService: ApiService?) {
//        if (mPaymentParams != null) {
//            val postParamsBuffer = StringBuffer()
//            postParamsBuffer.append(concatParams(PayuConstants.KEY, mPaymentParams.key))
//            postParamsBuffer.append(concatParams(PayuConstants.AMOUNT, mPaymentParams.amount))
//            postParamsBuffer.append(concatParams(PayuConstants.TXNID, mPaymentParams.txnId))
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.EMAIL,
//                    if (null == mPaymentParams.email) "" else mPaymentParams.email
//                )
//            )
//            postParamsBuffer.append(concatParams(PayuConstants.PRODUCT_INFO, mPaymentParams.productInfo))
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.FIRST_NAME,
//                    if (null == mPaymentParams.firstName) "" else mPaymentParams.firstName
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.UDF1,
//                    if (mPaymentParams.udf1 == null) "" else mPaymentParams.udf1
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.UDF2,
//                    if (mPaymentParams.udf2 == null) "" else mPaymentParams.udf2
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.UDF3,
//                    if (mPaymentParams.udf3 == null) "" else mPaymentParams.udf3
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.UDF4,
//                    if (mPaymentParams.udf4 == null) "" else mPaymentParams.udf4
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.UDF5,
//                    if (mPaymentParams.udf5 == null) "" else mPaymentParams.udf5
//                )
//            )
//            postParamsBuffer.append(
//                concatParams(
//                    PayuConstants.USER_CREDENTIALS,
//                    if (mPaymentParams.userCredentials == null) PayuConstants.DEFAULT else mPaymentParams.userCredentials
//                )
//            )
//
//            // for offer_key
//            if (null != mPaymentParams.offerKey)
//                postParamsBuffer.append(concatParams(PayuConstants.OFFER_KEY, mPaymentParams.offerKey))
//
//            val postParams = if (postParamsBuffer[postParamsBuffer.length - 1] == '&') postParamsBuffer.substring(
//                0,
//                postParamsBuffer.length - 1
//            ).toString() else postParamsBuffer.toString()
//
//            // lets make an api call
//            val getHashesFromServerTask = GetHashesFromServerTask(
//                requireActivity(),
//                textView168,
//                payuConfig,
//                mPaymentParams,
//                apiService,
//                this,
//                null,
//                payuHashs
//            )
//            getHashesFromServerTask.execute(postParams)
//
//        } else {
//            activity?.let { HelperMethods.toastit(it, "Error") }
//        }
//    }
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.frgament_moneys, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//
//    companion object {
//        protected fun concatParams(key: String, value: String): String {
//            return "$key=$value&"
//        }
//
//    }
//}

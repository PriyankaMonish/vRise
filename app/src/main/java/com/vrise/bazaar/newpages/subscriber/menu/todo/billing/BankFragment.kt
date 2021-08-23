package com.vrise.bazaar.newpages.subscriber.menu.todo.billing

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.retrofit.PayEnvironment
import com.vrise.bazaar.ex.shop.interfaces.OutViewModel
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.newpages.retrofit.ApiService
import com.vrise.bazaar.newpages.retrofit.Orderdata

import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.ID
import com.vrise.bazaar.newpages.utilities.Constants.Upi
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.payu.india.Payu.PayuConstants
import kotlinx.android.synthetic.main.fragment_bank.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*


class BankFragment : Fragment() {
    private var environment = PayEnvironment.STAGING
    private val TEZ_REQUEST_CODE = 123
    private val GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
    var amount = 0.0
    //    var apiService: ApiService? = null
    private val UPI_PAYMENT = 122
    lateinit var viewModel: OutViewModel
    private var paymentType = ""
    private var apiService: ApiService? = null
    companion object {
        val item = Orderdata()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bank, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControll()

    }
    private fun initControll() {
        apiService = (activity?.application as BaseApp).oldService()

        paymentType = arguments?.getString("payment_type", "").toString()


        arguments?.let {
            Upi = it.getString(Constants.Upi, "")
            amount = it.getString(PayuConstants.AMOUNT, "").toDouble()
            editText39.setText(Upi)
            editText39.isEnabled = false
            editText39.isFocusable = false
        }


        radioButton2.setOnClickListener {
            textInputLayout7.visibility = View.VISIBLE
        }
        radioButton.setOnClickListener {
            textInputLayout7.visibility = View.GONE
        }
        Pay.setOnClickListener {
            when (id) {
                R.id.radioButton -> initializeGPay()

                else -> initializeUpi()
            }

        }
            Instances.toast(activity, getString(R.string.could_not_receive_data))
            activity?.onBackPressed()
        }






    private fun initializeUpi() {
        if (environment == PayEnvironment.LIVE) {
            val uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", Upi) //"test@axisbank"
                .appendQueryParameter(
                    "tr",
                    (Random().nextInt() + System.currentTimeMillis()).toString()
                )
                .appendQueryParameter("pn", "IBR ADVERTISERS") //Test Merchant
                .appendQueryParameter("tn", "product_info") //test transaction note
                .appendQueryParameter("am", amount.toString())
                .appendQueryParameter("cu", "INR")
                .build()
            val upiPayIntent = Intent(Intent.ACTION_VIEW)
            upiPayIntent.data = uri
            val chooser = Intent.createChooser(upiPayIntent, "Pay with")
            if (null != chooser.resolveActivity(requireActivity().packageManager)) {
                startActivityForResult(chooser, UPI_PAYMENT)
            } else {
                Instances.toast(
                    requireActivity(),
                    "No UPI app found, please install one to continue"
                )
            }
        }
    }

    private fun initializeGPay() {
        if (environment == PayEnvironment.LIVE) {

            try {
                val uri = Uri.Builder()
                    .scheme("upi")
                    .authority("pay")
                    .appendQueryParameter("pa", Upi) //msmonish4u@okicici Payee VPA  "test@axisbank"
                    // if the account number is 123 and IFSC code is ABC456, then try sending UPI id as 123@ABC456.ifsc.npci
                    .appendQueryParameter("pn", "IBR ADVERTISERS") //Payee name ""
                    /*.appendQueryParameter("mc", "1234") // "Payee merchant code"*/
                    .appendQueryParameter(
                        "tr",
                        (Random().nextInt() + System.currentTimeMillis()).toString()
                    ) //Transaction reference ID *
                    .appendQueryParameter("tn", "product_info") //Transaction note
                    .appendQueryParameter("am", amount.toString()
                    )//Transaction amount in decimal format.
                    .appendQueryParameter("cu", "INR") //Currency code
                    /*.appendQueryParameter("url", "https://ibrbazaar.com/test")*/ //https://test.merchant.website
                    .build()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME)
                startActivityForResult(intent, TEZ_REQUEST_CODE)
            } catch (e: ActivityNotFoundException) {
                Instances.toast(
                    requireActivity(),
                    "Install Google Pay(Tez) app and login to your account before using this payment option."
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

     override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            UPI_PAYMENT -> if (RESULT_OK === resultCode || resultCode == 11) {
                if (data != null) {
                    val trxt = data.getStringExtra("response")
                    Log.d("UPI", "onActivityResult: $trxt")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add(trxt!!)
                    upiPaymentDataOperation(dataList, data.getStringExtra("txnId"))
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add("nothing")
                    upiPaymentDataOperation(dataList,data?.getStringExtra("txnId"))
                }
            } else {
                Log.d("UPI", "onActivityResult: " + "Return data is null") //when user simply back without payment
                val dataList: ArrayList<String> = ArrayList()
                dataList.add("nothing")
                upiPaymentDataOperation(dataList,data?.getStringExtra("txnId"))
            }
        }
//
//          if (requestCode == TEZ_REQUEST_CODE) {
//
//             toast(requireActivity(), data?.getStringExtra("Status"))
//
//              var request : Request ? = null
//            if (data?.getStringExtra("Status") == "SUCCESS") {
//                 "paid"
//             } else {
//                 "pending"
//             }
//             request?.txnid = data?.getStringExtra("txnId")
//             request?.online_data = data?.toString()
//              item.upi_transactionid = data?.getStringExtra("txnId")
//              item.payment_method = "upi"
//              val json = Gson().toJson(item)
//              apiService?.billPayment(
//                     Request(
//                         utoken = Preference.getSinglePreference(context!!, DATAFILE, ID),
//                         payment = amount.toString(),
//                         paymentData = json
//                     )
//
//                 )?.enqueue(object : Callback<Response> {
//
//                     override fun onResponse(
//                         call: Call<Response>?,
//                         response: retrofit2.Response<Response>?
//                     ) {
//                         Toast.makeText(activity, "Transaction successful.", Toast.LENGTH_SHORT)
//                             .show()
//                         HelperMethods.navigateTo(
//                             activity!!,
//                             Constants.FRAGMENT,
//                             false,
//                             Dashboard(),
//                             null,
//                             null,
//                             null,
//                             ""
//                         )
//
////                         Log.d("UPI", "responseStr: $approvalRefNo")
//                     }
//
//                     override fun onFailure(call: Call<Response>, t: Throwable) {
//                     }
//
//
//                 })
//
//                 }
             }




    private fun upiPaymentDataOperation(data: ArrayList<String>,txnId: String?) {
        if (isConnectionAvailable(requireActivity())) {
            var str = data[0]
            Log.d("UPIPAY", "upiPaymentDataOperation: $str")
            var paymentCancel = ""
            if (str == null) str = "discard"
            var status = ""
            var approvalRefNo = ""
            val response = str.split("&").toTypedArray()
            for (i in response.indices) {
                val equalStr =
                    response[i].split("=").toTypedArray()
                if (equalStr.size >= 2) {
                    if (equalStr[0].toLowerCase() == "Status".toLowerCase()) {
                        status = equalStr[1].toLowerCase()
                    }
        else if (equalStr[0].toLowerCase() == "ApprovalRefNo".toLowerCase() || equalStr[0].toLowerCase() == "txnRef".toLowerCase()) {
                        approvalRefNo = equalStr[1]
                    }
                } else {
                    paymentCancel = "Payment cancelled by user."
                }
            }
//            if (status == "success") {
//
//                item.upi_transactionid = txnId
//                item.payment_method = "upi"
//                val json = Gson().toJson(item)
//
//                apiService?.billPayment(
//                    Request(
//                        utoken = Preference.getSinglePreference(context!!,DATAFILE, ID),
//                        payment = amount.toString(),
//                        paymentData = json)
//
//                )?.enqueue(object : Callback<Response> {
//
//                    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                        Toast.makeText(activity, "Transaction successful.", Toast.LENGTH_SHORT)
//                            .show()
//                        HelperMethods.navigateTo(
//                            activity!!,
//                            Constants.FRAGMENT,
//                            false,
//                            Dashboard(),
//                            null,
//                            null,
//                            null,
//                            ""
//                        )                   }
//
//                    override fun onFailure(call: Call<Response>, t: Throwable) {
//                    }
//
//
//                })
//            }

//            else
                if ("Payment cancelled by user." == paymentCancel) {
                Toast.makeText(activity, "Payment cancelled by user.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    activity,
                    "Transaction failed.Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                activity,
                "Internet connection is not available. Please check and try again",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val netInfo: NetworkInfo = connectivityManager.activeNetworkInfo!!
            if (netInfo != null && netInfo.isConnected
                && netInfo.isConnectedOrConnecting
                && netInfo.isAvailable
            ) {
                return true
            }
        }
        return false
    }


}






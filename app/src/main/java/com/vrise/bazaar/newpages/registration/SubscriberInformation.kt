package com.vrise.bazaar.newpages.registration

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.google.firebase.iid.FirebaseInstanceId
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.*
//import com.vrise.bazaar.newpages.utilities.Constants.AGENT_ID
//import com.vrise.bazaar.newpages.utilities.Constants.FRAGMENT
//
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
//import com.vrise.bazaar.newpages.utilities.HelperMethods.hasData
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.HelperMethods.openPopUp
//import com.vrise.bazaar.newpages.utilities.HelperMethods.toastit
//import com.vrise.bazaar.newpages.utilities.Validator.hasText
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
//import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
//import kotlinx.android.synthetic.main.fragment_subscriber_information.*
//import kotlinx.android.synthetic.main.item_custom_toolbar.*
//import retrofit2.Call
//import retrofit2.Callback

//class SubscriberInformation : InitSub() {
//
//    override fun networkAvailable() {
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    var agentId = ""
//    lateinit var stateList: ArrayList<Request>
//    lateinit var districtList: ArrayList<Request>
//    var stateId: String = ""
//    var districtId: String = ""
//    override fun initView() {
//
//    }
//
//    override fun initModel() {
//
//    }
//
//    var device: String? = null
//    override fun initControl() {
//        activity!!.page_title.text = "Subscriber Information"
//        if (hasConnection(activity)) {
//            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                device = instanceIdResult.token
//                HelperMethods.println("newToken1 $device ")
//            }
//        }
//        getBundleData()
//        getStates()
//
//        textView131.setText(com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity, Constants.DATAFILE, Constants.REGISTRATION).toString())
//        textView135.setOnClickListener {
//            if (hasConnection(activity!!)) {
//                progressBar6.visibility = View.VISIBLE
//                if (
//                        agentId.isNotBlank() &&
//                        hasText(arrayOf(
//                                Validator.Main(textView130, Validator.isText),
//                                Validator.Main(textView131, Validator.isText),
//                                Validator.Main(textView131, Validator.isPhone),
//                                Validator.Main(textView132, Validator.isText),
//                                Validator.Main(textView132, Validator.isEmail),
//                                Validator.Main(textView133, Validator.isText),
//                                Validator.Main(textView136, Validator.isText),
//                                Validator.Main(textView139, Validator.isText),
//                                Validator.Main(textView134, Validator.isText)
//                        )) &&
//                        hasText(textView137) &&
//                        stateId.isNotBlank() &&
//                        hasText(textView138) &&
//                        districtId.isNotBlank()
//                ) {
//                    if (device.isNullOrBlank()) {
//                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                            device = instanceIdResult.token
//                            HelperMethods.println("newToken2 $device ")
//                        }
//                    } else if (device == "0") {
//                        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
//                            device = instanceIdResult.token
//                            HelperMethods.println("newToken3 $device ")
//                        }
//                    }
//
//                    println(Request(
//                            mobile = textView131.text.toString(),
//                            device_id = device,
//                            user_type = "1",
//                            email = textView132.text.toString(),
//                            name = textView130.text.toString(),
//                            code = textView124.text.toString(),
//                            agent_id = agentId,
//                            address = textView133.text.toString(),
//                            address2 = textView136.text.toString(),
//                            state = stateId,
//                            district = districtId,
//                            profile_pic = "",
//                            pincode = textView134.text.toString(),
//                            postOffice = textView139.text.toString()
//                    ))
//                    apiService?.signUp(Request(
//                            mobile = textView131.text.toString(),
//                            device_id = device,
//                            user_type = "1",
//                            email = textView132.text.toString(),
//                            name = textView130.text.toString(),
//                            code = textView124.text.toString(),
//                            agent_id = agentId,
//                            address = textView133.text.toString(),
//                            address2 = textView136.text.toString(),
//                            state = stateId,
//                            district = districtId,
//                            profile_pic = "",
//                            pincode = textView134.text.toString(),
//                            postOffice = textView139.text.toString()
//                    ))?.enqueue(object : Callback<Response> {
//                        override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                            if (activity != null && isAdded) {
//                                progressBar6.visibility = View.GONE
//                                t!!.printStackTrace()
//                            }
//                        }
//
//                        override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                            if (activity != null && isAdded) {
//                                if (hasData(activity!!, response)) {
//                                    progressBar6.visibility = View.GONE
//                                    navigateTo(activity!!, FRAGMENT, true, ContactAgentPopUp(), null, null, null, "")
//                                } else {
//                                    progressBar6.visibility = View.GONE
//                                }
//                            }
//                        }
//                    })
//                } else {
//                    textView137.error = if (stateId.isBlank()) {
//                        "Select State"
//                    } else {
//                        null
//                    }
//                    textView138.error = if (districtId.isBlank()) {
//                        "Select District"
//                    } else {
//                        null
//                    }
//                    progressBar6.visibility = View.GONE
//                }
//            }
//        }
//        textView138.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                textView138.error = null
//                if (stateId.isBlank()) {
//                    toastit(activity!!, "Select a state")
//                } else {
//                    if (hasConnection(activity!!)) {
//                        apiService?.getDistricts(Request(state = stateId))?.enqueue(object :Callback<Response> {
//                            override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                                if (activity != null && isAdded) {
//                                    t!!.printStackTrace()
//                                }
//                            }
//
//                            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                                if (activity != null && isAdded) {
//                                    if (hasData(activity!!, response)) {
//                                        if (response!!.body()!!.data != null) {
//                                            if (response.body()!!.data!!.districts != null) {
//                                                if (response.body()!!.data!!.districts!!.isNotEmpty()) {
//                                                    districtList = ArrayList()
//                                                    for (i in 0 until response.body()!!.data!!.districts!!.size) {
//                                                        districtList.add(Request(
//                                                                name = response.body()!!.data!!.districts!![i]!!.name,
//                                                                code = response.body()!!.data!!.districts!![i]!!.id
//                                                        ))
//                                                    }
//                                                    if (districtList.isNotEmpty()) {
//                                                        openPopUp(activity!!, districtList, textView138, "Districts", object : Interfaces.ReturnId {
//                                                            override fun returnId(string: String) {
//                                                                districtId = string
//                                                            }
//                                                        })
//                                                    } else {
//                                                        HelperMethods.toastit(activity!!, "No districts available")
//                                                    }
//                                                } else {
//                                                    HelperMethods.toastit(activity!!, "No districts available.")
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        })
//                    } else {
//                    }
//                }
//            }
//        })
//        textView137.setOnClickListener(object : ClickListener() {
//            override fun onOneClick(v: View) {
//                textView137.error = null
//                openPopUp(activity!!, stateList, textView137, "States", object : Interfaces.ReturnId {
//                    override fun returnId(string: String) {
//                        stateId = string
//                        textView138.text = ""
//                        districtId = ""
//                    }
//                })
//            }
//        })
//    }
//
//    private fun getStates() {
//        if (hasConnection(activity!!)) {
//            apiService?.getStates()?.enqueue(object : Callback<Response> {
//                override fun onFailure(call: Call<Response>?, t: Throwable?) {
//                    if (activity != null && isAdded) {
//                        t!!.printStackTrace()
//                    }
//                }
//
//                override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
//                    if (activity != null && isAdded) {
//                        if (hasData(activity!!, response)) {
//                            if (response!!.body()!!.data != null) {
//                                if (response.body()!!.data!!.states != null) {
//                                    if (response.body()!!.data!!.states!!.isNotEmpty()) {
//                                        stateList = ArrayList()
//                                        for (i in 0 until response.body()!!.data!!.states!!.size) {
//                                            stateList.add(Request(
//                                                    name = response.body()!!.data!!.states!![i]!!.state,
//                                                    code = response.body()!!.data!!.states!![i]!!.id
//                                            ))
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            })
//        }
//    }
//
//    private fun getBundleData() {
//        val bundle = arguments
//        if (bundle != null) {
//            agentId = bundle.getString(AGENT_ID, "")
//        }
//    }
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_subscriber_information, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setInitializer()
//    }
//}

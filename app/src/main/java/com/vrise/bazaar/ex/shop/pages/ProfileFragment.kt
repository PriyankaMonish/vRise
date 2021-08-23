package com.vrise.bazaar.ex.shop.pages

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.DistrictsItem
import com.vrise.bazaar.ex.model.submodels.StatesItem
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.ProfileViewModel
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Instances.hideKeyboard
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.NUMBER
import com.vrise.bazaar.ex.util.Preference.TOKEN
import com.vrise.bazaar.newpages.utilities.HelperMethods.toast
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.fragment_profile_new.*
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.district
import kotlinx.android.synthetic.main.profile_fragment.mobile
import kotlinx.android.synthetic.main.profile_fragment.state
import java.io.*


class ProfileFragment : androidx.fragment.app.Fragment() {
    companion object {
        var stateId = ""
        var districtId = ""
        var imageVlaue = ""
        var stateList: ArrayList<StatesItem?>? = null
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        include6.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(ProfileViewModel::class.java)
        view27.visibility = View.VISIBLE
        imageView81.visibility = View.GONE
        imageView80.visibility = View.GONE
      profiles_name.clearFocus()

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getStates()?.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        stateList = it
                    }
                })
            } else {
                Instances.toast(activity, getString(R.string.no_internet))
            }
        }

        getProfile()

        mobiles.setText(Preference.get(activity, DATAFILE, NUMBER))

        states.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
           hideKeyboard(activity)
                Instances.openListPoPUp(activity,
                    stateList as ArrayList<Any?>,
                    null,
                    "Select your state",
                    object : Interfaces.ReturnAny {
                        override fun getValue(values: Any?) {
                            if (values is StatesItem) {
                                districts.setText("")
                                districtId = ""
                                states.setText(values.state.toString())
                                stateId = values.id.toString()
                            }
                        }
                    })
            }
        })

        districts.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
          hideKeyboard(activity)
                if (stateId.isNotBlank()) {
                    viewModel.getDistricts(Request(state = stateId))
                        ?.observe(viewLifecycleOwner, Observer {
                            if (it != null) {
                                if (it.size == 1) {
                                    districts.setText(it[0]?.name.toString())
                                    districtId = it[0]?.id.toString()
                                } else {
                                    Instances.openListPoPUp(activity,
                                        it as ArrayList<Any?>,
                                        null,
                                        "Select your district",
                                        object : Interfaces.ReturnAny {
                                            override fun getValue(values: Any?) {
                                                if (values is DistrictsItem) {
                                                    districts.setText(values.name.toString())
                                                    districtId = values.id.toString()
                                                }
                                            }
                                        })
                                }
                            }
                        })
                } else {
                    Instances.toast(activity, "Please select a state")
                }
            }
        })
        button30.setOnClickListener {
            button30.visibility = View.GONE
            hideKeyboard(activity)
            if (view27.visibility == View.VISIBLE) {
                view27.visibility = View.GONE
                profiles_name.requestFocus()
                imageView81.visibility = View.VISIBLE
                imageView80.visibility = View.VISIBLE
            } else {
                view27.visibility = View.VISIBLE
                profiles_name.clearFocus()
                imageView81.visibility = View.GONE
                imageView80.visibility = View.GONE
                
            }
        }
        imageView81.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {

                include6.visibility = View.VISIBLE
                Instances.InternetCheck { internet ->
                    if (internet) {
                        if (validate(
                                arrayOf(
                                    profiles_name,
                                    housde_name,
                                    areas_locality,
                                    e_mails,
                                    pins
                                )
                            )
                        ) {
                            if (!stateId?.isNullOrBlank() && !districtId?.isNullOrBlank()) {
                                include6.visibility = View.VISIBLE
                                viewModel.profileUpdate(
                                    Request(
                                        utoken = Preference.get(activity, DATAFILE, TOKEN),
                                        name = profiles_name.text.toString(),
                                        email = e_mails.text.toString(),
                                        mobile = mobiles.text.toString(),
                                        address = housde_name.text.toString(),
                                        address2 = areas_locality.text.toString(),
                                        pincode = pins.text.toString(),
//                                        postOffice = post.text.toString(),
                                        state = stateId,
                                        district = districtId
                                    )
                                )?.observe(viewLifecycleOwner, Observer {
                                    include6.visibility = View.GONE
                                    try {
                                        pins.clearFocus()
                                        profiles_name.clearFocus()
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                    view27.visibility = View.VISIBLE
                                    imageView81.visibility = View.GONE
                                    imageView80.visibility = View.GONE
                                    profiles_name.clearFocus()
                                    toast(activity, "Successfully updated Profile")

                                    getProfile()

                                    button30.visibility = View.VISIBLE
                                })
                            } else {
                                include6.visibility = View.GONE
                                Instances.toast(activity, "please select a state and district")
                            }
                        } else {
                            Toast.makeText(activity, "Please Fill Mandatory Field", Toast.LENGTH_SHORT).show()
                            include6.visibility = View.GONE
                        }
                    } else {
                        Instances.toast(activity, getString(R.string.no_internet))
                        include6?.visibility = View.GONE
                    }
                }
            }
        })

        imageView80.setOnClickListener(object  :ClickListener(){
            override fun onOneClick(v: View) {
                findNavController().navigate(
                    R.id.action_navigation_profile_to_homeFragment2)

            }

        })
    }


    private fun getProfile() {
        include6.visibility = View.VISIBLE

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getProfile(Request(utoken = Preference.get(activity, DATAFILE, TOKEN)))?.observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            Preference.put(activity, Preference.NAME, it.name.toString(), DATAFILE)
                            Preference.put(
                                activity,
                                Preference.IMAGE,
                                it.imgLink.toString(),
                                DATAFILE
                            )
                            profiles_name.setText(it.name.toString())
                            housde_name.setText(it.address.toString())
                            areas_locality.setText(it.address2.toString())
                            states.setText(it.stateName.toString())
                            e_mails.setText(it.email.toString())
                            districts.setText(it.district_name.toString())
                            pins.setText(it.pincode.toString())
                            stateId = it.state.toString()
                            districtId = it.district.toString()
                        }

                    include6.visibility = View.GONE
                    })
            } else {
                Instances.toast(activity, getString(R.string.no_internet))
                include6.visibility = View.GONE
            }
        }
        hideKeyboard(activity)
    }

    private fun validate(elements: Array<EditText>): Boolean {
        val boolean: ArrayList<Boolean> = ArrayList()
        for (i in 0 until elements.size) {
            boolean.add(hasText(elements[i]))

        }
        return !boolean.contains(false)
    }

    private fun hasText(editText: EditText?): Boolean {
        if (editText != null) {
            val text = editText.text.toString().trim { it <= ' ' }
            editText.error = null
            if (text.isEmpty()) {
                editText.error = "Mandatory Field"
                return false
            }
        } else {
            return false
        }
        return true
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

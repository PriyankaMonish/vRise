package com.vrise.bazaar.ex.shop.pages.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.PlaceContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.PageViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.showBlur
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.IMAGE
import com.vrise.bazaar.ex.util.Preference.LANGUA
import com.vrise.bazaar.ex.util.Preference.NAME
import com.vrise.bazaar.ex.util.Preference.NUMBER
import com.vrise.bazaar.ex.util.Preference.TOKEN

import com.vrise.bazaar.ex.util.Values.shareLink
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.mobile_edit_number.*
import kotlinx.android.synthetic.main.more_fragment.*

class MoreFragment : Fragment() {
    private lateinit var viewModel: PageViewModel
    private var customerNumbers: List<String?>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(PageViewModel::class.java)

        textView227.text = "Change Mobile Number (${Preference.get(activity, DATAFILE, NUMBER)})"
        showBlur(activity, Preference.get(activity, DATAFILE, IMAGE).toString(), imageView32)
        textView118.text = Preference.get(activity, DATAFILE, NAME).toString()

        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getCustCare(Request(utoken = Preference.get(activity, DATAFILE, TOKEN)))
                    ?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                        if (!it.isNullOrEmpty()) {
                            customerNumbers = it
                        }
                    })

            } else {
                toast(activity, getString(R.string.no_internet))
            }
        }

        textView113.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (customerNumbers != null) {
                    Instances.openListPoPUp(activity,
                        customerNumbers?.filterNotNull() as java.util.ArrayList<Any?>,
                        null,
                        null,
                        object : Interfaces.ReturnAny {
                            override fun getValue(values: Any?) {
                                try {
                                    startActivity(
                                        Intent(
                                            ACTION_DIAL, Uri.parse("tel:" + values.toString())
                                        )
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        })
                }
            }
        })

        textView111.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                if (activity is DashBoardContainer) {
                    v.findNavController().navigate(R.id.action_navigation_more_to_navigation_profile)
                } else if (activity is BazaarContainer) {
                    v.findNavController().navigate(R.id.action_navigation_more_to_navigation_profile2)
                }
            }
        })

        textView112.setOnClickListener {
            Instances.openListPoPUp(activity, object : ArrayList<Any?>() {
                init {
                    add("English")
                    add("malayalam")
                }
            }, textView112, "Change Language", object : Interfaces.ReturnAny {
                override fun getValue(key: Any?) {
                    Preference.put(activity, LANGUA, key.toString(), DATAFILE)
                    textView112.text = "Change Language" + "(${key.toString()})"
                }
            })
        }

        textView114.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                try {
                    startActivity(
                        Intent(
                            ACTION_VIEW, Uri.parse("market://details?id=" + activity?.packageName)
                        ).addFlags(
                            FLAG_ACTIVITY_NO_HISTORY or FLAG_ACTIVITY_NEW_DOCUMENT or FLAG_ACTIVITY_MULTIPLE_TASK
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("https://play.google.com/store/apps/details?id=${activity?.packageName}")
                        )
                    )
                }
            }
        })

        textView115.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                startActivity(Intent(activity, PlaceContainer::class.java))
            }
        })

        textView210.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                val shareIntent = Intent(ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(EXTRA_SUBJECT, "IBR BAZAAR")
                val shareMessage = "$shareLink"
                shareIntent.putExtra(EXTRA_TEXT, shareMessage)
                startActivity(createChooser(shareIntent, "Share Viaronline using"))
            }
        })

        textView227.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                val dialog = BottomSheetDialog(activity!!)
                dialog.setCancelable(false)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.mobile_edit_number)
                dialog.textView230.text = Preference.get(activity, DATAFILE, NUMBER)
                dialog.progress.visibility = View.GONE
                dialog.textInputLayout20.visibility = View.GONE
                dialog.button20.setOnClickListener(object : ClickListener() {
                    override fun onOneClick(v: View) {
                        if (Instances.isPhone(
                                activity,
                                dialog.textInputEditText13.text.toString()
                            )
                        ) {
                            Instances.InternetCheck { internet ->
                                if (internet) {
                                    dialog.progressBar30.visibility = View.VISIBLE
                                    viewModel.send(Request(mobile = dialog.textInputEditText13.text.toString()))
                                        ?.observe(
                                            viewLifecycleOwnerLiveData.value ?: this@MoreFragment,
                                            Observer {
                                                if (it != null) {
                                                    dialog.button20.visibility = View.GONE
                                                    dialog.progressBar30.visibility = View.GONE
                                                    dialog.textInputLayout20.visibility =
                                                        View.VISIBLE

                                                    dialog.textInputEditText14.addTextChangedListener(
                                                        object : TextWatcher {
                                                            override fun afterTextChanged(s: Editable?) {

                                                            }

                                                            override fun beforeTextChanged(
                                                                s: CharSequence?,
                                                                start: Int,
                                                                count: Int,
                                                                after: Int
                                                            ) {

                                                            }

                                                            override fun onTextChanged(
                                                                s: CharSequence?,
                                                                start: Int,
                                                                before: Int,
                                                                count: Int
                                                            ) {
                                                                if (s.toString().length > 3) {
                                                                    if (s.toString() != it.data?.otp.toString()) {
                                                                        toast(
                                                                            activity,
                                                                            "Otp mismatch"
                                                                        )
                                                                    } else {
                                                                        Handler().postDelayed({
                                                                            dialog.progress.visibility =
                                                                                View.VISIBLE
                                                                            Instances.InternetCheck { internet ->
                                                                                if (internet) {
                                                                                    dialog.textInputEditText13.isEnabled =
                                                                                        false
                                                                                    dialog.textInputEditText13.isFocusable =
                                                                                        false
                                                                                    viewModel.updateNo(
                                                                                        Request(
                                                                                            utoken = Preference.get(
                                                                                                activity,
                                                                                                DATAFILE,
                                                                                                TOKEN
                                                                                            ),
                                                                                            mobile = dialog.textInputEditText13.text.toString()
                                                                                        )
                                                                                    )?.observe(
                                                                                        viewLifecycleOwnerLiveData.value
                                                                                            ?: this@MoreFragment,
                                                                                        Observer {
                                                                                            if (it != null) {
                                                                                                Instances.logoutFromApp(
                                                                                                    "Use new phone number to login",
                                                                                                    true,
                                                                                                    activity
                                                                                                )
                                                                                            } else {
                                                                                                toast(
                                                                                                    activity,
                                                                                                    "Error"
                                                                                                )
                                                                                            }
                                                                                            dialog.dismiss()
                                                                                            dialog.progress.visibility =
                                                                                                View.GONE
                                                                                        })
                                                                                } else {
                                                                                    dialog.textInputEditText13.isEnabled =
                                                                                        true
                                                                                    dialog.textInputEditText13.isFocusable =
                                                                                        true
                                                                                    dialog.progress.visibility =
                                                                                        View.GONE
                                                                                    toast(
                                                                                        activity,
                                                                                        getString(R.string.no_internet)
                                                                                    )
                                                                                }
                                                                            }
                                                                        }, 1000)
                                                                    }
                                                                }
                                                            }
                                                        })

                                                } else {
                                                    dialog.button20.visibility = View.VISIBLE
                                                    dialog.progressBar30?.visibility = View.GONE
                                                    dialog.textInputLayout20.visibility = View.GONE
                                                    toast(activity, getString(R.string.no_server))
                                                }
                                            })
                                } else {
                                    toast(activity, getString(R.string.no_internet))
                                }
                            }
                        }
                    }
                })
                dialog.imageView46.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.textView230.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        })

        progress.visibility = View.GONE
    }

}

@file:Suppress("DEPRECATION")

package com.vrise.bazaar.ex.shop.containers


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.temp.AddressItem
import com.vrise.bazaar.ex.retrofit.RetroService
import com.vrise.bazaar.ex.shop.pages.shop.*
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference.CARTCOUNT
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.NOTIFYCOUNT
import com.vrise.bazaar.newpages.containers.NotificationContainer
import com.vrise.bazaar.newpages.utilities.NetworkStateReceiver
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_new.imgsMenu
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*


class DashBoardContainer : AppCompatActivity(), NetworkStateReceiver.NetworkStateReceiverListener   {
    private var customerNumbers: List<String?>? = null
    override fun networkAvailable() {

    }

    override fun networkUnavailable() {
        toast(this, getString(R.string.no_internet))
    }

    var apiService: RetroService? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_new)
        Instances.hideKeyboard(this)
        apiService = (this.application as BaseApp).apiService()

        navigation_new.setupWithNavController(findNavController(R.id.nav_host_fragment))
        nav_view.getHeaderView(0).txt_nav_close.setOnClickListener {
            drawer_layout.closeDrawer(Gravity.LEFT)
        }
        imageView3.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                val intent = Intent(this@DashBoardContainer, NotificationContainer::class.java)
                val bundle = Bundle()
                bundle.putString(Constants.ID, 4.toString())
                bundle.putString(com.vrise.bazaar.newpages.utilities.Constants.APP_ID, "ibrbazaar")
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(destinationChange)

       val drawerLayout = DrawerLayout(this)
        val navView:NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

       val appBarConfiguration = AppBarConfiguration(
           setOf(
               R.id.nav_home
           ), drawerLayout
       )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        (navView.menu.findItem(R.id.logout)?.setOnMenuItemClickListener {

            Instances.logOut(this)
            true
        })
        (navView.menu.findItem(R.id.share_vrise_app)?.setOnMenuItemClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VRise BAZAAR")
            val shareMessage = "${Values.shareLink}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share Viaronlineusing"))
            true
        })
        (navView.menu.findItem(R.id.shop_category)?.setOnMenuItemClickListener {
                if (BaseApp.enableGlobal == 1){

                    findNavController(R.id.nav_host_fragment).navigate( R.id.sideBarCategory
                    )
//                    val fm= supportFragmentManager
//                    val ft = fm.beginTransaction()
//                    ft.replace(R.id.nav_host_fragment, SideBarCategory()).addToBackStack(null)
//                    ft.commit()
                    drawer_layout.closeDrawer(Gravity.LEFT)
                    } else {
                    drawer_layout.closeDrawer(Gravity.LEFT)
                }

            true
        })
        (navView.menu.findItem(R.id.update_profile)?.setOnMenuItemClickListener {
//            val fm= supportFragmentManager
//            val ft = fm.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, ProfileFragment()).addToBackStack(null)
//            ft.commit()

            findNavController(R.id.nav_host_fragment).navigate( R.id.navigation_profile
            )
            drawer_layout.closeDrawer(Gravity.LEFT)
            true
        })

        (navView.menu.findItem(R.id.change_delivery_address)?.setOnMenuItemClickListener {
//            val fm= supportFragmentManager
//            val ft = fm.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, AddressListFragment()).addToBackStack(null)
//            ft.commit()

            findNavController(R.id.nav_host_fragment).navigate(
                R.id.addressListFragment,
            )
            drawer_layout.closeDrawer(Gravity.LEFT)
            true
        })
        (navView.menu.findItem(R.id.call_customer_care)?.setOnMenuItemClickListener {
            val mobileNumber = "0000000000"
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL // Action for what intent called for
            intent.data = Uri.parse("tel: $mobileNumber") // Data with intent respective action on intent
            startActivity(intent)
            drawer_layout.closeDrawer(Gravity.LEFT)
            true
        })
        (navView.menu.findItem(R.id.terms_and_condition)?.setOnMenuItemClickListener {

            findNavController(R.id.nav_host_fragment).navigate(
                R.id.termsFragment,
            )
//            val fm= supportFragmentManager
//            val ft = fm.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, TermsFragment()).addToBackStack(null)
//            ft.commit()
            drawer_layout.closeDrawer(Gravity.LEFT)
            true
        })

        (navView.menu.findItem(R.id.frequently_asked_questions)?.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment).navigate(
                R.id.frequentFragment,
            )
//            val fm= supportFragmentManager
//            val ft = fm.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, FrequentFragment()).addToBackStack(null)
//            ft.commit()
            drawer_layout.closeDrawer(Gravity.LEFT)
            true

        })

        (navView.menu.findItem(R.id.privacy_policy)?.setOnMenuItemClickListener {
            findNavController(R.id.nav_host_fragment).navigate( R.id.privacyPolicy)

//            val fm= supportFragmentManager
//            val ft = fm.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, PrivacyPolicy()).addToBackStack(null)
//            ft.commit()
            drawer_layout.closeDrawer(Gravity.LEFT)
//            try {
//                startActivity(
//                    Intent(
//                        Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.packageName)
//                    ).addFlags(
//                        Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
//                    )
//                )
//            } catch (e: ActivityNotFoundException) {
//                startActivity(
//                    Intent(
//                        "android.intent.action.VIEW",
//                        Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")
//                    )
//                )
//            }
            true
        })
        imgsMenu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }
//        txtLocation.setOnClickListener {
//            startActivity(Intent(this, PlaceContainer::class.java))
//        }
    }

    @SuppressLint("SetTextI18n")
    private val destinationChange =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->

            when (destination.id) {
                R.id.myOrderFragment -> {
                    textView109?.text = "My Orders"
                }
                R.id.navigation_profile -> {
                    textView109?.text = "Profile"
                }
                R.id.navigation_wallet -> {
                    textView109?.text = "Wallet"
                }
                R.id.homeFragment -> {
                    val timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            textView17.setOnClickListener {
                                val bundle = Bundle()
                                bundle.getString(Constants.TYPE, 1.toString())
                                bundle.getString(Constants.NUMBER, arguments?.getString(Constants.ID))
                                findNavController(R.id.nav_host_fragment).navigate( R.id.finderFragment2,
                                    bundle)
//                                textView17.findNavController().navigate(
//                                    R.id.action_homeFragment_to_finderFragment,
//                                   bundle
//                                )
//                                FinderFragment().setArguments(bundle)
//                                val fm = supportFragmentManager
//                                val ft = fm.beginTransaction()
//                                ft.replace(R.id.nav_host_fragment, FinderFragment()).detach(
//                                    DashBoardFragment()
//                                ).addToBackStack(null)
//                                ft.commit()

                            }
                            textView109?.text = "VRise"
                        }
                        },1000)



                }
                R.id.finderFragment -> {
                    textView17.visibility = View.GONE
                }
                R.id.navigation_cart -> {
//                    nav_view.setCheckedItem(R.id.myOrderFragment)
                    textView109?.text = "Cart"
                }
                R.id.navigation_checkout -> {
                    textView109?.text = "Checkout"
                }
//                R.id.shopDetailFragmentB ->{
//
//                }
//                R.id.navigation_more -> {
//                    txtTitle?.text = "More"
//                }
                R.id.shoppingSuccess -> {
                }
                R.id.saleDone -> {
                }
                R.id.payWalletFragment -> {
                }
                R.id.verificationCode2 -> {
                }
                R.id.offerList -> {
                    textView109?.text = "Offers"
                }
                R.id.referralFragment -> {
                    textView109.text = "Refer & Earn"
                }
                R.id.trackPageFragment -> {
                    textView109?.text = "Track"
                }
                R.id.orderShow2 -> {
                    textView109?.text = "Track"
                }
                R.id.chatFragment -> {
                    textView109?.text = "Chat"
                }
            }
        }

    fun setCartBadge(value: Int) {
        Preference.put(this, CARTCOUNT, value.toString(), DATAFILE)
        navigation_new.getOrCreateBadge(R.id.navigation_cart).number = value
    }

    fun setNotificationBadge() {
        textView369.text = Preference.get(this@DashBoardContainer, DATAFILE, NOTIFYCOUNT)
    }

    fun apiService() = apiService

    fun setUserProfile(name: String, phone: String) {
        nav_view.getHeaderView(0).txtName.text = name
        nav_view.getHeaderView(0).txtPhone.text = phone
    }

    fun setSpinnerAddress(addressList: List<AddressItem>) {
        for (i in 0 until addressList.size) {
            if (addressList[i].defaultAddress.equals("1")) {
//                txtLocation.text = addressList[i].address
            }

        }
    }
    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }




    }


package com.vrise.bazaar.ex.shop.containers


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.retrofit.RetroService
import com.vrise.bazaar.ex.shop.ShopDetailFragmentB
import com.vrise.bazaar.ex.shop.pages.main.CartFragment
import com.vrise.bazaar.ex.shop.pages.main.DashBoardFragment

import com.vrise.bazaar.ex.shop.pages.main.SideBarCategory
import com.vrise.bazaar.ex.shop.pages.shop.FinderFragment
import com.vrise.bazaar.ex.shop.pages.shop.ShopDetailFragment
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.newpages.containers.NotificationContainer
import kotlinx.android.synthetic.main.activity_container_new.*

import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.add_delivery_address.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import kotlinx.android.synthetic.main.shop_detail_fragment.*
import java.util.*

class BazaarContainer : AppCompatActivity(){
    var SPLASH_TIME_OUT = 500
    var apiService: RetroService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_shop)

        apiService = (this.application as BaseApp).apiService()

        navigation.setupWithNavController(findNavController(R.id.nav_host_fragment))
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(destinationChange)


        imageView3.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                val bundle = Bundle()
                bundle.putString(Constants.ID, 4.toString())
                bundle.putString(
                    com.vrise.bazaar.newpages.utilities.Constants.APP_ID,
                    "ibrbazaar"
                )
                val inten = Intent(this@BazaarContainer, NotificationContainer::class.java)
                inten.putExtras(bundle)
                startActivity(inten)
            }
        })
    }

    var addressClickListener = object : ClickListener(){
        override fun onOneClick(v: View) {
            startActivity(Intent(this@BazaarContainer, PlaceContainer::class.java))
        }
    }

    private val destinationChange = NavController.OnDestinationChangedListener { controller, destination, arguments ->

            when (destination.id) {
                R.id.myOrderFragment -> {
                    navigation.visibility = View.VISIBLE
                }
                R.id.navigation_profile -> {
                    navigation.visibility = View.VISIBLE
                }
                R.id.navigation_wallet -> {
                    navigation?.visibility = View.VISIBLE
                }
                R.id.navigation_item_detail -> {
                    navigation.visibility = View.GONE
                }

                R.id.shopFragment ->{
                    textView7.setHint(R.string.search_for_products)
                    textView7.setOnClickListener {
                        textView7.findNavController().navigate(
                            R.id.action_shopFragment_to_finderFragment2,
                            bundleOf(
                                Constants.TYPE to 1,
                                Constants.NUMBER to arguments?.getString(Constants.ID)
                            )
                        )
                    }
                }
                R.id.navigation_shop_detail -> {
                    navigation.visibility = View.GONE
//                    cardView4.visibility = View.VISIBLE
                    textView7.setHint(R.string.search_for_products)
                    textView7.setOnClickListener {
                        textView7.findNavController().navigate(
                            R.id.action_navigation_shop_detail_to_finderFragment,
                            bundleOf(
                                Constants.TYPE to 1,
                                Constants.NUMBER to arguments?.getString(Constants.ID)
                            )
                        )
                    }
                }
                R.id.homeFragment -> {

                    Handler().postDelayed(Runnable {
                        textView106.visibility = View.VISIBLE

                        navigation.visibility = View.VISIBLE
                        textView7.visibility = View.VISIBLE
                        textView7.setHint(R.string.search_for_shops)
                        textView7.setOnClickListener(object : ClickListener() {
                            override fun onOneClick(v: View) {
                                textView7.findNavController().navigate(
                                    R.id.action_homeFragment_to_finderFragment,
                                    bundleOf(Constants.TYPE to 0)
                                )
                            }
                        })
                    }, SPLASH_TIME_OUT.toLong())
                }
//
                R.id.navigation_cart -> {
                    val timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            this@BazaarContainer!!.supportFragmentManager.popBackStack(null,
                                FragmentManager.POP_BACK_STACK_INCLUSIVE);

                            val fm = supportFragmentManager
                            val ft = fm.beginTransaction()
                            ft.replace(R.id.nav_host_fragment, CartFragment()).detach(
                                ShopDetailFragment()
                            ).hide(ShopDetailFragment()).addToBackStack(null)
                            ft.commit()
                            navigation.visibility = View.VISIBLE
                        }
                        },100)

                }
                R.id.navigation_checkout -> {
                    navigation.visibility = View.GONE
                }
                R.id.navigation_more -> {
                    navigation.visibility = View.VISIBLE
                }
                R.id.finderFragment -> {
                    navigation.visibility = View.GONE
                }
                R.id.shoppingSuccess -> {
                }
                R.id.saleDone -> {
                }
                R.id.payWalletFragment -> {
                    navigation.visibility = View.GONE
                }
                R.id.verificationCode -> {
                }
                R.id.shopDetailFragmentB -> {

                    navigation.visibility = View.GONE
                    textView7.visibility = View.GONE
                }
                R.id.trackPageFragment2 -> {
                    navigation.visibility = View.GONE }

                R.id.myOrderFragment -> {
                    navigation.visibility = View.GONE
                }
                R.id.chatFragment -> {

                }
                else -> {

                }
            }
        }

    private fun openDeliveryAddressPopUp() {
        val dialog = BottomSheetDialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_delivery_address)
        dialog.button15.setOnClickListener(object : ClickListener() {
            override fun onOneClick(v: View) {
                startActivity(Intent(this@BazaarContainer, PlaceContainer::class.java))
                dialog.dismiss()
            }
        })
        dialog.button19.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

    fun apiService() = apiService

    fun setCartBadge(value: Int) {
        Preference.put(this, Preference.CARTCOUNT, value.toString(), DATAFILE)
        navigation.getOrCreateBadge(R.id.navigation_cart).number = value
    }

    fun setNotificationBadge(value: Int) {
        textView369.text = value.toString()
    }

    override fun onResume() {
        if (Preference.get(this, DATAFILE, Preference.LOC).isNullOrBlank()) {
            openDeliveryAddressPopUp()
        }
        navigation.visibility = View.VISIBLE
        super.onResume()
    }


}

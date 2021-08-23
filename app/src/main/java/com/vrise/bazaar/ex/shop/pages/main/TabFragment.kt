package com.vrise.bazaar.ex.shop.pages.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.vrise.R
import com.vrise.bazaar.ex.shop.pages.shop.ShopsFragment
import com.vrise.bazaar.ex.util.Constants
import kotlinx.android.synthetic.main.fragment_shop_new_layout.*


class TabFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_new_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = (activity)?.findViewById(R.id.tab_Layout)
         viewPager = (activity)?.findViewById(R.id.pagers)
        tabLayout!!.newTab().setText("Shops Nearby").let { tabLayout?.addTab(it) }
        tabLayout!!.newTab().setText("Scan Store").let {

            tabLayout?.addTab(it) }

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = childFragmentManager.let {
            activity?.let { it1 ->
                tabLayout?.tabCount?.let { it2 ->
                    MyAdapter(
                        it1, it,
                        it2
                    )
                }
            }
        }

        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        try {
            val tabTextView = ((tabLayout?.getChildAt(0) as ViewGroup).getChildAt(0) as LinearLayout).getChildAt(1) as TextView?
            tabTextView?.setTypeface(tabTextView.typeface, Typeface.BOLD)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.setCurrentItem(tab.position);
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                viewPager?.setCurrentItem(tab.position);

            }
        })

    }

    class MyAdapter(private val myContext: Context, fm: FragmentManager?, var totalTabs: Int) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    ShopsFragment()
                }
                1 -> {
                    ScanCode()
                }else -> ScanCode()
            }
        }

        // this counts total number of tabs
        override fun getCount(): Int {
            return totalTabs
        }
    }
//
    public fun gotoShopFragment(id:String,name:String){
        findNavController().navigate(
            R.id.action_shopFragment_to_searchShopDetail,
            bundleOf(
                Constants.ID to (id), Constants.NAME to (name)
            )
        )
    }

    public fun gotoHomeFragment(){
        findNavController().navigate(
            R.id.action_scanCode_to_homeFragment
        )
    }

}
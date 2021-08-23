package com.vrise.bazaar.newpages.subscriber.dashboard


//import android.app.Dialog
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import android.content.Context
//import android.os.Bundle
//import android.os.Handler
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.viewpager.widget.PagerAdapter
//import androidx.recyclerview.widget.GridLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import com.vrise.R
//import com.vrise.bazaar.newpages.subscriber.addproducts.AddProducts
//import com.vrise.bazaar.newpages.subscriber.menu.todo.billing.BillingFragment
//import com.vrise.bazaar.newpages.utilities.Constants
//import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
//import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
//import com.vrise.bazaar.newpages.utilities.InitSub
//import com.vrise.bazaar.newpages.utilities.models.submodels.BannerItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.Data
//import com.vrise.bazaar.newpages.utilities.models.submodels.PendingListItem
//import com.vrise.bazaar.newpages.utilities.models.submodels.ProductListItem
//import kotlinx.android.synthetic.main.app_bar_sub_container.*
//import kotlinx.android.synthetic.main.fragment_dashboard.*
//import java.util.*
//import android.widget.RelativeLayout
//import android.content.Intent
//import android.graphics.drawable.ColorDrawable
//import androidx.fragment.app.FragmentActivity
//import android.view.Window
//import com.vrise.bazaar.newpages.subscriber.SubscriberProfileNew
//import com.vrise.bazaar.newpages.utilities.Constants.ACTIVITY
//import java.lang.ref.WeakReference
//
//
//class Dashboard : InitSub() {
//
//    override fun networkAvailable() {
//
//    }
//
//    override fun networkUnavailable() {
//    }
//
//    private var viewModel: DataList? = null
//
//    private val imagesObserver = Observer<Data> { t ->
//        if (t != null) {
//            if (t.action == "profile") {
//                navigateTo(activity, ACTIVITY, false, null,
//                    SubscriberProfileNew::class.java, null,
//                    arrayOf(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP), "")
//            } else {
//                setImageAdapter(t)
//            }
//        }
//    }
//    private var handler: Handler? = null
//    var page = 0
//    val delay = 5000
//    private var adapter: ViewPagerAdapter? = null
//
//    val runnable = object : Runnable {
//        override fun run() {
//            try {
//                if (adapter != null) {
//                    if (adapter?.count == page) {
//                        page = 0
//                    } else {
//                        page++
//                    }
//                    recyclerView.setCurrentItem(page, true)
//                    if (handler != null) {
//                        handler?.postDelayed(this, delay.toLong())
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    private fun setImageAdapter(t: Data) {
//        if (t.notifiCount != null) {
//            if (t.notifiCount.isNotBlank()) {
//                com.vrise.bazaar.newpages.utilities.Preference.putPreference(activity, Constants.Rem, t.notifiCount, DATAFILE)
//            }
//            if (activity != null && isAdded) {
//                activity?.invalidateOptionsMenu()
//            }
//        }
//        if (t.productList != null) {
//            if (t.productList.isNotEmpty())
//                setItemAdapter(t.productList, t.pendingList)
//            if (t.dueAmount == "0") {
//                textView23.text = "Next Bill Will Be On ${t.nextBill}"
//            } else {
//                textView23.text = "Settle Bill Amount - ₹ ${t.dueAmount}"
//            }
//        }
//        if (t.banner != null) {
//            val scale = context?.resources?.displayMetrics?.density ?: 0f
//            if (t.banner.isNotEmpty()) {
//                activity?.let {
//                    adapter = ViewPagerAdapter(t.banner, it)
//                }
//                recyclerView.adapter = adapter
//            }
//        }
//    }
//
//    class ViewPagerAdapter(imagesList: ArrayList<BannerItem>?, act: FragmentActivity) : PagerAdapter() {
//        val context: WeakReference<Context> = WeakReference(act)
//        private val images: ArrayList<BannerItem>? = imagesList
//
//        override fun isViewFromObject(p0: View, p1: Any): Boolean {
//            return p0 == (p1 as ConstraintLayout)
//        }
//
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            val imageLayout = LayoutInflater.from(container.context).inflate(R.layout.item_images, container, false)
//            val imageView = imageLayout?.findViewById<ImageView>(R.id.image)
//
//            imageView?.let {
//                images?.let { let ->
//                    Glide.with(container.context).load(let[position].image).thumbnail(0.1f).into(it)
//                }
//            }
//
//            imageLayout.setOnClickListener {
//                context.get()?.let { context ->
//                    val builder = Dialog(context)
//                    builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                    builder.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
//                    builder.setOnDismissListener { dialogInterface ->
//                        dialogInterface.dismiss()
//                    }
//
//                    val imageViews = ImageView(context)
//                    imageViews.setImageDrawable(imageView?.drawable)
//                    builder.addContentView(imageViews, RelativeLayout.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.MATCH_PARENT))
//                    builder.show()
//                }
//            }
//            container.addView(imageLayout, 0)
//            return imageLayout
//        }
//
//        override fun getCount(): Int {
//            return images?.size ?: 0
//        }
//
//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            container.removeView(`object` as View)
//        }
//
//    }
//
//    private fun setItemAdapter(products: ArrayList<ProductListItem?>?, pendingList: List<PendingListItem?>?) {
//        recyclerView2.layoutManager = GridLayoutManager(activity, 3)
//        val items: ArrayList<Any?> = ArrayList()
//        products?.let {
//            items.addAll(it)
//        }
//        pendingList?.let {
//            items.addAll(it)
//        }
//        recyclerView2.adapter = ItemList(activity, items)
//    }
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
//        setHasOptionsMenu(true)
//
//        activity?.page_title?.text = getString(R.string.dashboard)
//
//        handler = Handler()
//        getData()
//        textView24.setOnClickListener {
//            navigateTo(activity, Constants.FRAGMENT, true, BillingFragment(), null, null, null, "")
//        }
//
//        floatingActionButton.setOnClickListener {
//            navigateTo(activity, Constants.FRAGMENT, true, AddProducts(), null, null, null, "")
//        }
//
//    }
//
//    private fun getData() {
//        viewModel = ViewModelProviders.of(activity!!).get(DataList::class.java)
//        viewModel?.getDashDatas(com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(activity!!, DATAFILE, Constants.ID))?.observe(this, imagesObserver)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_dashboard, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setInitializer()
//
//        next.setOnClickListener {
//            try {
//                if (recyclerView.currentItem <= recyclerView.adapter!!.count) {
//                    recyclerView.currentItem = recyclerView.currentItem + 1
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        back.setOnClickListener {
//            try {
//                if (recyclerView.currentItem >= 1) {
//                    recyclerView.currentItem = recyclerView.currentItem - 1
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//        try {
//            if (handler != null) {
//                handler?.removeCallbacks(runnable)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onResume() {
//        try {
//            if (activity != null && isAdded) {
//                activity?.invalidateOptionsMenu()
//            }
//            if (handler != null)
//                handler?.postDelayed(runnable, delay.toLong())
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        super.onResume()
//    }
//
//
//}

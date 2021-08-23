package com.vrise.bazaar.ex.shop.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.vrise.R
import com.vrise.bazaar.MyApplication
import com.vrise.bazaar.ex.adapters.CategorySideAdapter
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.CategoryTree
import com.vrise.bazaar.ex.model.submodels.MainSubCategories
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.CartViewModel
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.fragment_side_bar_category.*


class SideBarCategory : Fragment() {

    private lateinit var viewModel: CartViewModel

    private lateinit var viewModels: ItemDetailViewModel
    private var itemsData = ArrayList<MainSubCategories?>()
    private var expandedSize =  ArrayList<MainSubCategories>()
    private var shopId:String? = null
    private var ids: String?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ids = BaseApp.globalVariable



        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(CartViewModel::class.java)
        viewModels = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(ItemDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side_bar_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemsrv.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE

        textView382.setOnClickListener {
            itemsrv.visibility = View.GONE
        }
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.sidebar(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = ids


                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {

                        itemsrv.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        var adapter = activity?.let { it1 ->
                            CategorySideAdapter(
                                it1, viewModels,
                                it.category_tree as List<CategoryTree>?
                            )
                        }
                        itemsrv.adapter = adapter
                        (itemsrv.getItemAnimator() as SimpleItemAnimator).setSupportsChangeAnimations(
                            false
                        )


                    }


                })
            } else {
                Instances.toast(activity, getString(R.string.no_internet))
            }
        }

    }




}
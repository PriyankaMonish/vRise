package com.vrise.bazaar.ex.shop.pages.shop

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ShopProductsItem
import com.vrise.bazaar.ex.model.submodels.ProductList
import com.vrise.bazaar.ex.model.submodels.SubLevel
import com.vrise.bazaar.ex.model.submodels.SubcategoryLevel
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.ItemDetailViewModel
import com.vrise.bazaar.ex.shop.pages.ItemsAdapter
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import com.vrise.databinding.FragmentProductListBinding
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.lyt_toolbar.*


class ItemFragment : Fragment(), OnSubMainCategoryClicked, OnSubCategoryClicked {

    lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ItemDetailViewModel
    var itemsAdapter: ItemsAdapter? = null
    var subCategoryAdapter: SubCategoryAdapter? = null
    var productList: List<ProductList?>? = null
    var shopData:List<ShopProductsItem?>? = null
    var subCategoryList: List<SubLevel>? = null
    var SPLASH_TIME_OUT = 500
    var subcategoryLevels: List<SubcategoryLevel>? = null
private var shopName:String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(ItemDetailViewModel::class.java)
        (activity as DashBoardContainer).txtTitle?.text = ""
        BaseApp.enableGlobal = 1
        shopName =   arguments?.getString(Constants.NAME) ?: ""

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        Handler().postDelayed(Runnable {
            getshopDetail()
        }, SPLASH_TIME_OUT.toLong())


        (activity as DashBoardContainer).textView17.visibility = View.VISIBLE
        (activity as DashBoardContainer).textView1009.text = "Vrise"

    }
    private fun getshopDetail() {
        include4.visibility =View.VISIBLE
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.getItemList(
                    Request(
                        utoken = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.TOKEN
                        ),
                        search_Key = "",
                        shop_id = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.SHOP_ID
                        ),
                        category_id = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.CATEGORY
                        ),
                        main_subcategory_id = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.MAIN_SUB_CATEGORY_ID
                        ),
                        subcategory_id = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.SUB_CATEGORY_ID
                        )

                    )
                )?.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        include4.visibility =View.GONE

                        txtMainCategory.text = it.getCategoryListData()?.mainSubcategory!!.name

                        textView312.text = shopName?.toString()?.split(' ')?.joinToString(" ") { it.capitalize() }

                        txtMainCategory.setOnClickListener {


                            findNavController().navigate(
                                R.id.sideBarCategory

                            )
//                            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                            val fm = requireActivity().supportFragmentManager
//                            val ft = fm.beginTransaction()
//        ft.replace(R.id.nav_host_fragment, SideBarCategory()).addToBackStack(null).detach(ItemFragment())
//                            ft.commit()
                        }
                        subcategoryLevels = it.getCategoryListData()?.subcategoryLevels
                        binding.recycleCategory.adapter =
                            MainCategoryAdapter(
                                subcategoryLevels,
                                activity,
                                this,
                                binding.root,
                                requireContext(), this
                            )

                        subCategoryList = ArrayList<SubLevel>()
                        val subLevel = SubLevel()
                        subLevel.id = Constants.ALL
                        subLevel.name = Constants.ALL
                        subLevel.subCategory = Constants.ALL
                        (subCategoryList as ArrayList<SubLevel>).add(subLevel)
                        if (it.getCategoryListData()?.subcategoryLevels?.size!! > 0)
                            for (i in 0 until it.getCategoryListData()?.subcategoryLevels?.get(0)?.subLevels!!.size) {
                                (subCategoryList as ArrayList<SubLevel>).add(
                                    it.getCategoryListData()?.subcategoryLevels?.get(
                                        0
                                    )?.subLevels!![i]
                                )
                            }
                        subCategoryAdapter = SubCategoryAdapter(
                            subCategoryList,
                            activity,
                            this,
                            binding.root,
                            requireContext(), this
                        )
                        binding.recycleSubCategory.adapter = subCategoryAdapter


                        productList = it.getProductList()
                        itemsAdapter =
                            ItemsAdapter(
                                productList,
                                activity, this, binding.root, requireContext(), viewModel, txtCount
                            )
                        binding.recycleItems.adapter = itemsAdapter
                    }
                })
            }
        }
    }

    override fun onSubMainClick(position: Int) {
        subCategoryAdapter?.filter?.filter(subcategoryLevels?.get(position)?.id)
    }

    override fun onSubClick(position: Int) {
        itemsAdapter?.filter?.filter(subCategoryList?.get(position)?.id)
    }


}


interface OnSubMainCategoryClicked {
    fun onSubMainClick(position: Int)
}

interface OnSubCategoryClicked {
    fun onSubClick(position: Int)
}




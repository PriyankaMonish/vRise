package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrise.R
import com.vrise.bazaar.ex.app.BaseApp
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OrdersViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Interfaces
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.fragment_my_order.include4
import kotlinx.android.synthetic.main.fragment_my_order.no_items
import kotlinx.android.synthetic.main.fragment_sale_model.*
import kotlinx.android.synthetic.main.order_fragment.*
import java.text.DecimalFormat

class SaleModel : Fragment() {
    private lateinit var viewModel: OrdersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale_model, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(OrdersViewModel::class.java)
          getDetails()


        continuedly.setOnClickListener {
            it.findNavController().navigate(R.id.myOrderFragment)
        }
    }

   @SuppressLint("SetTextI18n")
   private fun getDetails(){


           Instances.InternetCheck { internet ->
               if (internet) {
                   include4.visibility = View.VISIBLE
                   viewModel.getSaleModel(
                       Request(
                           utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                           sale_id =   arguments?.getString(Constants.saleId) ?: "",

                       )
                   )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

                       if (it != null) {
                           include4.visibility = View.GONE
                           (it.data?.total_amount)
                           val precision = DecimalFormat("0.00")
                           textView3517.setText("Rs." +
                                   (String.format(precision.format( it.data?.total_amount?.toInt()))))
                       }
                   })
               } else {
                   include4.visibility = View.GONE
                   Instances.toast(activity, getString(R.string.no_internet))

               }
           }

   }
}
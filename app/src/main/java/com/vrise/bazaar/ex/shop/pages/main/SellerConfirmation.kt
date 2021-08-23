package com.vrise.bazaar.ex.shop.pages.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OutViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.fragment_seller_confirmation.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SellerConfirmation.newInstance] factory method to
 * create an instance of this fragment.
 */
class SellerConfirmation : Fragment() {
    private lateinit var viewModel: OutViewModel
    private var sellerId:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))
        ).get(OutViewModel::class.java)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seller_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSellerData()
        arguments?.let {
            if(it.containsKey("SellerId")){
                sellerId = arguments?.getString(Constants.SellerId) ?: ""
            }
        }


        button33.setOnClickListener {
                view.findNavController().navigate(R.id.action_sellerConfirmation_to_myOrderFragment)
        }
    }

    private fun getSellerData() {
        Instances.InternetCheck { internet ->
            if (internet) {
                viewModel.sellerOrder(
                    Request(
                        utoken = Preference.get(activity, Preference.DATAFILE, Preference.TOKEN),
                        shop_id = sellerId
                    )
                )?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
                    if (it != null) {

                        textView388.setText(it.data?.seller_response)
                    }

                })
            }
        }
    }

}
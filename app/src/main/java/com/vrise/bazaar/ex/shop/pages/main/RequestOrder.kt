package com.vrise.bazaar.ex.shop.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.temp.OrdPastItem
import com.vrise.bazaar.ex.model.temp.OrdPendingItem
import com.vrise.bazaar.ex.model.temp.OrdProductsItem
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OrdersViewModel
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Preference
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.fragment_cart_new.*
import kotlinx.android.synthetic.main.fragment_request_order.*
import kotlinx.android.synthetic.main.fragment_sale_model.*
import java.text.DecimalFormat

class RequestOrder : Fragment() {

    private lateinit var viewModel: OrdersViewModel

    private var saleId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        saleId = arguments?.getString(Constants.saleId) ?: ""

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                RepoLive.getInstance(
                    Instances.serviceApi(
                        activity
                    ), activity
                )
            )
        ).get(OrdersViewModel::class.java)
                    getData()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        Instances.InternetCheck { internet ->
            if (internet) {

                viewModel.getrequest(
                    Request(
                        utoken = Preference.get(
                            activity,
                            Preference.DATAFILE,
                            Preference.TOKEN
                        ),
                        sale_id = saleId
                    )
                )?.observe(
                    requireActivity()!!,
                    Observer {
                        val precisions = DecimalFormat("0.00")


                        textView34111.setText("Rs . " + "" + (String.format(precisions.format(it?.requestedorder?.get(0)?.deliveryCharge?.toInt()))))
                        textView34133.setText("Rs . " + "" + (it?.requestedorder?.get(0)?.grandTotal))
                        textView3455.setText("Rs . " + "" + (String.format(precisions.format(it?.requestedorder?.get(0)?.packing_charge))))

                        if (it?.requestedorder!!.isEmpty()){
                            Toast.makeText(activity, "No Updations has been done in the particular order by the seller.", Toast.LENGTH_SHORT).show()
                        }
                        recy_two.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        recy_two.adapter = requestAdapter(
                            it?.requestedorder!!.get(0)!!.ordProducts
                        )


                    })
            }

        }
    }



    class requestAdapter(var dataItem: List<OrdProductsItem?>?) : RecyclerView.Adapter<requestAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return dataItem?.size ?: 0
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            if (dataItem != null) {
                val precisionsw = DecimalFormat("0.00")
                holder.item?.text = dataItem?.get(position)?.name
                holder.unit?.text = dataItem?.get(position)?.size
                holder.qty?.text = dataItem?.get(position)?.productQuantity.toString()
//                holder.price?.text = dataItem?.get(position)?.price
                holder.price?.setText((String.format(
                        precisionsw.format(
                            dataItem?.get(position)?.price?.toInt()
                        )
                    ))
                )
            }
        }


        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item = itemView.findViewById<TextView?>(R.id.textView337)
            val unit = itemView.findViewById<TextView?>(R.id.textView355)
            val qty = itemView.findViewById<TextView?>(R.id.textView338)
            val price = itemView.findViewById<TextView?>(R.id.textView339)


        }
    }


    }




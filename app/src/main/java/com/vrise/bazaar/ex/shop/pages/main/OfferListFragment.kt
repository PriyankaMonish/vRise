package com.vrise.bazaar.ex.shop.pages.main

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.ex.model.OfferItem
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.OfferListViewModel
import com.vrise.bazaar.ex.util.Constants

import com.vrise.bazaar.ex.util.Constants.PEAK
import com.vrise.bazaar.ex.util.Constants.VALUE
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.serviceApi
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.offer_list_fragment.*



class OfferListFragment : Fragment() {

	private lateinit var viewModel: OfferListViewModel
	var walletStatus = "0"
	var amount = 0.0
	var discountAmount = ""
	var couponId = ""
	companion object {
		var Peak= ""
	}

	var cashbackAmount = ""
	var tempAmount = 0.0
	var cat = ""

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.offer_list_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		val myClipboard: ClipboardManager



		myClipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

		Peak = arguments?.getString(PEAK, "").toString()
		walletStatus=Peak

		val bundle=Bundle()
		bundle!!.putString(PEAK, walletStatus)
     	val fragobj = OutFragment()
		fragobj.setArguments(bundle)

	progress?.visibility = View.VISIBLE
//
	progress?.visibility = View.VISIBLE
		viewModel = ViewModelProvider(this, ViewModelFactory(RepoLive.getInstance(serviceApi(activity), activity))).get(OfferListViewModel::class.java)


		Instances.InternetCheck { internet ->
			if (internet) {
				viewModel.getOffea(
					Request(utoken = Preference.get(activity, DATAFILE, TOKEN))
				)?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
					recyclerView32.adapter = OfferList(it)


				progress?.visibility = View.GONE
				})

			} else {
				Instances.toast(activity, getString(R.string.no_internet))
			progress?.visibility = View.GONE
			}
		}

	}

	class OfferList(var dataItem: List<OfferItem?>?) : RecyclerView.Adapter<OfferList.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_offer_list, parent, false))
		}

		override fun getItemCount(): Int {
			return dataItem?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {

			if (dataItem != null) {
				val bundle=Bundle()
				var horse=" "
                 var cat=dataItem?.get(holder.adapterPosition)?.offerCode
				holder.textView232?.text = dataItem?.get(holder.adapterPosition)?.offerTitle + " " + dataItem?.get(holder.adapterPosition)?.offerType
				holder.textView233?.text = dataItem?.get(holder.adapterPosition)?.offerDescription
				holder.textView234?.text = dataItem?.get(holder.adapterPosition)?.offerCode
//				holder.textView127?.text= bundle.getString(LOAD, "").toString()
				holder.textView234?.setOnClickListener(View.OnClickListener {

	                val cm: ClipboardManager = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
					cm.setText(holder.textView234.getText())
//					Toast.makeText(it.context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
					val activity = it.getContext() as AppCompatActivity
					val myFragment: Fragment = OutFragment()
			     	val args = Bundle()
					args.putString(VALUE, cat)
					args.putString(PEAK,Peak)

					it.findNavController().navigate(
						R.id.action_offerList_to_outFragment,args
					)

//					myFragment.setArguments(args)
//					activity.supportFragmentManager.beginTransaction()
//					.hide(OfferListFragment())
//					.show(myFragment)
//                            .replace(R.id.nav_host_fragment, myFragment)
//						.addToBackStack(null).commit()

				})
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val textView232 = itemView.findViewById<TextView?>(R.id.textView232)
			val textView233 = itemView.findViewById<TextView?>(R.id.textView233)
			val textView234 = itemView.findViewById<TextView?>(R.id.textView234)
			

		}


	}




}

package com.vrise.bazaar.ex.shop.pages.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.temp.TransactionsItem
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.WalletViewModel
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.changeDateFormat
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN

import com.payu.india.Payu.PayuConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.item_add_money.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.wallet_fragment.*

class WalletFragment : Fragment() {
	private lateinit var viewModel: WalletViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.wallet_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		progress.visibility = View.VISIBLE
		viewModel = ViewModelProvider(this, ViewModelFactory(RepoLive.getInstance(Instances.serviceApi(activity), activity))).get(WalletViewModel::class.java)
		(activity as DashBoardContainer).textView17.visibility = View.VISIBLE
		no_items.visibility = View.GONE

		getD()

		textView41.text = ""

		button8.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				openDialog()
			}
		})
	}

	private fun openDialog() {
		val dialog = BottomSheetDialog(requireActivity())
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
		dialog.setContentView(R.layout.item_add_money)
		dialog.textView222.setOnClickListener(object : ClickListener() {
			override fun onOneClick(v: View) {
				dialog.progress.visibility = View.VISIBLE
				if (dialog.textInputEditText12.text.toString().isNotBlank()) {
					if (dialog.textInputEditText12.text.toString() == "0") {
						toast(activity, "enter valid amount")
					} else {
						if (activity is DashBoardContainer) {
							view?.findNavController()?.navigate(
								R.id.action_navigation_wallet_to_payWalletFragment, bundleOf(
									PayuConstants.AMOUNT to dialog.textInputEditText12.text.toString()
								)
							)

						} else if (activity is BazaarContainer) {
							view?.findNavController()?.navigate(
								R.id.action_navigation_wallet_to_payWalletFragment2,
								bundleOf(PayuConstants.AMOUNT to dialog.textInputEditText12.text.toString())
							)

						}
					}
					dialog.progress.visibility = View.GONE
					dialog.dismiss()
				} else {
					dialog.progress.visibility = View.GONE
					toast(activity, "enter an amount")
				}
			}
		})
		dialog.progress.visibility = View.GONE
		dialog.show()
	}

	private fun getD() {
		Instances.InternetCheck { internet ->
			if (internet) {
				viewModel.getWalletDetails(
					Request(
						utoken = Preference.get(activity, DATAFILE, TOKEN)
					)
				)?.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {
					if (it?.transactions.isNullOrEmpty()) {
						no_items.visibility = View.VISIBLE
					} else {
						no_items.visibility = View.GONE
					}
					if (it != null) {
						textView42.text = "₹${it.balance ?: ("0.0")}"
					} else {
						textView42.text = "₹${"0.0"}"
					}
					recyclerView7.adapter = WalletListAdapter(
						activity, it?.transactions
					)
					progress.visibility = View.GONE
				})
			} else {
				toast(activity, getString(R.string.no_internet))
				progress?.visibility = View.GONE
			}
		}
	}

	class WalletListAdapter(val activity: FragmentActivity?, val transactions: List<TransactionsItem?>?) : RecyclerView.Adapter<WalletListAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_wallet_item, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return transactions?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (transactions != null) {
				if (transactions[holder.adapterPosition]?.type == "credit") {
					Picasso.get().load(R.drawable.ic_credit).into(holder.dot)
					if (activity != null) holder.textView46?.setTextColor(
						ContextCompat.getColor(
							activity, R.color.credit_green
						)
					)
				} else {
					Picasso.get().load(R.drawable.ic_debit).into(holder.dot)
					if (activity != null) holder.textView46?.setTextColor(
						ContextCompat.getColor(
							activity, R.color.debit_red
						)
					)
				}
				holder.textView46?.text =
					(transactions[holder.adapterPosition]?.amount ?: "0") + " ₹"
				holder.textView45?.text = changeDateFormat(
					"dd-MM-yyyy", "dd/MM/yyyy", transactions[holder.adapterPosition]?.date ?: ""
				)
				holder.textView44?.text = transactions[holder.adapterPosition]?.title.toString()

			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val dot = itemView.findViewById<ImageView?>(R.id.textView43)
			val textView44 = itemView.findViewById<TextView?>(R.id.textView44)
			val textView45 = itemView.findViewById<TextView?>(R.id.textView45)
			val textView46 = itemView.findViewById<TextView?>(R.id.textView46)
		}

	}

}

package com.vrise.bazaar.ex.shop.pages.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.SearchResultItem
import com.vrise.bazaar.ex.shop.ShopDetailFragmentB
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.FinderViewModel
import com.vrise.bazaar.ex.shop.pages.SearchShopDetail
import com.vrise.bazaar.ex.shop.pages.main.DashBoardFragment
import com.vrise.bazaar.ex.shop.pages.main.TabFragment
import com.vrise.bazaar.ex.util.ClickListener
import com.vrise.bazaar.ex.util.Constants
import com.vrise.bazaar.ex.util.Instances
import com.vrise.bazaar.ex.util.Instances.hideKeyboard
import com.vrise.bazaar.ex.util.Instances.serviceApi
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.fragment_finder.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.lyt_toolbar.*
import java.util.*

class FinderFragment : Fragment() {

	private lateinit var viewModel: FinderViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_finder, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		(activity as DashBoardContainer).textView17.visibility = View.GONE


		progress.visibility = View.VISIBLE

		viewModel = ViewModelProvider(
			this, ViewModelFactory(
				RepoLive.getInstance(
					serviceApi(
						activity
					), activity
				)
			)
		).get(FinderViewModel::class.java)

		Instances.InternetCheck { internet ->
			if (internet) {
				search("")
			} else {
				Instances.toast(activity, getString(R.string.no_internet))
				progress?.visibility = View.GONE
			}
		}
	}

	private fun search(searchKey: String) {
		when {
			(arguments?.getInt(Constants.TYPE) ?: 0) == 0 -> {
				progress.visibility = View.VISIBLE
				searchView2.hint = getString(R.string.shop_search)
				viewModel.getShops(
					Request(
						utoken = Preference.get(activity, DATAFILE, TOKEN),
						search_Key = searchKey,
						subcategory_id = "",
						category_id = Preference.get(activity, DATAFILE, Preference.CATEGORY) ?: ""
					)
				)?.observe(viewLifecycleOwner, Observer {
					if (it != null) {
						recyclerView30.layoutManager = LinearLayoutManager(activity)
						recyclerView30.adapter = MoreShopsAdapter(
							it, activity,this
						)
						val filterItems = it
						searchView2.addTextChangedListener(object : TextWatcher {
							override fun afterTextChanged(s: Editable?) {

							}

							override fun beforeTextChanged(
								s: CharSequence?, start: Int, count: Int, after: Int
							) {

							}

							override fun onTextChanged(
								s: CharSequence?, start: Int, before: Int, count: Int
							) {
								if ((s?.length ?: 0) > 0) {
									recyclerView30.adapter = MoreShopsAdapter(
										filterItems.filter {
											it?.storeName.toString().toLowerCase(Locale.ENGLISH)
												.contains(s.toString().toLowerCase(Locale.ENGLISH))
										}, activity,this@FinderFragment
									)
								} else {
									recyclerView30.adapter = MoreShopsAdapter(
										filterItems, activity,this@FinderFragment
									)
								}
							}
						})
					}
					progress.visibility = View.GONE
				})
			}
			(arguments?.getInt(Constants.TYPE) ?: 0) == 1 -> {
				recyclerView30.visibility = View.GONE
				progress.visibility = View.VISIBLE
				searchView2.setHint(getString(R.string.product_search))
				viewModel.getCatos(
					Request(
						utoken = Preference.get(activity, DATAFILE, TOKEN),
						search_Key = searchKey,
						subcategory_id = "",
						category_id = Preference.get(activity, DATAFILE, Preference.CATEGORY) ?: "",
						shop_id = arguments?.getString(Constants.NUMBER) ?: ""
					)
				)?.observe(viewLifecycleOwner, Observer {
					if (it != null) {
						recyclerView30.layoutManager = GridLayoutManager(activity, 2)
						recyclerView30.adapter = RecommendedItemsAdapter(it, activity)
						val filterItems = it
						searchView2.addTextChangedListener(object : TextWatcher {
							override fun afterTextChanged(s: Editable?) {

							}

							override fun beforeTextChanged(
								s: CharSequence?, start: Int, count: Int, after: Int
							) {

							}

							override fun onTextChanged(
								s: CharSequence?, start: Int, before: Int, count: Int
							) {
								if ((s?.length ?: 0) > 0) {
									recyclerView30.adapter = RecommendedItemsAdapter(
										filterItems.filter {
											it?.name.toString().toLowerCase(Locale.ENGLISH)
												.contains(
													s.toString().toLowerCase(
														Locale.ENGLISH
													)
												)
										}, activity
									)
								} else {
									recyclerView30.adapter = RecommendedItemsAdapter(
										filterItems, activity
									)
								}
							}
						})
					}
					progress.visibility = View.GONE
				})

			}
			else -> {
				progress.visibility = View.GONE
			}
		}
	}

	public class MoreShopsAdapter(
		val dataItem: List<SearchResultItem?>?, val activity: FragmentActivity?,val frag: FinderFragment
	) : RecyclerView.Adapter<MoreShopsAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_more_shops, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return dataItem?.size ?: 0
		}

		@SuppressLint("SetTextI18n")
		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (dataItem != null) {
				Instances.showBlur(
					activity,
					dataItem[holder.adapterPosition]?.imgLink.toString(),
					holder.imageView8
				)
				holder.textView10?.text = dataItem[holder.adapterPosition]?.storeName.toString()
				holder.textView13?.text =
					Math.round(dataItem[holder.adapterPosition]?.distance!!.toDouble()).toString() + " km from you"
				holder.ratingBar?.progress =
					dataItem[holder.adapterPosition]?.rating.toString().toInt() ?: 0
				holder.item?.setOnClickListener(object : ClickListener() {
					override fun onOneClick(v: View) {
						hideKeyboard(activity)
						val args = Bundle()
						args.putString(Constants.ID, (dataItem[holder.adapterPosition]?.id ?: ""))
						v.findNavController().navigate(
							R.id.action_finderFragment2_to_searchShopDetail,
							args
						)
					}
				})
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val ratingBar = itemView.findViewById<RatingBar?>(R.id.ratingBar)
			val textView13 = itemView.findViewById<TextView?>(R.id.textView13)
			val textView10 = itemView.findViewById<TextView?>(R.id.textView10)
			val imageView8 = itemView.findViewById<ImageView?>(R.id.imageView8)
			val item = itemView.findViewById<CardView?>(R.id.cardView6)
		}

	}

	class RecommendedItemsAdapter(
		val dataItem: List<SearchResultItem?>?, val activity: FragmentActivity?
	) : RecyclerView.Adapter<RecommendedItemsAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_recommended_item, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return dataItem?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (dataItem != null) {
				Instances.showBlur(
					activity,
					dataItem[holder.adapterPosition]?.imgLink.toString(),
					holder.imageViews1
				)
				holder.textView14?.text = dataItem[holder.adapterPosition]?.name.toString()
				if (!dataItem[holder.adapterPosition]?.sizeStockPrice.isNullOrEmpty()) {
					holder.chip3?.visibility = View.VISIBLE
					holder.chip2?.visibility = View.VISIBLE
					holder.chip2?.text = dataItem[holder.adapterPosition]?.sizeStockPrice?.get(0)
						?.sizeName.toString()
					holder.chip3?.text =
						"₹" + dataItem[holder.adapterPosition]?.sizeStockPrice?.get(0)?.sizePrice
							?: "0.0"
				}
				holder.textView15?.text = dataItem[holder.adapterPosition]?.storeName.toString()

				holder.item?.setOnClickListener {
					hideKeyboard(activity)
					it.findNavController().navigate(
						R.id.action_finderFragment_to_navigation_item_detail, bundleOf(
							Constants.ID to (dataItem[holder.adapterPosition]?.sprdtid ?: "")
						)
					)
				}
				if (dataItem[holder.adapterPosition]?.spotDelivery != "1") {
					holder.textView241?.visibility = View.VISIBLE
					holder.textView241?.text = dataItem[holder.adapterPosition]?.nextAvailable.toString()
				} else {
					holder.textView241?.visibility = View.GONE
				}
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val textView15 = itemView.findViewById<TextView?>(R.id.textView15)
			val chip3 = itemView.findViewById<Chip?>(R.id.chip3)
			val chip2 = itemView.findViewById<Chip?>(R.id.chip2)
			val textView14 = itemView.findViewById<TextView?>(R.id.textView14)
			val imageViews1 = itemView.findViewById<ImageView?>(R.id.imageViews1)
			val textView241 = itemView.findViewById<TextView?>(R.id.textView241)
			val item = itemView.findViewById<CardView?>(R.id.cardView21)

			init {
				textView241?.visibility = View.GONE
				chip3?.visibility = View.GONE
				chip2?.visibility = View.GONE
			}
		}

	}

}

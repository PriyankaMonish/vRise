package com.vrise.bazaar.ex.shop.pages.shop

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import com.vrise.R
import com.vrise.bazaar.ex.model.mainmodels.Filters
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.newmodels.ItemsItem
import com.vrise.bazaar.ex.model.newmodels.ShopProductsItem
import com.vrise.bazaar.ex.model.newmodels.ShopRecommProductItem
import com.vrise.bazaar.ex.model.newmodels.Subcategory
import com.vrise.bazaar.ex.model.submodels.Filterdata
import com.vrise.bazaar.ex.model.submodels.ShopBrandsItem
import com.vrise.bazaar.ex.model.submodels.Valuess
import com.vrise.bazaar.ex.shop.Filtersub
import com.vrise.bazaar.ex.shop.ShopDetailFragmentB
import com.vrise.bazaar.ex.shop.containers.BazaarContainer
import com.vrise.bazaar.ex.shop.containers.DashBoardContainer
import com.vrise.bazaar.ex.shop.containers.MainActivity
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive
import com.vrise.bazaar.ex.shop.dataprovider.ViewModelFactory
import com.vrise.bazaar.ex.shop.interfaces.IFilterConstraintListener
import com.vrise.bazaar.ex.shop.interfaces.ShopDetailViewModel
import com.vrise.bazaar.ex.util.*
import com.vrise.bazaar.ex.util.Instances.showBlur
import com.vrise.bazaar.ex.util.Instances.toast
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import kotlinx.android.synthetic.main.activity_container_new.*
import kotlinx.android.synthetic.main.activity_container_shop.*
import kotlinx.android.synthetic.main.activity_container_shop.container
import kotlinx.android.synthetic.main.bg_main.*
import kotlinx.android.synthetic.main.bootomsheet2.*
import kotlinx.android.synthetic.main.custom_list_dialog_new.*
import kotlinx.android.synthetic.main.item_progress_sheet.*
import kotlinx.android.synthetic.main.shop_detail_fragment.*


@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class ShopDetailFragment : Fragment(){


	private lateinit var viewModel: ShopDetailViewModel
	private var itemList: ArrayList<Subcategory?>? = null
	private var vegFilter = MutableLiveData<Boolean>()
	private var newsnew:List<Filterdata>?=null
	private var newsub:List<Valuess>?=null

	var SPLASH_TIME_OUT = 500
	var behaviour: BottomSheetBehavior<FrameLayout>? = null
	var isExpand = false
	private var filter_id = Filters()
	private  var previousFilter:String = ""
	private var selectedFilterPosition = 0

	private var shopId : String? = null


	private val mMessageReceiver = object : BroadcastReceiver() {
		override fun onReceive(context: Context, intent: Intent) {

			try {
				if (isExpand) {
					behaviour!!.state = BottomSheetBehavior.STATE_HIDDEN
					isExpand = false
				} else {
					behaviour!!.state = BottomSheetBehavior.STATE_EXPANDED
					isExpand = true
				}
			} catch (e: Exception) {

			}
		}
	}
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {


		return inflater.inflate(R.layout.shop_detail_fragment, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		shopId = requireArguments().getString(Constants.ID)

		(activity as DashBoardContainer).textView17.visibility = View.VISIBLE

		(activity as DashBoardContainer).textView1009.text = "Vrise"
		(activity as BazaarContainer).textView109.text = "Vrise"
		viewModel = ViewModelProvider(
			this, ViewModelFactory(
				RepoLive.getInstance(
					Instances.serviceApi(
						activity
					), activity
				)
			)
		).get(ShopDetailViewModel::class.java)

		floatingActionButton7.expand()
		textView106.expand()
		GGGinclude4.visibility = View.VISIBLE
		requireArguments().getString(Constants.IMAGE)?.let { image ->
			if (image.isNotBlank() && image != "null") {
				Picasso.get().load(image).fit().into(constraintLayout10)
			}
		}

		Handler().postDelayed(Runnable {
			textView106.visibility = View.VISIBLE

			vegFilter.value = false
			vegFilter.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

				if (it) getshopDetail(vegFilter) else getshopDetail(vegFilter)
			})
		}, SPLASH_TIME_OUT.toLong())


		textView106.setOnClickListener {
			if (itemList != null) {
				val dialog = Dialog(requireActivity())
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
				dialog.setCancelable(true)
				dialog.setContentView(R.layout.custom_list_dialog_new)
				dialog.textView223.text = "Menu"
				dialog.recyclerView16.layoutManager = LinearLayoutManager(activity)
				val adapter =
					CategorieListAdapter(activity, itemList, object : Interfaces.ReturnText {
						override fun getValue(textValue: String) {
							dialog.dismiss()
							try {
								val idea = getTargetPosition(recyclerView14, textValue)
								nestedScrollView4.smoothScrollTo(
									0, recyclerView14.getChildAt(
										idea
									).y.toInt()
								)

							} catch (e: Exception) {
								e.printStackTrace()
							}
						}
					})
				val wmlp = dialog.window?.attributes
				wmlp?.gravity = Gravity.BOTTOM
				dialog.window?.attributes = wmlp
				dialog.recyclerView16.adapter = adapter
				adapter.notifyDataSetChanged()
				dialog.show()
				/*(activity as ContainerShop).openCategoriesDialog(itemList, null)*/
			} else {
				toast(activity, "categories not available")
			}
		}

		recyclerView5.isNestedScrollingEnabled = false
		ViewCompat.setNestedScrollingEnabled(recyclerView5, false)

		recyclerView14.isNestedScrollingEnabled = false
		ViewCompat.setNestedScrollingEnabled(recyclerView14, false)

		switch2.isChecked = false

		switch2.setOnClickListener {
			vegFilter.value = switch2.isChecked
		}

	}

	class CategorieListAdapter(
		activity: FragmentActivity?,
		val itemList: ArrayList<Subcategory?>?,
		val param: Interfaces.ReturnText
	) : RecyclerView.Adapter<CategorieListAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					android.R.layout.simple_list_item_activated_1, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return itemList?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (itemList != null) {
				holder.text?.text = itemList[holder.adapterPosition]?.name.toString()
				holder.text?.setOnClickListener(object : ClickListener() {
					override fun onOneClick(v: View) {
						param.getValue(itemList[holder.adapterPosition]?.name.toString())
					}
				})
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val text = itemView.findViewById<TextView?>(android.R.id.text1)
		}
	}

	private fun getshopDetail(type: MutableLiveData<Boolean>) {
		GGGinclude4.visibility = View.VISIBLE
		Instances.InternetCheck { internet ->
			if (internet) {
				viewModel.applyFilter(Request(utoken = Preference.get(activity, DATAFILE, TOKEN),
					shop_id = shopId,
					category_id = Preference.get(activity, DATAFILE, Preference.CATEGORY),
					vegOrNon = if (type.value != null) {
						type.value?.let {
							if (it) {
								"veg"
							} else {
								""
							}
						}
					} else {
						""
					},

					filter = filter_id
				))
				viewModel.filterData.observe(viewLifecycleOwnerLiveData.value ?: this, Observer {

					if (it != null) {
						GGGinclude4.visibility = View.GONE
						imageView71.setOnClickListener {

							val adapter = ShopDetailFragmentB.FilterMain(
								newsnew!!,
								newsub!!,
								Fragment()
							)
							val dialog = BottomSheetDialog(requireActivity())
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
							dialog.setCancelable(true)
							dialog.window?.setLayout(
								WindowManager.LayoutParams.MATCH_PARENT,
								WindowManager.LayoutParams.WRAP_CONTENT
							)
							dialog.setContentView(R.layout.bootomsheet2)
							dialog.show()
							dialog.recyclerView25.layoutManager = LinearLayoutManager(activity)
							adapter.listener = object : IFilterConstraintListener {
								override fun onFilterItemClicked(type: String, position: Int) {
									if (previousFilter == Constants.TYPE_SUBCATEGORY) {
										filter_id.subcategory =
											viewModel.filterData.value?.filter_data?.get(
												selectedFilterPosition
											)!!.getSelectedValuesAsString()
									} else if (previousFilter == Constants.TYPE_BREED) {
										filter_id.breed =
											viewModel.filterData.value?.filter_data?.get(
												selectedFilterPosition
											)!!.getSelectedValuesAsString()

									} else {
										filter_id.brand_id =
											viewModel.filterData.value?.filter_data?.get(
												selectedFilterPosition
											)!!.getSelectedValuesAsString()
									}
									newsub =
										viewModel.filterData.value?.filter_data?.get(position)?.values

									selectedFilterPosition = position

									dialog.recy26.visibility = View.VISIBLE
									dialog.button27.setBackgroundColor(
										activity?.getResources()!!.getColor(
											R.color.mine_shaft_hint
										)
									);
									dialog.recy26.layoutManager = LinearLayoutManager(activity)
									dialog.recy26.adapter = Filtersub(activity, newsub!!)


									previousFilter = type
								}

							}
							dialog.recyclerView25.adapter = adapter
//
							dialog.imageView67.setOnClickListener {
								dialog.dismiss()
							}

							dialog.textView310.setOnClickListener {
								dialog.recy26.visibility = View.GONE

							}

							dialog.button27.setOnClickListener {
								dialog.button27.setBackgroundColor(
									this.getResources().getColor(R.color.colorAccent)
								);
								dialog.recy26.visibility = View.GONE

							}
							dialog.apply.setOnClickListener {
								GGGinclude4.visibility = View.VISIBLE

								if (previousFilter == Constants.TYPE_SUBCATEGORY) {
									filter_id.subcategory =
										viewModel.filterData.value?.filter_data?.get(
											selectedFilterPosition
										)!!
											.getSelectedValuesAsString()
								} else if (previousFilter == Constants.TYPE_BREED) {
									filter_id.breed =
										viewModel.filterData.value?.filter_data?.get(
											selectedFilterPosition
										)!!
											.getSelectedValuesAsString()

								} else {
									filter_id.brand_id =
										viewModel.filterData.value?.filter_data?.get(
											selectedFilterPosition
										)!!
											.getSelectedValuesAsString()
								}



								viewModel.applyFilter(
									Request(
										utoken = Preference.get(requireActivity(), DATAFILE, TOKEN),
										shop_id = shopId,
										category_id = Preference.get(
											requireActivity(),
											DATAFILE,
											Preference.CATEGORY
										),
										subcategory_id = "",
										vegOrNon = "",
										filter = filter_id
									)
								)

								dialog.dismiss()
							}


						}
						if (it.shopData != null) {
							GGGinclude4.visibility = View.GONE
							textView23.text = it.shopData.storeName.toString()
							textView24.text = it.shopData.address.toString()
							textView26.text = it.shopData.rating ?: "0"
							textView27.text = it.shopData.distance.toString() + " km from you"
							textView272?.text = it.shopData.packingtime?.let {
								Instances.changeDateFormat("HH:mm:ss", "mm", it) + "mins"
							}
						}

						itemList = object : ArrayList<Subcategory?>() {
							init {
								if (it.shopProducts != null) {
									for (i in 0 until (it.shopProducts.size)) {
										add(it.shopProducts[i]?.subcategory)
									}
								}
							}
						}

						recyclerView5.adapter = MostPopularAdapter(
							activity,
							it.shopRecommProduct,
							viewModel
						)

						recyclerView14.adapter = CatgoryItemAdapter(
							activity,
							viewModel,
							it.shopProducts
						)

						if (it.shopBrands != null) {
							floatingActionButton7.visibility = if (it.shopBrands.isEmpty()) {
								View.GONE
							} else {
								View.VISIBLE
							}
						}

						it.shopBrands?.add(0, ShopBrandsItem(id = "", name = "All"))

						floatingActionButton7.setOnClickListener { _ ->
							val arrayList = it.shopBrands as java.util.ArrayList<Any?>
							val dialog = Dialog(requireContext())
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
							dialog.setCancelable(true)
							dialog.setContentView(R.layout.custom_list_dialog_new)
							dialog.textView223.text = "Filter by brand"
							dialog.textView223.visibility = View.VISIBLE
							val recyclerView16 =
								dialog.findViewById<RecyclerView>(R.id.recyclerView16)
							recyclerView16?.layoutManager = LinearLayoutManager(activity)
							if (arrayList != null) {
								if (arrayList.isNotEmpty()) {
									val adapter =
										Instances.CustomStringListAdapter(requireActivity(),
											arrayList,
											dialog,
											null,
											object : Interfaces.ReturnAny {
												override fun getValue(values: Any?) {
													when (values) {
														is ShopBrandsItem -> {
															if (values.name == "All") {
																recyclerView14.adapter =
																	CatgoryItemAdapter(
																		activity,
																		viewModel,
																		it.shopProducts
																	)
															} else {
																val listItems =
																	ArrayList<ItemsItem?>()
																if (!it.shopProducts.isNullOrEmpty()) {
																	for (i in 0 until it.shopProducts.size) {
																		if (!it.shopProducts[i]?.items.isNullOrEmpty()) {
																			for (j in 0 until (it.shopProducts[i]?.items?.size
																				?: 0)) {
																				if (it.shopProducts[i]?.items?.get(
																						j
																					)?.brand.toString()
																						.equals(
																							values.name.toString(), ignoreCase = true)
																				) {
																					listItems.add(
																						it.shopProducts[i]?.items?.get(
																							j
																						)
																					)
																				}
																			}
																		}
																	}
																}
																if (!listItems.isNullOrEmpty()) {
																	recyclerView14.adapter =
																		RecomendedAdapter(
																			activity,
																			viewModel,
																			listItems
																		)
																}
															}
														}
														else -> {
														}
													}
												}
											})
									recyclerView16?.adapter = adapter
									adapter.notifyDataSetChanged()
								}
							}
							val wmlp = dialog.window?.attributes
							wmlp?.gravity = Gravity.BOTTOM
							dialog.window?.attributes = wmlp
							dialog.show()
						}

						textView267.isGone = false
						if (it.shopData?.licenses.isNullOrBlank()) {
							textView267.text = ""
						} else {
							textView267.text = "License No: ${it.shopData?.licenses}"
						}

					} else {
						recyclerView5.adapter = MostPopularAdapter(
							activity,
							it?.shopRecommProduct,
							viewModel
						)
						recyclerView14.adapter = CatgoryItemAdapter(
							activity,
							viewModel,
							it?.shopProducts
						)
						floatingActionButton7.visibility = View.GONE
						floatingActionButton7.setOnClickListener { }
						textView267.isGone = true
					}
					switch2.isChecked = type.value ?: false
					progress.visibility = View.GONE
				})
			} else {
				toast(container, getString(R.string.no_internet))
			}
		}
	}

	private fun getTargetPosition(recyclerView: RecyclerView, string: String): Int {
		val adapter = recyclerView.adapter
		return if (adapter != null) {
			if (adapter is CatgoryItemAdapter) {
				return if (adapter.itemCount > 0) {
					var ii = 0
					for (i in 0 until adapter.itemCount) {
						if ((adapter.item?.get(i)?.subcategory?.name
								?: "").toLowerCase() == string.toLowerCase()
						) {
							ii = i
						}
					}
					ii
				} else {
					0
				}
			} else {
				return 0
			}
		} else {
			0
		}
	}

	class CatgoryItemAdapter(
		val activity: FragmentActivity?,
		val viewModel: ShopDetailViewModel,
		val item: List<ShopProductsItem?>?
	) : RecyclerView.Adapter<CatgoryItemAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
			)
		}

		override fun getItemCount(): Int {
			return item?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (item != null) {
				holder.textView119?.text =
					item[holder.adapterPosition]?.subcategory?.name ?: "Category"
				holder.recyclerView17?.adapter =
					RecomendedAdapter(activity, viewModel, item[holder.adapterPosition]?.items)
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val textView119 = itemView.findViewById<TextView?>(R.id.textView119)
			val recyclerView17 = itemView.findViewById<RecyclerView?>(R.id.recyclerView17)

			init {
				recyclerView17?.addItemDecoration(
					DividerItemDecoration(
						recyclerView17.context,
						(recyclerView17.layoutManager as LinearLayoutManager).orientation
					)
				)
			}
		}

	}

	class RecomendedAdapter(
		val activity: FragmentActivity?,
		val viewModel: ShopDetailViewModel,
		val dataList: List<ItemsItem?>?
	) : RecyclerView.Adapter<RecomendedAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_recommended_list, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return dataList?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (dataList != null) {
				if (!dataList[holder.adapterPosition]?.sizeStockPrice.isNullOrEmpty()) {
					holder.textView34?.text =
						(dataList[holder.adapterPosition]?.sizeStockPrice!![0]?.sizeName
							?: "") + " ₹" +Math.round(
							(dataList[holder.adapterPosition]?.sizeStockPrice?.get(
								0
							)?.sizePrice!!.toDouble()
									)
						)
				}

				activity?.let {
					when {
						dataList[holder.adapterPosition]?.vegNon.toString() == "veg" -> holder.imageView15?.setImageDrawable(
							ContextCompat.getDrawable(activity, R.drawable.ic_vegetarian)
						)
						dataList[holder.adapterPosition]?.vegNon.toString() == "non" -> holder.imageView15?.setImageDrawable(
							ContextCompat.getDrawable(activity, R.drawable.ic_non_vegetarian)
						)
						else -> holder.imageView15?.setImageDrawable(
							ContextCompat.getDrawable(
								activity, R.drawable.ic_non_item
							)
						)
					}
				}
				holder.textView31?.text = dataList[holder.adapterPosition]?.name ?: ""
				holder.textView296!!.text=(dataList[holder.adapterPosition]?.sizeStockPrice!!.get(0)!!.pickup_discount)
				holder.item?.setOnClickListener(object : ClickListener() {
					override fun onOneClick(v: View) {
						v.findNavController().navigate(
							R.id.action_navigation_shop_detail_to_navigation_item_detail, bundleOf(
								Constants.ID to (dataList[holder.adapterPosition]?.sprdtid ?: "")
							)
						)
					}
				})
				if (dataList[holder.adapterPosition]?.spotDelivery != "1") {
					holder.textView212?.text =
						dataList[holder.adapterPosition]?.nextAvailable.toString()
				}
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val item = itemView.findViewById<ConstraintLayout?>(R.id.constraintLayout4)
			val textView31 = itemView.findViewById<TextView?>(R.id.textView31)
			val textView34 = itemView.findViewById<TextView?>(R.id.textView34)
			val textView212 = itemView.findViewById<TextView?>(R.id.textView212)
			val textView296 = itemView.findViewById<TextView?>(R.id.textView296)
			val imageView15 = itemView.findViewById<ImageView?>(R.id.imageView15)
		}

	}

	class MostPopularAdapter(
		val activity: FragmentActivity?,
		val item: List<ShopRecommProductItem?>?,
		val viewModel: ShopDetailViewModel
	) : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_most_popular, parent, false
				)
			)
		}

		override fun getItemCount(): Int {
			return item?.size ?: 0
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			if (item != null) {
				showBlur(activity, item[holder.adapterPosition]?.imgLink ?: "", holder.imageView14)
				holder.textView30?.text = item[holder.adapterPosition]?.name ?: ""
				holder.item?.setOnClickListener {
					it.findNavController().navigate(
						R.id.action_navigation_shop_detail_to_navigation_item_detail, bundleOf(
							Constants.ID to (item[holder.adapterPosition]?.sprdtid ?: "")
						)
					)
				}
			}
		}

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val imageView14 = itemView.findViewById<ImageView?>(R.id.imageView14)
			val item = itemView.findViewById<CardView?>(R.id.cardView22)
			val textView30 = itemView.findViewById<TextView?>(R.id.textView30)
		}

	}

	public fun getData():String? {
		return shopId
	}



}

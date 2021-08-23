package com.vrise.bazaar.newpages.containers

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrise.R
import com.vrise.bazaar.ex.shop.pages.main.DashBoardFragment
import com.vrise.bazaar.ex.shop.pages.shop.ItemFragment
import com.vrise.bazaar.newpages.registration.NotificationList
import com.vrise.bazaar.newpages.top.NotificationsFactory
import com.vrise.bazaar.newpages.top.NotificationsFragment
import com.vrise.bazaar.newpages.top.NotificationsRepository
import com.vrise.bazaar.newpages.top.NotificationsViewModel
import com.vrise.bazaar.newpages.top.temps.Misc
import com.vrise.bazaar.newpages.utilities.*
import com.vrise.bazaar.newpages.utilities.Constants.ACTIVITY
import com.vrise.bazaar.newpages.utilities.Constants.DATAFILE
import com.vrise.bazaar.newpages.utilities.Constants.Rem
import com.vrise.bazaar.newpages.utilities.Constants.TAG_NOTIFY
import com.vrise.bazaar.newpages.utilities.HelperMethods.hasConnection
import com.vrise.bazaar.newpages.utilities.HelperMethods.navigateTo
import com.vrise.bazaar.newpages.utilities.models.Requests
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Response
import com.vrise.bazaar.newpages.utilities.models.submodels.Data
import com.vrise.bazaar.newpages.utilities.models.submodels.NotificationsItem
import kotlinx.android.synthetic.main.activity_notification_container.*
import kotlinx.android.synthetic.main.content_notification_container.*
import kotlinx.android.synthetic.main.item_custom_toolbar.*
import retrofit2.Call

class NotificationContainer : InitMain() {
	var from: String? = ""
	var adapter: NotificationList? = null
	var viewModel: NotificationsViewModel? = null

	override fun initView() {

	}

	override fun initModel() {

	}

	override fun initControl() {
		try {
			page_tiitle.text = "Notifications"
		} catch (e: Exception) {
			e.printStackTrace()
		}

		viewModel = ViewModelProviders.of(
			this@NotificationContainer, NotificationsFactory(
				NotificationsRepository(
					apiService, this@NotificationContainer
				)
			)
		).get(NotificationsViewModel::class.java)
		val bundle: Bundle? = intent.extras

		if (bundle != null) {
			from = when (bundle.getString(Constants.ID, "")) {
				1.toString() -> "1"
				2.toString() -> "2"
				3.toString() -> "3"
				4.toString() -> "4"
				else -> ""
			}
		}
		try {
			if (from == 4.toString()) {
				fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary))
				toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}

		progressBar.visibility = View.VISIBLE
		try {
			if (bundle?.getString(Constants.APP_ID, "") == "paytoall") {
				getDetail(from)
			} else if (bundle?.getString(Constants.APP_ID, "") == "ibrbazaar") {
				getDatas()
			} else {
				getDetail(from)
			}
		} catch (e: Exception) {
			progressBar.visibility = View.GONE
			e.printStackTrace()
		}

		fab.setOnClickListener { view ->
			if (hasConnection(this@NotificationContainer)) {
				progressBar.visibility = View.VISIBLE

				if (bundle?.getString(Constants.APP_ID, "") == "paytoall") {
					val call: Call<Response>? = if (from == 1.toString()) {
						/*subscriber*/
						apiService?.clearSubscriberNotification(
							Request(
								utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
									this@NotificationContainer, DATAFILE, Constants.ID
								)
							)
						)
					} else if (from == 2.toString() || from == 3.toString()) {
						/*Agent*/
						apiService?.clearNotificationAgent(
							Request(
								utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
									this@NotificationContainer, DATAFILE, Constants.ID
								)
							)
						)
					} else {
						null
					}

					call?.enqueue(object : CallbackClient(this@NotificationContainer, object : Interfaces.Callback {
						override fun additionalData(display: String?, logout: Boolean) {

						}

						override fun failed(t: Throwable) {
							t.printStackTrace()
						}

						override fun success(response: Response?, data: Data?, state: Boolean) {
							if (state) {
								if (data != null) {
									com.vrise.bazaar.newpages.utilities.Preference.putPreference(
										this@NotificationContainer, Constants.Rem, data.notifiCount.toString(), DATAFILE
									)
								}

								try {
									adapter?.removeAll(object : Interfaces.ClickedItem {
										override fun returnIdValue(
											clickPosPosition: String, clickPosvalue: String
										) {
											adapter?.notifyDataSetChanged()
											setRecyclerEmptyList()
										}
									})
								} catch (e: Exception) {
									e.printStackTrace()
								}
							}
							progressBar.visibility = View.GONE
						}
					}) {})

				} else if (bundle?.getString(Constants.APP_ID, "") == "ibrbazaar") {
					viewModel?.clearnotification(
						Request(
							utoken = Preference.getSinglePreference(
								this@NotificationContainer, DATAFILE, Constants.ID
							)
						)
					)?.observe(this, Observer {
						getDatas()
					})
				} else {
				}
			}
		}

	}

	private fun getDatas() {
		progressBar.visibility = View.VISIBLE
		viewModel?.getShopNotifications(
			Request(
				utoken = com.vrise.bazaar.ex.util.Preference.get(
					this@NotificationContainer,
					com.vrise.bazaar.ex.util.Preference.DATAFILE,
					com.vrise.bazaar.ex.util.Preference.TOKEN
				)
			)
		)?.observe(this, Observer {
			Misc.println(it)
			recyclerView20.layoutManager = LinearLayoutManager(this)
			if (it.isNullOrEmpty()) {
				setRecyclerEmptyList()
			} else {
				recyclerView20.adapter = NotificationsFragment.NotificationsAdapter(
					viewModel,
					this@NotificationContainer,
					it.toMutableList(),
					object : Interfaces.returnBundle {
						override fun returnBundle(any: Any) {
							getDatas()
						}
					})
			}
			progressBar.visibility = View.GONE
		})
	}

	private fun getDetail(from: String?) {
		val call: Call<Response>? = if (from == 1.toString()) {
			/*Subscriber*/
			apiService?.notification(
				Request(
					utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
						this@NotificationContainer, DATAFILE, Constants.ID
					)
				)
			)
		} else if (from == 2.toString() || from == 3.toString()) {
			/*Agent*/
			apiService?.agentnotification(
				Request(
					utoken = com.vrise.bazaar.newpages.utilities.Preference.getSinglePreference(
						this@NotificationContainer, DATAFILE, Constants.ID
					)
				)
			)
		} else {
			null
		}

		if (hasConnection(this@NotificationContainer)) {
			call?.enqueue(object : ConsAndroidCallback(this@NotificationContainer, null, object : Interfaces.Callback {
				override fun additionalData(display: String?, logout: Boolean) {

				}

				override fun success(response: Response?, data: Data?, state: Boolean) {
					if (state) {
						if (data != null) {
							if (data.notifications != null) {
								if (data.notifications.isNotEmpty()) {
									setAdapter(data.notifications)
								} else {
									setRecyclerEmptyList()
								}
							} else {
								setRecyclerEmptyList()
							}
						} else {
							setRecyclerEmptyList()
						}
					} else {
						setRecyclerEmptyList()
					}
				}

				override fun failed(t: Throwable) {
					setRecyclerEmptyList()
					t.printStackTrace()
				}
			}) {})
		}

	}

	private fun setRecyclerEmptyList() {
		progressBar.visibility = View.GONE
		recyclerView20.layoutManager = LinearLayoutManager(this)
		recyclerView20.adapter = EmptyList(
			this@NotificationContainer, Requests(
				display = "No Notifications ", id = R.drawable.ic_notifications
			)
		)
	}

	private fun setAdapter(notifications: ArrayList<NotificationsItem?>) {
		recyclerView20.layoutManager = LinearLayoutManager(this)
		adapter = NotificationList(this, notifications, object : Interfaces.ClickedItem {
			override fun returnIdValue(clickPosPosition: String, clickPosvalue: String) {
				try {
					if (adapter != null) {
						if (notifications[clickPosPosition.toInt()]!!.status == "0") {
							when (from) {
							}
						} else {
							readStatus(
								from.toString(), clickPosvalue, notifications, clickPosPosition
							)
						}
					}
				} catch (e: Exception) {
					e.printStackTrace()
				}
			}
		})
		recyclerView20.adapter = adapter
		progressBar.visibility = View.GONE
	}

	private fun backToDetails(toString: String, toString1: String) {
		intent.putExtra(Constants.ID, toString)
		intent.putExtra(Constants.TYPE_OF, toString1)
		setResult(RESULT_OK, intent)
		finish()
	}

	private fun readStatus(
		from: String, clickPosvalue: String, notifications: List<NotificationsItem?>, clickPosPosition: String
	) {
		print(
			Request(
				utoken = Preference.getSinglePreference(
					this@NotificationContainer, DATAFILE, Constants.ID
				), notId = clickPosvalue
			)
		)
		val cal = if (from == 1.toString()) {
			/*subscriber*/
			apiService?.read(
				Request(
					utoken = Preference.getSinglePreference(
						this@NotificationContainer, DATAFILE, Constants.ID
					), notId = clickPosvalue
				)
			)
		} else if (from == 2.toString() || from == 3.toString()) {
			/*agent*/
			apiService?.agentread(
				Request(
					utoken = Preference.getSinglePreference(
						this@NotificationContainer, DATAFILE, Constants.ID
					), notId = clickPosvalue
				)
			)
		} else {
			null
		}

		cal?.enqueue(object : ConsAndroidCallback(this@NotificationContainer, null, object : Interfaces.Callback {
			override fun additionalData(display: String?, logout: Boolean) {

			}

			override fun success(response: Response?, data: Data?, state: Boolean) {
				if (state) {
					if (data != null) {
						Preference.putPreference(
							this@NotificationContainer, Rem, data.notifiCount.toString(), DATAFILE
						) }
					try {


					} catch (e: Exception) {
						e.printStackTrace()
					}
				}
			}

			override fun failed(t: Throwable) {
				t.printStackTrace()
			}
		}) {})
	}

	@SuppressLint("SourceLockedOrientationActivity")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			val window = window
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
			window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccentNew)
		}
		setContentView(R.layout.activity_notification_container)
		setSupportActionBar(custom_toolbar)
		setInitializer()


		imageView19.setOnClickListener {
			finish()
		}
	}

}

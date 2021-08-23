package com.vrise.bazaar.newpages.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.vrise.R
import com.vrise.bazaar.newpages.containers.NotificationContainer
import com.vrise.bazaar.newpages.utilities.Constants
import java.util.*
import kotlin.collections.ArrayList

internal class NotificationHelper(var ctx: Context) : ContextWrapper(ctx) {
	private val manager: NotificationManager by lazy {
		getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
	}
	var pendingIntent: PendingIntent? = null

	init {
		list = ArrayList()
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val chan1: NotificationChannel? = NotificationChannel(
				PRIMARY_CHANNEL,
				getString(R.string.noti_channel_default),
				NotificationManager.IMPORTANCE_DEFAULT
			)
			chan1?.lightColor = Color.GREEN
			chan1?.enableLights(true)
			chan1?.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
			chan1?.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
			chan1?.enableVibration(true)
			chan1?.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
			chan1?.let { manager.createNotificationChannel(it) }
			val chan2: NotificationChannel? = NotificationChannel(
				SECONDARY_CHANNEL,
				getString(R.string.noti_channel_second),
				NotificationManager.IMPORTANCE_HIGH
			)
			chan2?.lightColor = Color.BLUE
			chan2?.enableLights(true)
			chan2?.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
			chan2?.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
			chan2?.enableVibration(true)
			chan2?.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
			if (chan2 != null) {
				manager.createNotificationChannel(chan2)
			}
			list.add(PRIMARY_CHANNEL)
			list.add(SECONDARY_CHANNEL)
		}
	}

	fun getSimpleNotification(title: String, body: String): Notification.Builder {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

			val rand = Random()
			val channel = list[rand.nextInt(list.size)]
			Notification.Builder(applicationContext, channel).setContentTitle(title)
				.setContentText(body)

				.setColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
				.setSmallIcon(smallIcon).setAutoCancel(true)
		} else {
			Notification.Builder(applicationContext).setContentTitle(title).setContentText(body)
				.setSmallIcon(smallIcon)

				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.setAutoCancel(true)
		}
	}

	fun getNotification(
		to: String?,
		title: String?,
		body: String?,
		app: String
	): Notification.Builder {
		val bundle = Bundle()
		if (app == "paytoall") {
			if (to == 1.toString()) {
				bundle.putString(Constants.ID, to)
				bundle.putString(Constants.APP_ID, "paytoall")
				val notifyIntent = Intent(this, NotificationContainer::class.java)
				notifyIntent.putExtras(bundle)
				notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
				pendingIntent = PendingIntent.getActivity(
					ctx,
					0,
					notifyIntent,
					PendingIntent.FLAG_UPDATE_CURRENT
				)
			} else if (to == 2.toString()) {
				bundle.putString(Constants.ID, 3.toString())
				bundle.putString(Constants.APP_ID, "paytoall")
				val notifyIntent = Intent(this, NotificationContainer::class.java)
				notifyIntent.putExtras(bundle)
				notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
				pendingIntent = PendingIntent.getActivity(
					ctx,
					0,
					notifyIntent,
					PendingIntent.FLAG_UPDATE_CURRENT
				)
			}
		} else if (app == "ibrshop") {
			bundle.putString(Constants.ID, 4.toString())
			bundle.putString(Constants.APP_ID, "ibrbazaar")
			val notifyIntent = Intent(this, NotificationContainer::class.java)
			notifyIntent.putExtras(bundle)
			notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
			pendingIntent = PendingIntent.getActivity(ctx, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
		} else {
		}


		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val rand = Random()
			val channel = list[rand.nextInt(list.size)]
			val builder = Notification.Builder(applicationContext, channel)
			builder.setContentIntent(pendingIntent)
			builder.setContentTitle(title)
			builder.setContentText(body)
			builder.setSmallIcon(smallIcon)
			builder.setAutoCancel(true)
		} else {
			val builder = Notification.Builder(applicationContext)
			builder.setContentTitle(title)
			builder.setContentIntent(pendingIntent)
			builder.setContentText(body)
			builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
			builder.setSmallIcon(smallIcon)
			builder.setAutoCancel(true)
		}
	}

	fun notify(id: Int, notification: Notification.Builder) {
		manager.notify(id, notification.build())
	}

	private val smallIcon: Int
		get() = R.mipmap.vr2

	companion object {
		val PRIMARY_CHANNEL = "default"
		val SECONDARY_CHANNEL = "second"
		lateinit var list: ArrayList<String?>
	}
}
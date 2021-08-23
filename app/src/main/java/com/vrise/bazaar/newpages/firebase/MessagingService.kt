package com.vrise.bazaar.newpages.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

import com.vrise.bazaar.ex.util.Constants.ACTION_CHAT
import com.vrise.bazaar.ex.util.Constants.ACTION_NOTIFICATION
import com.vrise.bazaar.ex.util.Preference
import com.vrise.bazaar.ex.util.Preference.DATAFILE
import com.vrise.bazaar.ex.util.Preference.TOKEN
import java.util.*

class MessagingService : FirebaseMessagingService() {
    private var TAG = "MessagingService"
    private lateinit var helper: NotificationHelper
    private var id = 10

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d(TAG, "Message body : " + p0.notification?.body)

        if (!Preference.get(this, DATAFILE, TOKEN).isNullOrBlank()) {

            helper = NotificationHelper(this)
            id = Random().nextInt(100000000)
            if (p0.notification != null) {
                Log.d(TAG, "Message body : " + p0.notification?.body)
                try {
                    helper.notify(
                        id,
                        helper.getSimpleNotification(
                            p0.notification?.title ?: "",
                            p0.notification?.body ?: ""
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            if (p0.data.isNotEmpty()) {
                Log.d(TAG, "Message data : " + p0.data)
                p0.data.let {
                    helper.notify(
                        id,
                        helper.getNotification(
                            it["user_type"] ?: "",
                            it["title"] ?: "",
                            it["body"] ?: "",
                            it["app"] ?: ""
                        )
                    )
                    //TODO Broadcast seller accepted/ delivered
                    if (it["app"] == "ibrshop") {
                        when (it["type"]) {
                            "ord_notfi", "place_order", "delivery_status" -> {
                                val intent = Intent(ACTION_NOTIFICATION)
                                Log.d("notification", intent.toString())
                                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                            }
//                            "chat" -> {
//                                val json = Gson().fromJson(p0.data["detail"], MessagesItem::class.java)
//                                val bundle = Bundle()
//                                bundle.putSerializable("detail", json)
//                                val intent = Intent(ACTION_CHAT)
//                                intent.putExtras(bundle)
//                                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
//                            }
//                        }
//                    }
                        }
                    }
                }
            }
        }
    }
}

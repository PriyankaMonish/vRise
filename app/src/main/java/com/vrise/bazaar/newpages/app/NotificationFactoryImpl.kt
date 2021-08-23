package com.vrise.bazaar.newpages.app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

/*import zlc.season.rxdownload3.core.*
import zlc.season.rxdownload3.notification.NotificationFactory*/


class NotificationFactoryImpl/* : NotificationFactory */{
    private val channelId = "RxDownload"
    private val channelName = "RxDownload"

    /*   private val map = mutableMapOf<RealMission, NotificationCompat.Builder>()

       override fun build(context: Context, mission: RealMission, status: Status): Notification? {
           createChannelForOreo(context, channelId, channelName)

           val builder = get(mission, context)

           return when (status) {
               is Suspend -> suspend(builder)
               is Waiting -> waiting(builder)
               is Downloading -> downloading(builder, status)
               is Failed -> failed(builder)
               is Succeed -> succeed(builder)
               is Deleted -> deleted(context, mission)
               else -> {
                   Notification()
               }
           }
       }

       private fun deleted(context: Context, mission: RealMission): Notification? {
           val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
           notificationManager.cancel(mission.hashCode())
           return null
       }

       private fun suspend(builder: NotificationCompat.Builder): Notification {
           builder.setContentText("Suspended")
           dismissProgress(builder)
           return builder.build()
       }

       private fun succeed(builder: NotificationCompat.Builder): Notification {
           builder.setContentText("Success")
           dismissProgress(builder)
           return builder.build()
       }

       private fun downloading(builder: NotificationCompat.Builder, status: Status): Notification {
           builder.setContentText("Downloading")
           if (status.chunkFlag) {
               builder.setProgress(0, 0, true)
           } else {
               builder.setProgress(status.totalSize.toInt(), status.downloadSize.toInt(), false)
           }
           return builder.build()
       }

       private fun failed(builder: NotificationCompat.Builder): Notification {
           builder.setContentText("Failed")
           dismissProgress(builder)
           return builder.build()
       }

       private fun waiting(builder: NotificationCompat.Builder): Notification {
           builder.setContentText("Waiting")
           builder.setProgress(0, 0, true)
           return builder.build()
       }


       private fun dismissProgress(builder: NotificationCompat.Builder) {
           builder.setProgress(0, 0, false)
       }

       private fun get(mission: RealMission, context: Context): NotificationCompat.Builder {
           var builder = map[mission]
           if (builder == null) {
               builder = createNotificationBuilder(mission, context)
               map.put(mission, builder)
           }

           return builder.setContentTitle(mission.actual.saveName)
       }

       private fun createNotificationBuilder(mission: RealMission, context: Context): NotificationCompat.Builder {
           return NotificationCompat.Builder(context, channelId)
                   .setSmallIcon(R.drawable.ic_download)
                   .setContentTitle(mission.actual.saveName)
       }*/

    private fun createChannelForOreo(context: Context, channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            channel.enableLights(true)
            channel.setShowBadge(true)
            channel.lightColor = Color.GREEN
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            notificationManager.createNotificationChannel(channel)
        }
    }
}
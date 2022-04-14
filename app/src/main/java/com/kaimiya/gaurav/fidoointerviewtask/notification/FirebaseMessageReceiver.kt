package com.kaimiya.gaurav.fidoointerviewtask.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.widget.RemoteViews
import com.kaimiya.gaurav.fidoointerviewtask.R
import android.content.Intent
import android.app.PendingIntent
import android.app.NotificationManager
import android.os.Build
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import com.kaimiya.gaurav.fidoointerviewtask.MainActivity

class FirebaseMessageReceiver : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            showNotification(
                remoteMessage.notification!!.title,
                remoteMessage.notification!!.body
            )
        }
    }

    private fun getCustomDesign(
        title: String?,
        message: String?
    ): RemoteViews {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.notification
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.ic_notification_background
        )
        return remoteViews
    }

    fun showNotification(title: String?, message: String?) {
        val intent = Intent(this, MainActivity::class.java)
        val channelId = "notification_channel"
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.ic_notification_background)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        builder = builder.setContent(
            getCustomDesign(title, message)
        )
        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channelId, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager.notify(0, builder.build())
    }
}
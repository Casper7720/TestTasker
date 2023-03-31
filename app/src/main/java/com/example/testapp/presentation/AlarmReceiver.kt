package com.example.testapp.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.testapp.R
import com.example.testapp.presentation.screens.activity.main.MainActivity

class AlarmReceiver : BroadcastReceiver() {
    private var notificationManager: NotificationManagerCompat? = null

    override fun onReceive(p0: Context?, p1: Intent?) {
        val tapResultIntent = Intent(p0, MainActivity::class.java)
        tapResultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent =
            getActivity(p0, 0, tapResultIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

        val notification = p0?.let {
            NotificationCompat.Builder(it, NOTIFICATION_CHANNEL)
                .setContentTitle(p1?.getStringExtra(ALARM_RECEIVER_TITLE))
                .setContentText(p1?.getStringExtra(ALARM_RECEIVER_DESCRIPTION))
                .setSmallIcon(R.drawable.ic_tasker)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()
        }

        notificationManager = p0?.let { NotificationManagerCompat.from(it) }


        val channel =
            NotificationChannel(
                NOTIFICATION_CHANNEL,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

        val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes =
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()

        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        channel.setSound(ringtoneManager, audioAttributes)
        notificationManager?.createNotificationChannel(channel)


        notification?.let {
            p1?.getIntExtra(
                ALARM_RECEIVER_ID, 0
            )?.let { it1 ->
                notificationManager?.notify(
                    it1, it
                )
            }
        }


    }

    companion object {
        val ALARM_RECEIVER_TITLE = "ALARM_RECEIVER_TITLE"
        val ALARM_RECEIVER_DESCRIPTION = "ALARM_RECEIVER_DESCRIPTION"
        val ALARM_RECEIVER_ID = "ALARM_RECEIVER_ID"

        val NOTIFICATION_CHANNEL = "NOTIFICATION_CHANNEL"
        val NOTIFICATION_NAME = "NOTIFICATION_NAME"
    }
}